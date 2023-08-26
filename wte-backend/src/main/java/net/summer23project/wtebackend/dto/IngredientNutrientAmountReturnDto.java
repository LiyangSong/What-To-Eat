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
public class IngredientNutrientAmountReturnDto {
    private Long ingredientId;
    private String ingredientName;
    private Long nutrientId;
    private String nutrientName;
    private double nutrientAmount;
}
