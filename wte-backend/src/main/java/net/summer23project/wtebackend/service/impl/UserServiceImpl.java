package net.summer23project.wtebackend.service.impl;

import lombok.AllArgsConstructor;
import net.summer23project.wtebackend.dto.DishDto;
import net.summer23project.wtebackend.dto.UserDto;
import net.summer23project.wtebackend.dto.UserIngredientInventoryDto;
import net.summer23project.wtebackend.entity.Dish;
import net.summer23project.wtebackend.entity.User;
import net.summer23project.wtebackend.entity.UserIngredientInventory;
import net.summer23project.wtebackend.exception.ApiException;
import net.summer23project.wtebackend.mapper.DishMapper;
import net.summer23project.wtebackend.mapper.UserMapper;
import net.summer23project.wtebackend.repository.DishRepository;
import net.summer23project.wtebackend.repository.IngredientRepository;
import net.summer23project.wtebackend.repository.UserIngredientInventoryRepository;
import net.summer23project.wtebackend.repository.UserRepository;
import net.summer23project.wtebackend.service.UserService;
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
    private final UserRepository userRepository;
    private final DishRepository dishRepository;
    private final IngredientRepository ingredientRepository;
    private final UserIngredientInventoryRepository userIngredientInventoryRepository;
    private final UserMapper userMapper;
    private final DishMapper dishMapper;

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public UserDto updateUserWithAddedDish(String userName, DishDto dishDto) {
        User user = userRepository.findByName(userName)
                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "User does not exist with given userName: " + userName));

        String dishName = dishDto.getName();
        Dish dish = dishRepository.findByName(dishName)
                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "Dish does not exist with given dishName: " + dishName));

        Set<Dish> dishes = user.getDishes();
        dishes.add(dish);
        user.setDishes(dishes);
        User savedUser = userRepository.save(user);

        return userMapper.mapToUserDto(savedUser);
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public List<DishDto> getDishesByUserName(String userName) {
        User user = userRepository.findByName(userName)
                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "User does not exist with given userName: " + userName));
        Set<Dish> dishes = user.getDishes();
        return dishes.stream()
                .map(dish -> dishMapper.mapToDishDto(dish))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public UserDto updateUserWithAddedUserIngredientInventoryDto(
            String userName,
            UserIngredientInventoryDto userIngredientInventoryDto) {

        User user = userRepository.findByName(userName)
                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "User does not exist with given userName: " + userName));
        Long userId = user.getId();

        String ingredientName = userIngredientInventoryDto.getIngredientName();
        Long ingredientId = ingredientRepository.findByName(ingredientName)
                        .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "Ingredient does not exist with given ingredientName: " + ingredientName))
                .getId();

        UserIngredientInventory userIngredientInventory = userIngredientInventoryRepository.findByUserIdAndIngredientId(userId, ingredientId)
                .orElseThrow(()  -> new ApiException(HttpStatus.BAD_REQUEST, "UserIngredientInventory does not exist with given userId: " + userId + " and ingredientId: " + ingredientId));


        Set<UserIngredientInventory> userIngredientInventories= user.getUserIngredientInventories();
        userIngredientInventories.add(userIngredientInventory);
        user.setUserIngredientInventories(userIngredientInventories);
        User savedUser = userRepository.save(user);

        return userMapper.mapToUserDto(savedUser);
    }
}
