package net.summer23project.wtebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.summer23project.wtebackend.entity.Dish;
import net.summer23project.wtebackend.entity.Ingredient;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DishIngredientDto {
    private Long id;
    private int ingredientNumber;
    private Dish dish;
    private Ingredient ingredient;
}
