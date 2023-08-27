package net.summer23project.wtebackend.mapper;

import lombok.AllArgsConstructor;
import net.summer23project.wtebackend.dto.IngredientNutrientAmountCreateDto;
import net.summer23project.wtebackend.dto.IngredientNutrientAmountReturnDto;
import net.summer23project.wtebackend.entity.IngredientNutrientAmount;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Liyang
 */
@Component
@AllArgsConstructor
public class IngredientNutrientAmountMapper {
    
    public IngredientNutrientAmountReturnDto mapToIngredientNutrientAmountReturnDto(
            IngredientNutrientAmount ingredientNutrientAmount) {

        return new IngredientNutrientAmountReturnDto(
                ingredientNutrientAmount.getIngredient().getId(),
                ingredientNutrientAmount.getIngredient().getName(),
                ingredientNutrientAmount.getNutrient().getId(),
                ingredientNutrientAmount.getNutrient().getName(),
                ingredientNutrientAmount.getNutrientAmount()
        );
    }

    public Map<String, Object> mapToIngredientNutrientAmountMap(
            IngredientNutrientAmountReturnDto amountReturnDto) {

        Map<String, Object> amountReturnMap = new HashMap<>(0);
        amountReturnMap.put("nutrientId", amountReturnDto.getNutrientId());
        amountReturnMap.put("nutrientName", amountReturnDto.getNutrientName());
        amountReturnMap.put("nutrientAmount", amountReturnDto.getNutrientAmount());
        return amountReturnMap;
    }

    public IngredientNutrientAmountCreateDto mapToIngredientNutrientAmountCreateDto(
            Long ingredientId,
            Map<String, Object> ingredientNutrientAmountMap) {

        return new IngredientNutrientAmountCreateDto(
                ingredientId,
                Integer.toUnsignedLong((int) ingredientNutrientAmountMap.get("nutrientId")),
                (double) ingredientNutrientAmountMap.get("nutrientAmount")
        );
    }
}
