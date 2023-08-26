package net.summer23project.wtebackend.mapper;

import net.summer23project.wtebackend.dto.DishIngredientAmountCreateDto;
import net.summer23project.wtebackend.dto.DishIngredientAmountReturnDto;
import net.summer23project.wtebackend.entity.DishIngredientAmount;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Liyang
 */
@Component
public class DishIngredientAmountMapper {

    public DishIngredientAmountReturnDto mapToDishIngredientAmountReturnDto(
            DishIngredientAmount dishIngredientAmount) {

        return new DishIngredientAmountReturnDto(
                dishIngredientAmount.getDish().getId(),
                dishIngredientAmount.getDish().getName(),
                dishIngredientAmount.getIngredient().getId(),
                dishIngredientAmount.getIngredient().getName(),
                dishIngredientAmount.getIngredientAmount()
        );
    }

    public Map<String, Object> mapToDishIngredientAmountMap(
            DishIngredientAmountReturnDto amountReturnDto) {

        Map<String, Object> amountReturnMap = new HashMap<>(0);
        amountReturnMap.put("ingredientId", amountReturnDto.getIngredientId());
        amountReturnMap.put("ingredientName", amountReturnDto.getIngredientName());
        amountReturnMap.put("ingredientAmount", amountReturnDto.getIngredientAmount());
        return amountReturnMap;
    }

    public DishIngredientAmountCreateDto mapToDishIngredientAmountCreateDto(
            Long dishId,
            Map<String, Object> dishIngredientAmountMap) {

        return new DishIngredientAmountCreateDto(
                dishId,
                Integer.toUnsignedLong((int) dishIngredientAmountMap.get("ingredientId")),
                (double) dishIngredientAmountMap.get("ingredientAmount")
        );
    }
}
