package net.summer23project.wtebackend.mapper;

import lombok.AllArgsConstructor;
import net.summer23project.wtebackend.dto.DishIngredientAmountCreateDto;
import net.summer23project.wtebackend.dto.DishIngredientAmountReturnDto;
import net.summer23project.wtebackend.entity.Dish;
import net.summer23project.wtebackend.entity.DishIngredientAmount;
import net.summer23project.wtebackend.entity.Ingredient;
import net.summer23project.wtebackend.exception.ApiException;
import net.summer23project.wtebackend.repository.DishIngredientAmountRepository;
import net.summer23project.wtebackend.repository.DishRepository;
import net.summer23project.wtebackend.repository.IngredientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

        Map<String, Object> amountReturnMap = new HashMap<>();
        amountReturnMap.put("ingredientId", amountReturnDto.getIngredientId());
        amountReturnMap.put("ingredientName", amountReturnDto.getIngredientName());
        amountReturnMap.put("ingredientAmount", amountReturnDto.getIngredientAmount());
        return amountReturnMap;
    }
}
