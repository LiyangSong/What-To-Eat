package net.summer23project.wtebackend.mapper;

import lombok.AllArgsConstructor;
import net.summer23project.wtebackend.dto.DishReturnDto;
import net.summer23project.wtebackend.dto.DishIngredientAmountCreateDto;
import net.summer23project.wtebackend.exception.ApiException;
import net.summer23project.wtebackend.repository.DishIngredientAmountRepository;
import net.summer23project.wtebackend.repository.DishRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Liyang
 */
@Component
@AllArgsConstructor
public class DishDetailsMapper {
    //private final DishRepository dishRepository;
    //private final DishIngredientAmountRepository dishIngredientAmountRepository;
    //
    //public DishReturnDto mapToDishDto (DishReturnDto dishReturnDto) {
    //    return new DishReturnDto(dishReturnDto.getDishName());
    //}
    //
    //public List<DishIngredientAmountCreateDto> mapToDishIngredientAmountDtos (
    //        DishReturnDto dishReturnDto) {
    //
    //    String dishName = dishReturnDto.getDishName();
    //    List<Map<String, Object>> ingredientAmountMaps = dishReturnDto.getIngredientAmountMaps();
    //
    //    return ingredientAmountMaps.stream().map(ingredientAmountMap ->
    //        new DishIngredientAmountCreateDto(
    //                dishName,
    //                (String) ingredientAmountMap.get("ingredientName"),
    //                (int) ingredientAmountMap.get("ingredientAmount")
    //        )
    //    ).collect(Collectors.toList());
    //}
    //
    //public DishReturnDto mapToDishDetailsDto (
    //        DishReturnDto dishReturnDto,
    //        List<DishIngredientAmountCreateDto> dishIngredientAmountCreateDtos) {
    //
    //    return new DishReturnDto(
    //            dishReturnDto.getName(),
    //            dishIngredientAmountCreateDtos.stream().map(dishIngredientAmountDto -> {
    //                String ingredientName = dishIngredientAmountDto.getIngredientName();
    //                int ingredientAmount = dishIngredientAmountDto.getIngredientAmount();
    //
    //                Map<String, Object> ingredientAmountMap = new HashMap<>(0);
    //                ingredientAmountMap.put("ingredientName", ingredientName);
    //                ingredientAmountMap.put("ingredientAmount", ingredientAmount);
    //
    //                return ingredientAmountMap;
    //            }).collect(Collectors.toList())
    //    );
    //}
    //
    //// With only one argument, will retrieve ingredientAmount from database
    //public DishReturnDto mapToDishDetailsDto(DishReturnDto dishReturnDto) {
    //    String dishName = dishReturnDto.getName();
    //    Long dishId = dishRepository.findByName(dishName)
    //            .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "Dish does not exist with given dishName: " + dishName))
    //            .getId();
    //
    //    List<DishIngredientAmountCreateDto> dishIngredientAmountCreateDtos = dishIngredientAmountRepository.findByDishId(dishId)
    //            .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "DishIngredientAmount does not exist with given dishId: " + dishId))
    //            .stream().map(dishIngredientAmount ->
    //                 new DishIngredientAmountCreateDto(
    //                       dishIngredientAmount.getDish().getName(),
    //                       dishIngredientAmount.getIngredient().getName(),
    //                       dishIngredientAmount.getIngredientAmount()
    //                )
    //            ).collect(Collectors.toList());
    //
    //    return mapToDishDetailsDto(dishReturnDto, dishIngredientAmountCreateDtos);
    //}
}
