package net.summer23project.wtebackend.service;

import net.summer23project.wtebackend.dto.DishDto;
import net.summer23project.wtebackend.dto.UserDto;
import net.summer23project.wtebackend.dto.UserIngredientInventoryDto;

import java.util.List;

/**
 * @author Liyang
 */
public interface UserService {
    UserDto updateUserWithAddedDish(String userName, DishDto dishDto);
    List<DishDto> getDishesByUserName(String userName);
    UserDto updateUserWithAddedUserIngredientInventoryDto(String userName, UserIngredientInventoryDto userIngredientInventoryDto);
}
