package net.summer23project.wtebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * @author Liyang
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DishDetailsReturnDto {
    private Long dishId;
    private String dishName;

    // Map format: {ingredientId: x, ingredientName: x, ingredientAmount: x}
    private List<Map<String, Object>> ingredientAmountMaps;
}
