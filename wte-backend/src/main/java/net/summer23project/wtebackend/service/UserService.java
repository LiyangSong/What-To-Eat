package net.summer23project.wtebackend.service;

import net.summer23project.wtebackend.dto.DishDto;
import net.summer23project.wtebackend.dto.UserDto;
import net.summer23project.wtebackend.entity.Dish;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author Liyang
 */
public interface UserService {
    UserDto updateUserWithAddedDish (String userName, DishDto dishDto);
    public Set<Dish> getDishesByUsername(String username);

}
