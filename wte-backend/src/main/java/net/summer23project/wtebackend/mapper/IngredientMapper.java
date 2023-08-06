package net.summer23project.wtebackend.mapper;

import net.summer23project.wtebackend.dto.IngredientDto;
import net.summer23project.wtebackend.entity.Ingredient;

public class IngredientMapper {
    public static IngredientDto mapToIngredientDto(Ingredient ingredient){
        return new IngredientDto(
                ingredient.getId(),
                ingredient.getName(),
                ingredient.getUnit(),
                ingredient.getDishIngredients(),
                ingredient.getIngredientNutrients()
        );
    }

    public static Ingredient mapToIngredient(IngredientDto ingredientDto){
        return new Ingredient(
                ingredientDto.getId(),
                ingredientDto.getName(),
                ingredientDto.getUnit(),
                ingredientDto.getDishIngredients(),
                ingredientDto.getIngredientNutrients()
        );
    }
}
