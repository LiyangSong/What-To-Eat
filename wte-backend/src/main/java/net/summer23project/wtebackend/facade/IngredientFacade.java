package net.summer23project.wtebackend.facade;

import net.summer23project.wtebackend.dto.*;

import java.util.List;

/**
 * @author Liyang
 */
public interface IngredientFacade {
    IngredientDetailsReturnDto create(IngredientDetailsCreateDto ingredientDetailsCreateDto, String userName);
    IngredientDetailsReturnDto getById(Long ingredientId);
    List<IngredientDetailsReturnDto> getByName(String ingredientName, String userName);
    List<IngredientDetailsReturnDto> getAll(String userName);
    IngredientDetailsReturnDto update(
            Long ingredientId, IngredientDetailsCreateDto updatedIngredientDetailsCreateDto, String userName);
    String delete(Long ingredientId, String userName);
    UserIngredientInventoryReturnDto add(Long ingredientId, String userName);
    UserIngredientInventoryReturnDto getInventory(Long ingredientId, String userName);
    UserIngredientInventoryReturnDto updateInventory(
            Long ingredientId, UserIngredientInventoryCreateDto updatedInventoryCreateDto, String userName);
    String remove(Long ingredientId, String userName);
}
