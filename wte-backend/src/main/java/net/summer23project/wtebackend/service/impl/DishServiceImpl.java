package net.summer23project.wtebackend.service.impl;

import lombok.AllArgsConstructor;
import net.summer23project.wtebackend.dto.DishDto;
import net.summer23project.wtebackend.entity.Dish;
import net.summer23project.wtebackend.exception.ApiException;
import net.summer23project.wtebackend.mapper.DishMapper;
import net.summer23project.wtebackend.repository.DishRepository;
import net.summer23project.wtebackend.service.DishService;
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
    private final DishRepository dishRepository;
    private final DishMapper dishMapper;

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public DishDto createDish(DishDto dishDto) {
        Dish dish = dishMapper.mapToDish(dishDto);
        Dish savedDish = dishRepository.save(dish);
        return dishMapper.mapToDishDto(savedDish);
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public DishDto getDishByName(String dishName) {
        Dish dish = dishRepository.findByName(dishName)
                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "Dish does not exist with given dishName: " + dishName));
        return dishMapper.mapToDishDto(dish);
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public List<DishDto> getAllDishes() {
        List<Dish> dishes = dishRepository.findAll();
        return dishes.stream()
                .map(dishMapper::mapToDishDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public DishDto updateDish(String dishName, DishDto updatedDishDto) {
        Dish dish = dishRepository.findByName(dishName)
                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "Dish does not exist with given dishName: " + dishName));
        dish.setName(updatedDishDto.getName());
        Dish savedDish = dishRepository.save(dish);
        return dishMapper.mapToDishDto(savedDish);
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public void deleteDish(String dishName) {
        Dish dish = dishRepository.findByName(dishName)
                        .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "Dish does not exist with given dishName: " + dishName));
        dishRepository.delete(dish);
    }
}
