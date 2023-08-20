package net.summer23project.wtebackend.mapper;

import lombok.AllArgsConstructor;
import net.summer23project.wtebackend.dto.DishDetailsDto;
import net.summer23project.wtebackend.dto.DishDto;
import net.summer23project.wtebackend.dto.DishIngredientAmountDto;
import net.summer23project.wtebackend.exception.ApiException;
import net.summer23project.wtebackend.repository.DishIngredientAmountRepository;
import net.summer23project.wtebackend.repository.DishRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Liyang
 */
@Service
@AllArgsConstructor
public class DishDetailsMapper {
    private DishRepository dishRepository;
    private DishIngredientAmountRepository dishIngredientAmountRepository;
    public DishDto mapToDishDto (DishDetailsDto dishDetailsDto) {
        return new DishDto(dishDetailsDto.getDishName());
    }

    public List<DishIngredientAmountDto> mapToDishIngredientAmountDtos (
            DishDetailsDto dishDetailsDto) {

        String dishName = dishDetailsDto.getDishName();
        List<Map<String, Object>> ingredientAmountMaps = dishDetailsDto.getIngredientAmountMaps();

        return ingredientAmountMaps.stream().map(ingredientAmountMap ->
            new DishIngredientAmountDto(
                    dishName,
                    (String) ingredientAmountMap.get("ingredientName"),
                    (int) ingredientAmountMap.get("ingredientAmount")
            )
        ).collect(Collectors.toList());
    }

    public DishDetailsDto mapToDishDetailsDto (
            DishDto dishDto,
            List<DishIngredientAmountDto> dishIngredientAmountDtos) {

        return new DishDetailsDto(
                dishDto.getName(),
                dishIngredientAmountDtos.stream().map(dishIngredientAmountDto -> {
                    String ingredientName = dishIngredientAmountDto.getIngredientName();
                    int ingredientAmount = dishIngredientAmountDto.getIngredientAmount();

                    Map<String, Object> ingredientAmountMap = new HashMap<>(0);
                    ingredientAmountMap.put("ingredientName", ingredientName);
                    ingredientAmountMap.put("ingredientAmount", ingredientAmount);

                    return ingredientAmountMap;
                }).collect(Collectors.toList())
        );
    }

    // With only one argument, will retrieve ingredientAmount from database
    public DishDetailsDto mapToDishDetailsDto(DishDto dishDto) {
        String dishName = dishDto.getName();
        Long dishId = dishRepository.findByName(dishName)
                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "Dish does not exist with given dishName: " + dishName))
                .getId();

        List<DishIngredientAmountDto> dishIngredientAmountDtos = dishIngredientAmountRepository.findByDishId(dishId)
                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "DishIngredientAmount does not exist with given dishId: " + dishId))
                .stream().map(dishIngredientAmount ->
                     new DishIngredientAmountDto(
                           dishIngredientAmount.getDish().getName(),
                           dishIngredientAmount.getIngredient().getName(),
                           dishIngredientAmount.getIngredientAmount()
                    )
                ).collect(Collectors.toList());

        return mapToDishDetailsDto(dishDto, dishIngredientAmountDtos);
    }
}
