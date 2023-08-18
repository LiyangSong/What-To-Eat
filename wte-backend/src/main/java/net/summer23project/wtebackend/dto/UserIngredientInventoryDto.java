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
public class UserIngredientInventoryDto {
    private Long userId;
    private Long ingredientId;
    private int userIngredientInventory;
}
