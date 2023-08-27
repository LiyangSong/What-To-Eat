package net.summer23project.wtebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Liyang
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DishIngredientAmountReturnDto {
    private Long dishId;
    private String dishName;
    private Long ingredientId;
    private String ingredientName;
    private double ingredientAmount;
}