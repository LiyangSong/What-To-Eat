package net.summer23project.wtebackend.service.impl;

import lombok.AllArgsConstructor;
import net.summer23project.wtebackend.dto.DishDto;
import net.summer23project.wtebackend.dto.UserDto;
import net.summer23project.wtebackend.dto.UserIngredientInventoryDto;
import net.summer23project.wtebackend.entity.Dish;
import net.summer23project.wtebackend.entity.User;
import net.summer23project.wtebackend.entity.UserIngredientInventory;
import net.summer23project.wtebackend.exception.ApiException;
import net.summer23project.wtebackend.repository.UserRepository;
import net.summer23project.wtebackend.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Liyang
 */
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private ModelMapper modelMapper;
    @Override
    @Transactional(rollbackFor = ApiException.class)
    public UserDto updateUserWithAddedDish(String userName, DishDto dishDto) {
        User user = userRepository.findByName(userName)
                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "User does not exist with given userName: " + userName));

        Set<Dish> dishes = user.getDishes();
        dishes.add(modelMapper.map(dishDto, Dish.class));
        user.setDishes(dishes);

        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public List<DishDto> getDishesByUserName(String userName) {
        User user = userRepository.findByName(userName)
                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "User does not exist with given userName: " + userName));
        Set<Dish> dishes = user.getDishes();
        return dishes.stream()
                .map(dish -> modelMapper.map(dish, DishDto.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public UserDto updateUserWithAddedUserIngredientInventoryDto(
            String userName,
            UserIngredientInventoryDto userIngredientInventoryDto) {

        User user = userRepository.findByName(userName)
                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "User does not exist with given userName: " + userName));

        Set<UserIngredientInventory> userIngredientInventories= user.getUserIngredientInventories();
        userIngredientInventories.add(modelMapper.map(userIngredientInventoryDto, UserIngredientInventory.class));
        user.setUserIngredientInventories(userIngredientInventories);

        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDto.class);
    }
}
