package net.summer23project.wtebackend.mapper;

import net.summer23project.wtebackend.dto.IngredientNutrientDto;
import net.summer23project.wtebackend.entity.IngredientNutrient;

public class IngredientNutrientMapper {
    public static IngredientNutrientDto mapToIngredientNutrientDto(IngredientNutrient ingredientNutrient){
        return new IngredientNutrientDto(
                ingredientNutrient.getId(),
                ingredientNutrient.getNutrientNumber(),
                ingredientNutrient.getIngredient(),
                ingredientNutrient.getNutrient()
        );
    }

    public static IngredientNutrient mapToIngredientNutrient(IngredientNutrientDto ingredientNutrientDto){
        return new IngredientNutrient(
                ingredientNutrientDto.getId(),
                ingredientNutrientDto.getNutrientNumber(),
                ingredientNutrientDto.getIngredient(),
                ingredientNutrientDto.getNutrient()
        );
    }
}
