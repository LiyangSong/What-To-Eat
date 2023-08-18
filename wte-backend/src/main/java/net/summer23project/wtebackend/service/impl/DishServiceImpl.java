package net.summer23project.wtebackend.service.impl;

import lombok.AllArgsConstructor;
import net.summer23project.wtebackend.dto.DishDto;
import net.summer23project.wtebackend.entity.Dish;
import net.summer23project.wtebackend.exception.ApiException;
import net.summer23project.wtebackend.repository.DishRepository;
import net.summer23project.wtebackend.service.DishService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Yue, Liyang
 */
@Service
@AllArgsConstructor
public class DishServiceImpl implements DishService {
    private DishRepository dishRepository;
    private ModelMapper modelMapper;

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public DishDto createDish(DishDto dishDto) {
        Dish dish = modelMapper.map(dishDto, Dish.class);
        Dish savedDish = dishRepository.save(dish);
        return modelMapper.map(savedDish, DishDto.class);
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public DishDto getDishById(Long dishId) {
        return null;
        //Dish dish = dishRepository.findById(dishId)
        //        .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "Dish does not exist with given id: " + dishId));
        //return modelMapper.map(dish, DishDto.class);
    }

    @Override
    public DishDto getDishByName(String dishName) {
        return null;
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public List<DishDto> getAllDishes(String userName) {
        return null;
        //User user = userRepository.findByName(userName)
        //        .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "User does not exist with given userName: " + userName));
        //List<DishDto> dishes = user.getDishes().stream()
        //        .map(dish -> modelMapper.map(dish, DishDto.class))
        //        .collect(Collectors.toList());
        //return dishes;
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public DishDto updateDish(Long dishId, DishDto updatedDish) {
        return null;
        //Dish dish = dishRepository.findById(dishId)
        //        .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "Dish does not exist with given id: " + dishId));
        //dish.setName(updatedDish.getName());
        //Set<DishIngredientAmount> updatedSet = updatedDish.getDishIngredientIds().stream()
        //        .map(id -> dishIngredientAmountRepository.findById(id)
        //                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "Dish Ingredient Repository does not exist with given id: " + id)))
        //        .collect(Collectors.toSet());
        //dish.setDishIngredients(updatedSet);
        //return modelMapper.map(dish, DishDto.class);
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
