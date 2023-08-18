package net.summer23project.wtebackend.service;

import net.summer23project.wtebackend.dto.DishDto;
import net.summer23project.wtebackend.dto.UserDto;

/**
 * @author Liyang
 */
public interface UserService {
    UserDto updateUserWithAddedDish (String userName, DishDto dishDto);
}
