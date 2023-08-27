package net.summer23project.wtebackend.service;

import net.summer23project.wtebackend.dto.DishIngredientAmountCreateDto;
import net.summer23project.wtebackend.dto.DishIngredientAmountReturnDto;

import java.util.List;

/**
 * @author Liyang
 */
public interface DishIngredientAmountService {
    DishIngredientAmountReturnDto create(
            DishIngredientAmountCreateDto amountCreateDto);
    List<DishIngredientAmountReturnDto> getByDishId(Long dishId);
    DishIngredientAmountReturnDto update(
            Long amountId,
            DishIngredientAmountCreateDto updatedAmountCreateDto);
    void delete(Long amountId);
    List<DishIngredientAmountReturnDto> updateList(
            Long  dishId,
            List<DishIngredientAmountCreateDto> updatedAmountCreateDtos);
}
