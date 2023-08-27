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
public class IngredientDetailsCreateDto {
    private String ingredientName;
    private String unitName;

    // Map format: {nutrientId: x, nutrientAmount: x}
    private List<Map<String, Object>> nutrientAmountMaps;
}
