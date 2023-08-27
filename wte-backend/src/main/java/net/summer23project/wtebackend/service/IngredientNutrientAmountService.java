package net.summer23project.wtebackend.service;

import net.summer23project.wtebackend.dto.IngredientNutrientAmountCreateDto;
import net.summer23project.wtebackend.dto.IngredientNutrientAmountReturnDto;

import java.util.List;

/**
 * @author Liyang
 */
public interface IngredientNutrientAmountService {
    IngredientNutrientAmountReturnDto create(
            IngredientNutrientAmountCreateDto amountCreateDto);
    List<IngredientNutrientAmountReturnDto> getByIngredientId(Long ingredientId);
    IngredientNutrientAmountReturnDto update(
            Long amountId,
            IngredientNutrientAmountCreateDto updatedAmountCreateDto);
    void delete(Long amountId);
    List<IngredientNutrientAmountReturnDto> updateList(
            Long ingredientId,
            List<IngredientNutrientAmountCreateDto> updatedAmountCreateDtos);
}
