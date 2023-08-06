package net.summer23project.wtebackend.mapper;

import net.summer23project.wtebackend.dto.DishIngredientDto;
import net.summer23project.wtebackend.entity.DishIngredient;

public class DishIngredientMapper {
    public static DishIngredientDto mapToDishIngredientDto(DishIngredient dishIngredient){
        return new DishIngredientDto(
                dishIngredient.getId(),
                dishIngredient.getIngredientNumber(),
                dishIngredient.getDish(),
                dishIngredient.getIngredient()
        );
    }

    public static DishIngredient mapToDishIngredient(DishIngredientDto dishIngredientDto){
        return new DishIngredient(
                dishIngredientDto.getId(),
                dishIngredientDto.getIngredientNumber(),
                dishIngredientDto.getDish(),
                dishIngredientDto.getIngredient()
        );
    }
}
