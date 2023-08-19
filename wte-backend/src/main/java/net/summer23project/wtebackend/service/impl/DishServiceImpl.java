package net.summer23project.wtebackend.service.impl;

import lombok.AllArgsConstructor;
import net.summer23project.wtebackend.dto.DishDto;
import net.summer23project.wtebackend.entity.Dish;
import net.summer23project.wtebackend.exception.ApiException;
import net.summer23project.wtebackend.repository.DishRepository;
import net.summer23project.wtebackend.service.DishService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
    public DishDto getDishByName(String dishName) {
        Dish dish = dishRepository.findByName(dishName)
                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "Dish does not exist with given dishName: " + dishName));
        return modelMapper.map(dish, DishDto.class);
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public List<DishDto> getAllDishes() {
        List<Dish> dishes = dishRepository.findAll();
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

    }
}
