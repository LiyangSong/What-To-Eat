package net.summer23project.wtebackend.service;

import net.summer23project.wtebackend.dto.DishIngredientAmountDto;

import java.util.List;

/**
 * @author Liyang
 */
public interface DishIngredientAmountService {
    DishIngredientAmountDto createDishIngredientAmount(DishIngredientAmountDto dishIngredientAmountDto);
    List<DishIngredientAmountDto> getDishIngredientAmountDtosByDishName(String dishName);
}
