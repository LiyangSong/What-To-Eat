package net.summer23project.wtebackend.mapper;

import net.summer23project.wtebackend.dto.NutrientDto;
import net.summer23project.wtebackend.entity.Nutrient;

public class NutrientMapper {
    public static NutrientDto mapToNutrientDto(Nutrient nutrient){
        return new NutrientDto(
                nutrient.getId(),
                nutrient.getName(),
                nutrient.getUnit(),
                nutrient.getIngredients()
        );
    }

    public static Nutrient mapToNutrient(NutrientDto nutrientDto){
        return new Nutrient(
                nutrientDto.getId(),
                nutrientDto.getName(),
                nutrientDto.getUnit(),
                nutrientDto.getIngredients()
        );
    }

}
