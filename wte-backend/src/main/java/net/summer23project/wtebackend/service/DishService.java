package net.summer23project.wtebackend.service;

import net.summer23project.wtebackend.dto.DishDto;
import net.summer23project.wtebackend.entity.Dish;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author Yue, Liyang
 */
public interface DishService {
    DishDto createDish(DishDto dishDto);
    DishDto getDishByName(String dishName, String username);
    List<DishDto> getAllDishes(String userName);
    DishDto updateDish(String userName, String dishName, DishDto updatedDish);
    void deleteDish(Long dishId, String userName);

}
