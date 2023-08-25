package net.summer23project.wtebackend.service;

import net.summer23project.wtebackend.dto.DishIngredientAmountCreateDto;
import net.summer23project.wtebackend.dto.DishIngredientAmountReturnDto;

import java.util.List;

/**
 * @author Liyang
 */
public interface DishIngredientAmountService {
    DishIngredientAmountReturnDto create(DishIngredientAmountCreateDto dishIngredientAmountCreateDto);
    List<DishIngredientAmountReturnDto> getByDishId(Long dishId);
    List<DishIngredientAmountReturnDto> getByDishName(String dishName);
    DishIngredientAmountCreateDto updateDishIngredientAmount(
            DishIngredientAmountCreateDto dishIngredientAmountCreateDto,
            DishIngredientAmountCreateDto updatedDishIngredientAmountCreateDto);
    void deleteDishIngredientAmount(
            DishIngredientAmountCreateDto dishIngredientAmountCreateDto);
    List<DishIngredientAmountCreateDto> updateDishIngredientAmountList(
            List<DishIngredientAmountCreateDto> dishIngredientAmountCreateDtos,
            List<DishIngredientAmountCreateDto> updatedDishIngredientAmountCreateDtos);
}
