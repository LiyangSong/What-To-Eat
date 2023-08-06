package net.summer23project.wtebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.summer23project.wtebackend.entity.Ingredient;
import net.summer23project.wtebackend.entity.Nutrient;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IngredientNutrientDto {
    private Long id;
    private int nutrientNumber;
    private Ingredient ingredient;
    private Nutrient nutrient;
}
