package net.summer23project.wtebackend.service;

import net.summer23project.wtebackend.dto.DishDto;

import java.util.List;

/**
 * @author Yue, Liyang
 */
public interface DishService {
    DishDto createDish(DishDto dishDto);
    DishDto getDishByName(String dishName);
    List<DishDto> getAllDishes();
    DishDto updateDish(String userName, String dishName, DishDto updatedDish);
    void deleteDish(Long dishId, String userName);

}
