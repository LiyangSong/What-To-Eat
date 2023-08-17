package net.summer23project.wtebackend.service;

import net.summer23project.wtebackend.dto.DishDto;
import net.summer23project.wtebackend.dto.IngredientDto;
import net.summer23project.wtebackend.entity.User;

import java.util.List;

public interface DishService {
    DishDto createDish(DishDto dishDto, String userName);
    DishDto getDishById(Long dishId);
    List<DishDto> getAllDishes(String userName);
    DishDto updateDish(Long dishId, DishDto updatedDish);
    void deleteDish(Long dishId, String userName);

}
