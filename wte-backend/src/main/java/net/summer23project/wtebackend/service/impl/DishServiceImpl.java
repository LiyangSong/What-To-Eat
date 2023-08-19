package net.summer23project.wtebackend.service.impl;

import lombok.AllArgsConstructor;
import net.summer23project.wtebackend.dto.DishDto;
import net.summer23project.wtebackend.dto.DishIngredientAmountDto;
import net.summer23project.wtebackend.entity.Dish;
import net.summer23project.wtebackend.entity.DishIngredientAmount;
import net.summer23project.wtebackend.entity.User;
import net.summer23project.wtebackend.exception.ApiException;
import net.summer23project.wtebackend.repository.DishRepository;
import net.summer23project.wtebackend.repository.UserRepository;
import net.summer23project.wtebackend.service.DishService;
import net.summer23project.wtebackend.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Yue, Liyang
 */
@Service
@AllArgsConstructor
public class DishServiceImpl implements DishService {
    private DishRepository dishRepository;
    private ModelMapper modelMapper;

    private UserService userService;

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public DishDto createDish(DishDto dishDto) {
        Dish dish = modelMapper.map(dishDto, Dish.class);
        Dish savedDish = dishRepository.save(dish);
        return modelMapper.map(savedDish, DishDto.class);
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public DishDto getDishByName(String dishName, String username) {
        Set<Dish> dishes = userService.getDishesByUsername(username);

        Dish matchedDish = dishes.stream()
                .filter(dish -> dish.getName().equals(dishName))
                .findFirst()
                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "Dish does not exist with given name: " + dishName + " for user: " + username));

        DishDto dishDto = modelMapper.map(matchedDish, DishDto.class);

        // Convert DishIngredientAmounts to DTOs and set them in the DishDto
        List<DishIngredientAmountDto> ingredientAmountDtos = matchedDish.getDishIngredientAmounts()
                .stream()
                .map(ingredientAmount -> modelMapper.map(ingredientAmount, DishIngredientAmountDto.class))
                .collect(Collectors.toList());

        dishDto.setDishIngredientAmounts(ingredientAmountDtos);

        return dishDto;
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public List<DishDto> getAllDishes(String userName) {
        Set<Dish> dishes = userService.getDishesByUsername(userName);
        return dishes.stream()
                .map(dish -> modelMapper.map(dish, DishDto.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public DishDto updateDish(String userName, String dishName, DishDto updatedDishDto) {
        return null;
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public void deleteDish(Long dishId, String userName) {
        /**
         * Still need to consider the change with dishIngredient and so on.....
         */
        //Dish dish = dishRepository.findById(dishId)
        //        .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "Dish does not exist with given id: " + dishId));
        //User user = userRepository.findByName(userName)
        //        .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "User does not exist with given userName: " + userName));
        //user.getDishes().remove(dish);
        //dishRepository.deleteById(dishId);
    }
}
