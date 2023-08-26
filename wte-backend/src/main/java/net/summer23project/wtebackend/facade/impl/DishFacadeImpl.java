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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

        // Auto create userDishMapping when user creating new dish.
        Long userId = userService.getByName(userName).getId();
        UserDishMappingDto userDishMappingDto = new UserDishMappingDto(
                userId, dishReturnDto.getId());
        userDishMappingService.create(userDishMappingDto);

        List<DishIngredientAmountReturnDto> amountReturnDtos = dishDetailsCreateDto.getIngredientAmountMaps().stream().map(map -> {
            Long ingredientId = Integer.toUnsignedLong((int) map.get("ingredientId"));
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
    public UserDishMappingDto addDish(Long dishId, String userName) {
        Long userId = userService.getByName(userName).getId();
        UserDishMappingDto userDishMappingDto = new UserDishMappingDto(
                userId, dishId);
        return userDishMappingService.create(userDishMappingDto);
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
        // Get all dishes of current user and admin.
        List<UserDishMappingDto> mappingDtos = Stream.concat(
                userDishMappingService.getByUserName(userName).stream(),
                userDishMappingService.getByUserName("admin").stream()
        ).toList();
        Set<Long> mappingDishIds = mappingDtos.stream()
                .map(UserDishMappingDto::getDishId)
                .collect(Collectors.toSet());

        // Get all dishName returnDtos.
        List<DishReturnDto> dishReturnDtos = dishService.getByName(dishName);

        return dishReturnDtos.stream()
                .filter(dishReturnDto ->
                        mappingDishIds.contains(dishReturnDto.getId()))
                .map(dishReturnDto -> {
                    Long dishId = dishReturnDto.getId();
                    List<DishIngredientAmountReturnDto> amountReturnDtos = dishIngredientAmountService.getByDishId(dishId);
                    List<Map<String, Object>> amountReturnMaps = amountReturnDtos.stream()
                            .map(dishIngredientAmountMapper::mapToDishIngredientAmountMap)
                            .toList();
                    return new DishDetailsReturnDto(
                            dishId,
                            dishReturnDto.getName(),
                            amountReturnMaps
                    );
                }).toList();
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public List<DishDetailsReturnDto> getAllDishes(String userName) {
        List<UserDishMappingDto> mappingDtos = userDishMappingService.getByUserName(userName);
        return mappingDtos.stream().map(mappingDto -> {
            Long dishId = mappingDto.getDishId();
            DishReturnDto dishReturnDto = dishService.getById(dishId);
            List<DishIngredientAmountReturnDto> amountReturnDtos = dishIngredientAmountService.getByDishId(dishId);
            List<Map<String, Object>> amountReturnMaps = amountReturnDtos.stream()
                    .map(dishIngredientAmountMapper::mapToDishIngredientAmountMap)
                    .toList();
            return new DishDetailsReturnDto(
                    dishId,
                    dishReturnDto.getName(),
                    amountReturnMaps
            );
        }).toList();
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public DishDetailsReturnDto updateDish(
            Long dishId, DishDetailsCreateDto updatedDishDetailsCreateDto, String userName) {

        List<UserDishMappingDto> mappingDtos = userDishMappingService.getByUserName(userName);
        Set<Long> mappingDishIds = mappingDtos.stream()
                .map(UserDishMappingDto::getDishId)
                .collect(Collectors.toSet());

        if (!mappingDishIds.contains(dishId)) {
            throw new ApiException(HttpStatus.UNAUTHORIZED, "Permit required to update dish with given dishId: " + dishId);
        }

        DishCreateDto updatedDishCreateDto = new DishCreateDto(
                updatedDishDetailsCreateDto.getDishName());
        DishReturnDto dishReturnDto = dishService.update(dishId, updatedDishCreateDto);

        List<DishIngredientAmountCreateDto> updatedAmountCreateDtos = updatedDishDetailsCreateDto.getIngredientAmountMaps()
                .stream().map(amountMap ->
                        dishIngredientAmountMapper.mapToDishIngredientAmountCreateDto(
                                dishId, amountMap)
                ).toList();
        List<DishIngredientAmountReturnDto> amountReturnDtos = dishIngredientAmountService.updateList(dishId, updatedAmountCreateDtos);
        List<Map<String, Object>> amountReturnMaps = amountReturnDtos.stream()
                .map(dishIngredientAmountMapper::mapToDishIngredientAmountMap)
                .toList();

        return new DishDetailsReturnDto(
                dishId,
                dishReturnDto.getName(),
                amountReturnMaps
        );
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public String deleteDish(Long dishId, String userName) {
        List<UserDishMappingDto> mappingDtos = userDishMappingService.getByUserName(userName);
        Set<Long> mappingDishIds = mappingDtos.stream()
                .map(UserDishMappingDto::getDishId)
                .collect(Collectors.toSet());

        if (!mappingDishIds.contains(dishId)) {
            throw new ApiException(HttpStatus.UNAUTHORIZED, "Permit required to delete dish with given dishId: " + dishId);
        }

        dishService.delete(dishId);
        return "Delete dish successfully with dishId: " + dishId;
    }
}
