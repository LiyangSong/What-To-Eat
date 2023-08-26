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
public class IngredientDetailsReturnDto {
    private Long ingredientId;
    private String ingredientName;
    private String unitName;

    // Map format: {nutrientId: x, nutrientName: x, nutrientAmount: x}
    private List<Map<String, Object>> nutrientAmountMaps;
}
