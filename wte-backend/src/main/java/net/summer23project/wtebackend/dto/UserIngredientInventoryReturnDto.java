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
public class UserIngredientInventoryReturnDto {
    private Long id;
    private String userName;
    private Long ingredientId;
    private String ingredientName;
    private double ingredientInventory;
}
