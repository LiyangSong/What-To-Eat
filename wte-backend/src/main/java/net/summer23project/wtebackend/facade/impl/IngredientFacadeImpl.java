package net.summer23project.wtebackend.facade.impl;

import lombok.AllArgsConstructor;
import net.summer23project.wtebackend.dto.*;
import net.summer23project.wtebackend.entity.Ingredient;
import net.summer23project.wtebackend.exception.ApiException;
import net.summer23project.wtebackend.facade.IngredientFacade;
import net.summer23project.wtebackend.mapper.IngredientNutrientAmountMapper;
import net.summer23project.wtebackend.repository.IngredientRepository;
import net.summer23project.wtebackend.service.IngredientNutrientAmountService;
import net.summer23project.wtebackend.service.IngredientService;
import net.summer23project.wtebackend.service.UserIngredientInventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Liyang
 */
@Component
@AllArgsConstructor
public class IngredientFacadeImpl implements IngredientFacade {
    private final IngredientService ingredientService;
    private final UserIngredientInventoryService userIngredientInventoryService;
    private final IngredientNutrientAmountService ingredientNutrientAmountService;
    private final IngredientNutrientAmountMapper ingredientNutrientAmountMapper;
    private final IngredientRepository ingredientRepository;


    @Override
    @Transactional(rollbackFor = ApiException.class)
    public IngredientDetailsReturnDto create(
            IngredientDetailsCreateDto ingredientDetailsCreateDto, String userName) {

        // Create new ingredient
        IngredientCreateDto ingredientCreateDto = new IngredientCreateDto(
                ingredientDetailsCreateDto.getIngredientName(),
                ingredientDetailsCreateDto.getUnitName()
        );
        IngredientReturnDto ingredientReturnDto = ingredientService.create(ingredientCreateDto);

        // Auto create new userIngredientInventory when creating new ingredient
        UserIngredientInventoryCreateDto inventoryCreateDto = new UserIngredientInventoryCreateDto();
        inventoryCreateDto.setUserName(userName);
        inventoryCreateDto.setIngredientId(ingredientReturnDto.getId());
        userIngredientInventoryService.create(inventoryCreateDto);

        // Create new ingredientNutrientAmounts
         List<IngredientNutrientAmountReturnDto> amountReturnDtos = ingredientDetailsCreateDto.getNutrientAmountMaps()
                .stream().map(amountMap -> {
                    IngredientNutrientAmountCreateDto amountCreateDto = ingredientNutrientAmountMapper.mapToIngredientNutrientAmountCreateDto(
                            ingredientReturnDto.getId(), amountMap);
                    return ingredientNutrientAmountService.create(amountCreateDto);
                }).toList();

        // Get created ingredientDetails
        List<Map<String, Object>> amountReturnMaps = new ArrayList<>();
        for (IngredientNutrientAmountReturnDto amountReturnDto : amountReturnDtos) {
            amountReturnMaps.add(
                    ingredientNutrientAmountMapper.mapToIngredientNutrientAmountMap(amountReturnDto)
            );
        }

        return new IngredientDetailsReturnDto(
                ingredientReturnDto.getId(),
                ingredientReturnDto.getName(),
                ingredientReturnDto.getUnitName(),
                amountReturnMaps
        );
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public IngredientDetailsReturnDto getById(Long ingredientId) {
        IngredientReturnDto ingredientReturnDto = ingredientService.getById(ingredientId);
        List<IngredientNutrientAmountReturnDto> nutrientAmounts = ingredientNutrientAmountService.getByIngredientId(ingredientId);
        List<Map<String, Object>> nutrientAmountMaps = nutrientAmounts.stream()
                .map(ingredientNutrientAmountMapper::mapToIngredientNutrientAmountMap)
                .collect(Collectors.toList());

        return new IngredientDetailsReturnDto(
                ingredientId,
                ingredientReturnDto.getName(),
                ingredientReturnDto.getUnitName(),
                nutrientAmountMaps
        );
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public List<IngredientDetailsReturnDto> getByName(String ingredientName, String userName) {
        List<UserIngredientInventoryReturnDto> inventoryDtos = userIngredientInventoryService.getByUserName(userName);

        // Get all Ingredient IDs
        Set<Long> inventoryIngredientIds = inventoryDtos.stream()
                .map(UserIngredientInventoryReturnDto::getIngredientId)
                .collect(Collectors.toSet());

        List<Ingredient> ingredients = ingredientRepository.findAllByNameContainingIgnoreCase(ingredientName)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Ingredients not found with name: " + ingredientName));


        List<IngredientDetailsReturnDto> result= ingredients.stream()
                .filter(ingredient -> inventoryIngredientIds.contains(ingredient.getId()))
                .map(ingredient -> {
                    Long ingredientId = ingredient.getId();
                    List<IngredientNutrientAmountReturnDto> nutrientAmountReturnDtos =
                            ingredientNutrientAmountService.getByIngredientId(ingredientId);
                    List<Map<String, Object>> nutrientAmountMaps = nutrientAmountReturnDtos.stream()
                            .map(ingredientNutrientAmountMapper::mapToIngredientNutrientAmountMap)
                            .collect(Collectors.toList());
                    return new IngredientDetailsReturnDto(
                            ingredientId,
                            ingredient.getName(),
                            ingredient.getUnit().getName(),
                            nutrientAmountMaps
                    );
                }).collect(Collectors.toList());
        if (result.isEmpty()) {
            throw new ApiException(HttpStatus.NOT_FOUND, "No ingredients with the name " + ingredientName + " found in the inventory for user: " + userName);
        }

        return result;
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public List<IngredientDetailsReturnDto> getAll(String userName) {
        // Get all user ingredient inventory by userName
        List<UserIngredientInventoryReturnDto> inventoryDtos = userIngredientInventoryService.getByUserName(userName);

        //Extract all Ingredient IDs
        Set<Long> inventoryIngredientIds = inventoryDtos.stream()
                .map(UserIngredientInventoryReturnDto::getIngredientId)
                .collect(Collectors.toSet());

        // Fetch Ingredients based on IDs
        List<Ingredient> ingredients = ingredientRepository.findAllById(inventoryIngredientIds);

        //Transform Ingredients to IngredientDetailsReturnDto
        return ingredients.stream()
                .map(ingredient -> {
                    Long ingredientId = ingredient.getId();
                    List<IngredientNutrientAmountReturnDto> nutrientAmountReturnDtos =
                            ingredientNutrientAmountService.getByIngredientId(ingredientId);
                    List<Map<String, Object>> nutrientAmountMaps = nutrientAmountReturnDtos.stream()
                            .map(ingredientNutrientAmountMapper::mapToIngredientNutrientAmountMap)
                            .collect(Collectors.toList());
                    return new IngredientDetailsReturnDto(
                            ingredientId,
                            ingredient.getName(),
                            ingredient.getUnit().getName(),
                            nutrientAmountMaps
                    );
                }).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public IngredientDetailsReturnDto update(
            Long ingredientId, IngredientDetailsCreateDto updatedIngredientDetailsCreateDto, String userName) {

        if(!userIngredientInventoryService.exist(userName, ingredientId)) {
            throw new ApiException(HttpStatus.UNAUTHORIZED, "Permit required to udpate ingredient with given ingredientId: " + ingredientId);
        }

        // Update ingredient.
        IngredientCreateDto ingredientCreateDto = new IngredientCreateDto(
                updatedIngredientDetailsCreateDto.getIngredientName(),
                updatedIngredientDetailsCreateDto.getUnitName()
        );
        IngredientReturnDto ingredientReturnDto = ingredientService.update(ingredientId, ingredientCreateDto);

        // Update ingredientNutrientAmount
        List<IngredientNutrientAmountCreateDto> updatedAmountCreateDtos = updatedIngredientDetailsCreateDto.getNutrientAmountMaps()
                .stream().map(amountMap ->
                                ingredientNutrientAmountMapper.mapToIngredientNutrientAmountCreateDto(
                                        ingredientId, amountMap)
                ).toList();
        List<IngredientNutrientAmountReturnDto> amountReturnDtos = ingredientNutrientAmountService.updateList(
                ingredientId, updatedAmountCreateDtos);
        List<Map<String, Object>> amountReturnMaps = amountReturnDtos.stream()
                .map(ingredientNutrientAmountMapper::mapToIngredientNutrientAmountMap)
                .toList();

        return new IngredientDetailsReturnDto(
                ingredientId,
                ingredientReturnDto.getName(),
                ingredientReturnDto.getUnitName(),
                amountReturnMaps
        );

    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public String delete(Long ingredientId, String userName) {
        if (!userIngredientInventoryService.exist(userName, ingredientId)) {
            throw new ApiException(HttpStatus.UNAUTHORIZED, "Permit required to delete ingredient with given ingredientId: " + ingredientId);
        }

        ingredientService.delete(ingredientId);
        return "Delete ingredient successfully with ingredientId: " + ingredientId;
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public UserIngredientInventoryReturnDto add(Long ingredientId, String userName) {
        if (userIngredientInventoryService.exist(userName, ingredientId)) {
            throw new ApiException(HttpStatus.CONFLICT, "Inventory already exists with given ingredientId: " + ingredientId);
        }

        UserIngredientInventoryCreateDto inventoryCreateDto = new UserIngredientInventoryCreateDto();
        inventoryCreateDto.setUserName(userName);
        inventoryCreateDto.setIngredientId(ingredientId);
        return userIngredientInventoryService.create(inventoryCreateDto);
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public UserIngredientInventoryReturnDto getInventory(Long ingredientId, String userName) {
        return userIngredientInventoryService.getByUserNameAndIngredientId(userName, ingredientId);
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public UserIngredientInventoryReturnDto updateInventory(
            Long ingredientId, UserIngredientInventoryCreateDto updatedInventoryCreateDto, String userName) {

        if(!updatedInventoryCreateDto.getIngredientId().equals(ingredientId) ||
                !updatedInventoryCreateDto.getUserName().equals(userName)) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "InventoryCreateDto contains wrong userName or ingredientId!");
        }

        Long inventoryId = userIngredientInventoryService.getByUserNameAndIngredientId(
                userName, ingredientId).getId();
        return userIngredientInventoryService.update(inventoryId, updatedInventoryCreateDto);
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public String remove(Long ingredientId, String userName) {
        Long inventoryId = userIngredientInventoryService.getByUserNameAndIngredientId(
                userName, ingredientId).getId();
        userIngredientInventoryService.delete(inventoryId);

        return "Delete userIngredientInventory successfully with ingredientId: " + ingredientId;
    }
}
