package net.summer23project.wtebackend.facade.impl;

import lombok.AllArgsConstructor;
import net.summer23project.wtebackend.dto.*;
import net.summer23project.wtebackend.exception.ApiException;
import net.summer23project.wtebackend.facade.IngredientFacade;
import net.summer23project.wtebackend.mapper.IngredientNutrientAmountMapper;
import net.summer23project.wtebackend.service.IngredientNutrientAmountService;
import net.summer23project.wtebackend.service.IngredientService;
import net.summer23project.wtebackend.service.UserIngredientInventoryService;
import net.summer23project.wtebackend.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Liyang
 */
@Component
@AllArgsConstructor
public class IngredientFacadeImpl implements IngredientFacade {
    private final IngredientService ingredientService;
    private final UserIngredientInventoryService userIngredientInventoryService;
    private final IngredientNutrientAmountService ingredientNutrientAmountService;
    private final UserService userService;
    private final IngredientNutrientAmountMapper ingredientNutrientAmountMapper;

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public IngredientDetailsReturnDto createIngredient(
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
    public UserIngredientInventoryReturnDto addIngredient(Long ingredientId, String userName) {
        UserIngredientInventoryCreateDto inventoryCreateDto = new UserIngredientInventoryCreateDto();
        inventoryCreateDto.setUserName(userName);
        inventoryCreateDto.setIngredientId(ingredientId);
        return userIngredientInventoryService.create(inventoryCreateDto);
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public String removeIngredient(Long ingredientId, String userName) {
        Long inventoryId = userIngredientInventoryService.getByUserNameAndIngredientId(
                userName, ingredientId).getId();
        userIngredientInventoryService.delete(inventoryId);

        return "Delete userIngredientInventory successfully with ingredientId: " + ingredientId;
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public IngredientDetailsReturnDto getIngredientById(Long ingredientId) {
        return null;
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public List<IngredientDetailsReturnDto> getIngredientesByName(String ingredientName, String userName) {
        return null;
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public List<IngredientDetailsReturnDto> getAllIngredientes(String userName) {
        return null;
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public IngredientDetailsReturnDto updateIngredient(Long ingredientId, IngredientDetailsCreateDto updatedIngredientDetailsCreateDto, String userName) {
        return null;
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public String deleteIngredient(Long ingredientId, String userName) {
        return null;
    }
}
