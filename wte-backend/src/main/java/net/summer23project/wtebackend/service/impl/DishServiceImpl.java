package net.summer23project.wtebackend.service.impl;

import lombok.AllArgsConstructor;
import net.summer23project.wtebackend.dto.DishDto;
import net.summer23project.wtebackend.entity.Dish;
import net.summer23project.wtebackend.entity.DishIngredient;
import net.summer23project.wtebackend.entity.User;
import net.summer23project.wtebackend.exception.ApiException;
import net.summer23project.wtebackend.repository.DishIngredientRepository;
import net.summer23project.wtebackend.repository.DishRepository;
import net.summer23project.wtebackend.repository.UserRepository;
import net.summer23project.wtebackend.service.DishIngredientService;
import net.summer23project.wtebackend.service.DishService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DishServiceImpl implements DishService {

    private DishRepository dishRepository;

    private UserRepository userRepository;

    private ModelMapper modelMapper;

    private DishIngredientRepository dishIngredientRepository;

    @Override
    public DishDto createDish(DishDto dishDto, String userName) {
        Dish dish=modelMapper.map(dishDto, Dish.class);
        Dish savedDish=dishRepository.save(dish);
        User user = userRepository.findByName(userName)
                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "User name not exists!"));
        user.getDishes().add(savedDish);
        userRepository.save(user);
        return modelMapper.map(savedDish,DishDto.class);
    }

    @Override
    public DishDto getDishById(Long dishId) {
        Dish dish=dishRepository.findById(dishId)
                .orElseThrow(()->new RuntimeException("Dish is not exists with given id"+dishId));
        return modelMapper.map(dish,DishDto.class);
    }

    @Override
    public List<DishDto> getAllDishes(String userName) {
        User user = userRepository.findByName(userName)
                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "User name not exists!"));
        List<DishDto> dishes=user.getDishes().stream()
                .map(dish -> modelMapper.map(dish,DishDto.class))
                .collect(Collectors.toList());
        return dishes;
    }

    @Override
    public DishDto updateDish(Long dishId, DishDto updatedDish) {
        Dish dish=dishRepository.findById(dishId)
                .orElseThrow(()->new RuntimeException("Dish is not exists with given id"+dishId));
        dish.setName(updatedDish.getName());
        Set<DishIngredient> updateset=updatedDish.getDishIngredientIds().stream()
                .map(id->dishIngredientRepository.findById(id)
                        .orElseThrow(()->new RuntimeException("Dish is not exists with given id"+id)))
                .collect(Collectors.toSet());
        dish.setDishIngredients(updateset);
        return modelMapper.map(dish,DishDto.class);
    }

    @Override
    public void deleteDish(Long dishId, String userName) {
        /**
         * Still need to consider the change with dishIngredient and so on.....
         */
        Dish dish=dishRepository.findById(dishId)
                .orElseThrow(()->new RuntimeException("Dish is not exists with given id"+dishId));
        User user = userRepository.findByName(userName)
                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "User name not exists!"));
        user.getDishes().remove(dish);
        dishRepository.deleteById(dishId);
    }
}
