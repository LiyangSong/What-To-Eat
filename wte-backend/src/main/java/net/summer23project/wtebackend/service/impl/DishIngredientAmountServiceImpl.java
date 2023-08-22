package net.summer23project.wtebackend.service.impl;

import lombok.AllArgsConstructor;
import net.summer23project.wtebackend.dto.DishIngredientAmountDto;
import net.summer23project.wtebackend.entity.DishIngredientAmount;
import net.summer23project.wtebackend.exception.ApiException;
import net.summer23project.wtebackend.mapper.DishIngredientAmountMapper;
import net.summer23project.wtebackend.repository.DishIngredientAmountRepository;
import net.summer23project.wtebackend.repository.DishRepository;
import net.summer23project.wtebackend.service.DishIngredientAmountService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Liyang
 */
@Service
@AllArgsConstructor
public class DishIngredientAmountServiceImpl implements DishIngredientAmountService {
    private final DishRepository dishRepository;
    private final DishIngredientAmountRepository dishIngredientAmountRepository;
    private final DishIngredientAmountMapper dishIngredientAmountMapper;

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public DishIngredientAmountDto createDishIngredientAmount(
            DishIngredientAmountDto dishIngredientAmountDto) {

        DishIngredientAmount dishIngredientAmount = dishIngredientAmountMapper.mapToDishIngredientAmount(dishIngredientAmountDto);
        DishIngredientAmount savedDishIngredientAmount = dishIngredientAmountRepository.save(dishIngredientAmount);
        return dishIngredientAmountMapper.mapToDishIngredientAmountDto(savedDishIngredientAmount);
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public List<DishIngredientAmountDto> getDishIngredientAmountDtosByDishName(
            String dishName) {

        Long dishId = dishRepository.findByName(dishName)
                        .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "Dish does not exist with given dishName: " + dishName))
                .getId();

        List<DishIngredientAmount> dishIngredientAmounts = dishIngredientAmountRepository.findByDishId(dishId)
                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "DishIngredientAmount does not exist with given dishId: " + dishId));

        return dishIngredientAmounts.stream().map(
                dishIngredientAmountMapper::mapToDishIngredientAmountDto
        ).collect(Collectors.toList());
    }
}
