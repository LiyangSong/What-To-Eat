package net.summer23project.wtebackend.facade.impl;

import lombok.AllArgsConstructor;
import net.summer23project.wtebackend.dto.*;
import net.summer23project.wtebackend.exception.ApiException;
import net.summer23project.wtebackend.facade.DishFacade;
import net.summer23project.wtebackend.mapper.DishIngredientAmountMapper;
import net.summer23project.wtebackend.service.DishIngredientAmountService;
import net.summer23project.wtebackend.service.DishService;
import net.summer23project.wtebackend.service.UserDishMappingService;
import net.summer23project.wtebackend.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Liyang
 */
@Component
@AllArgsConstructor
public class DishFacadeImpl implements DishFacade {
    private final DishService dishService;
    private final UserService userService;
    private final UserDishMappingService userDishMappingService;
    private final DishIngredientAmountService dishIngredientAmountService;
    private final DishIngredientAmountMapper dishIngredientAmountMapper;
    @Override
    @Transactional(rollbackFor = ApiException.class)
    public DishDetailsReturnDto createDish(
            DishDetailsCreateDto dishDetailsCreateDto, String userName) {

        DishCreateDto dishCreateDto = new DishCreateDto(
                dishDetailsCreateDto.getDishName());
        DishReturnDto dishReturnDto = dishService.create(dishCreateDto);

        Long userId = userService.getByName(userName).getId();
        UserDishMappingDto userDishMappingDto = new UserDishMappingDto(
                userId, dishReturnDto.getId());
        userDishMappingService.create(userDishMappingDto);

        List<DishIngredientAmountReturnDto> amountReturnDtos = dishDetailsCreateDto.getIngredientAmountMaps().stream().map(map -> {
            Long ingredientId = (Long) map.get("ingredientId");
            double ingredientAmount = (double) map.get("ingredientAmount");
            DishIngredientAmountCreateDto amountDto = new DishIngredientAmountCreateDto(
                    dishReturnDto.getId(), ingredientId, ingredientAmount);
            return dishIngredientAmountService.create(amountDto);
        }).toList();

        List<Map<String, Object>> amountReturnMaps = new ArrayList<>();
        for (DishIngredientAmountReturnDto amountReturnDto : amountReturnDtos) {
            amountReturnMaps.add(
                    dishIngredientAmountMapper.mapToDishIngredientAmountMap(
                            amountReturnDto));
        }

        return new DishDetailsReturnDto(
                dishReturnDto.getId(),
                dishReturnDto.getName(),
                amountReturnMaps
        );
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public String addDish(Long dishId, String userName) {
        Long userId = userService.getByName(userName).getId();
        UserDishMappingDto userDishMappingDto = new UserDishMappingDto(
                userId, dishId);
        userDishMappingService.create(userDishMappingDto);
        return "UserDishMapping has been created: UserId: " + userId + ", DishId: " + dishId;
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public DishDetailsReturnDto getDishById(Long dishId) {
        List<DishIngredientAmountReturnDto> amountReturnDtos = dishIngredientAmountService.getByDishId(dishId);
        List<Map<String, Object>> amountReturnMaps = amountReturnDtos.stream()
                .map(dishIngredientAmountMapper::mapToDishIngredientAmountMap)
                .toList();

        return new DishDetailsReturnDto(
                dishId,
                dishService.getById(dishId).getName(),
                amountReturnMaps
        );
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public List<DishDetailsReturnDto> getDishesByName(String dishName, String userName) {

        return null;
    }

    @Override
    public void updateDish(Long dishId, DishDetailsCreateDto updatedDishDetailsCreateDto) {

    }

    @Override
    public void deleteDish(Long dishId) {

    }
}
