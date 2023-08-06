package net.summer23project.wtebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.summer23project.wtebackend.entity.DishIngredient;
import net.summer23project.wtebackend.entity.IngredientNutrient;
import net.summer23project.wtebackend.enums.UnitType;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IngredientDto {
    private Long id;
    private String name;
    private UnitType unit;
    private Set<DishIngredient> dishIngredients;
    private Set<IngredientNutrient> ingredientNutrients;
}
