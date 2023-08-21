package net.summer23project.wtebackend.service;

import net.summer23project.wtebackend.dto.IngredientDto;

import java.util.List;

/**
 * @author Liyang, Yue
 */
public interface IngredientService {
    IngredientDto createIngredient(IngredientDto ingredientDto);
    IngredientDto getIngredientByName(String ingredientName);
    List<IngredientDto> getAllIngredients();
    IngredientDto updateIngredient(Long ingredientId, IngredientDto updatedIngredient);
    void deleteIngredient(String ingredientName);

}
