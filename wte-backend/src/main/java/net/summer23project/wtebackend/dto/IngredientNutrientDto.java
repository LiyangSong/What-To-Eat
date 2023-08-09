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
public class IngredientNutrientDto {
    private Long id;
    private int nutrientNumber;
    private Long ingredientId;
    private Long nutrientId;
}
