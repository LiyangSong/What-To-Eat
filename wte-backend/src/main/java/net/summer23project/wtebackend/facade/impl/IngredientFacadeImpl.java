package net.summer23project.wtebackend.facade.impl;

import lombok.AllArgsConstructor;
import net.summer23project.wtebackend.dto.*;
import net.summer23project.wtebackend.exception.ApiException;
import net.summer23project.wtebackend.facade.IngredientFacade;
import net.summer23project.wtebackend.mapper.IngredientNutrientAmountMapper;
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
import java.util.stream.Stream;

/**
 * @author Liyang, Yue
 */
@Component
@AllArgsConstructor
public class IngredientFacadeImpl implements IngredientFacade {
    private final IngredientService ingredientService;
    private final UserIngredientInventoryService userIngredientInventoryService;
    private final IngredientNutrientAmountService ingredientNutrientAmountService;
    private final IngredientNutrientAmountMapper ingredientNutrientAmountMapper;

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
        List<IngredientNutrientAmountReturnDto> amountReturnDtos = ingredientNutrientAmountService.getByIngredientId(ingredientId);
        List<Map<String, Object>> amountMaps = amountReturnDtos.stream()
                .map(ingredientNutrientAmountMapper::mapToIngredientNutrientAmountMap)
                .toList();

        return new IngredientDetailsReturnDto(
                ingredientId,
                ingredientReturnDto.getName(),
                ingredientReturnDto.getUnitName(),
                amountMaps
        );
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public List<IngredientDetailsReturnDto> getByName(String ingredientName, String userName) {
        List<UserIngredientInventoryReturnDto> inventoryReturnDtos = Stream.concat(
                userIngredientInventoryService.getByUserName(userName).stream(),
                userIngredientInventoryService.getByUserName("admin").stream()
        ).toList();

        // Get all Ingredient IDs
        Set<Long> inventoryIngredientIds = inventoryReturnDtos.stream()
                .map(UserIngredientInventoryReturnDto::getIngredientId)
                .collect(Collectors.toSet());

        List<IngredientReturnDto> ingredientReturnDtos = ingredientService.getByName(ingredientName);

        return ingredientReturnDtos.stream()
                .filter(ingredientReturnDto ->
                        inventoryIngredientIds.contains(ingredientReturnDto.getId()))
                .map(ingredientReturnDto -> {
                    Long ingredientId = ingredientReturnDto.getId();
                    List<IngredientNutrientAmountReturnDto> amountReturnDtos =
                            ingredientNutrientAmountService.getByIngredientId(ingredientId);
                    List<Map<String, Object>> amountMaps = amountReturnDtos.stream()
                            .map(ingredientNutrientAmountMapper::mapToIngredientNutrientAmountMap)
                            .toList();
                    return new IngredientDetailsReturnDto(
                            ingredientId,
                            ingredientReturnDto.getName(),
                            ingredientReturnDto.getUnitName(),
                            amountMaps
                    );
                }).toList();
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public List<IngredientDetailsReturnDto> getAll(String userName) {
        // Get all user ingredient inventory by userName
        List<UserIngredientInventoryReturnDto> inventoryReturnDtos = userIngredientInventoryService.getByUserName(userName);

        // Transform InventoryReturnDtos to IngredientDetailsReturnDto
        return inventoryReturnDtos.stream()
                .map(inventoryReturnDto -> {
                    Long ingredientId = inventoryReturnDto.getId();
                    IngredientReturnDto ingredientReturnDto = ingredientService.getById(ingredientId);
                    List<IngredientNutrientAmountReturnDto> amountReturnDtos =
                            ingredientNutrientAmountService.getByIngredientId(ingredientId);
                    List<Map<String, Object>> nutrientAmountMaps = amountReturnDtos.stream()
                            .map(ingredientNutrientAmountMapper::mapToIngredientNutrientAmountMap)
                            .toList();
                    return new IngredientDetailsReturnDto(
                            ingredientId,
                            ingredientReturnDto.getName(),
                            ingredientReturnDto.getUnitName(),
                            nutrientAmountMaps
                    );
                }).toList();
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public IngredientDetailsReturnDto update(
            Long ingredientId, IngredientDetailsCreateDto updatedIngredientDetailsCreateDto, String userName) {

        if(!userIngredientInventoryService.exist(userName, ingredientId)) {
            throw new ApiException(HttpStatus.UNAUTHORIZED, "Permit required to update ingredient with given ingredientId: " + ingredientId);
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
