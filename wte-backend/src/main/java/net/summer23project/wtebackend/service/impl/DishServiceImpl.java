package net.summer23project.wtebackend.service.impl;

import lombok.AllArgsConstructor;
import net.summer23project.wtebackend.dto.DishCreateDto;
import net.summer23project.wtebackend.dto.DishReturnDto;
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
    public DishReturnDto create(DishCreateDto dishCreateDto) {
        Dish dish = new Dish();
        dish.setName(dishCreateDto.getName());
        Dish savedDish = dishRepository.save(dish);
        return dishMapper.mapToDishReturnDto(savedDish);
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public DishReturnDto getById(Long dishId) {
        Dish dish = dishRepository.findById(dishId)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Dish does not exist with given dishId: " + dishId));
        return dishMapper.mapToDishReturnDto(dish);
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public List<DishReturnDto> getByName(String dishName) {
        List<Dish> dishes = dishRepository.findAllByNameContainingIgnoreCase(dishName)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Dish does not exist with given dishName: " + dishName));
        return dishes.stream()
                .map(dishMapper::mapToDishReturnDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public List<DishReturnDto> getAll() {
        List<Dish> dishes = dishRepository.findAll();
        return dishes.stream()
                .map(dishMapper::mapToDishReturnDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public DishReturnDto update(Long dishId, DishCreateDto dishCreateDto) {
        Dish dish = dishRepository.findById(dishId)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Dish does not exist with given dishId: " + dishId));
        dish.setName(dishCreateDto.getName());
        Dish savedDish = dishRepository.save(dish);
        return dishMapper.mapToDishReturnDto(savedDish);
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public void delete(Long dishId) {
        Dish dish = dishRepository.findById(dishId)
                        .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Dish does not exist with given dishId: " + dishId));
        dishRepository.delete(dish);
    }
}
