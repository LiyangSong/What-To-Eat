package net.summer23project.wtebackend.facade;

import net.summer23project.wtebackend.dto.*;

import java.util.List;

/**
 * @author Liyang
 */
public interface IngredientFacade {
    IngredientDetailsReturnDto createIngredient(IngredientDetailsCreateDto ingredientDetailsCreateDto, String userName);
    UserIngredientInventoryReturnDto addIngredient(Long ingredientId, String userName);
    String removeIngredient(Long ingredientId, String userName);
    IngredientDetailsReturnDto getIngredientById(Long ingredientId);
    List<IngredientDetailsReturnDto> getIngredientesByName(String ingredientName, String userName);
    List<IngredientDetailsReturnDto> getAllIngredientes(String userName);
    IngredientDetailsReturnDto updateIngredient(
            Long ingredientId, IngredientDetailsCreateDto updatedIngredientDetailsCreateDto, String userName);
    String deleteIngredient(Long ingredientId, String userName);
}
