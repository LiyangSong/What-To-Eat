package net.summer23project.wtebackend.service;

import net.summer23project.wtebackend.dto.DishDto;

import java.util.List;

/**
 * @author Yue, Liyang
 */
public interface DishService {
    DishDto createDish(DishDto dishDto);
    DishDto getDishById(Long dishId);
    DishDto getDishByName(String dishName);
    List<DishDto> getAllDishes(String userName);
    DishDto updateDish(Long dishId, DishDto updatedDishDto);
    void deleteDish(Long dishId, String userName);

}
