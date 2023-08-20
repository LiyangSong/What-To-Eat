package net.summer23project.wtebackend.mapper;

import net.summer23project.wtebackend.dto.DishDetailsDto;
import net.summer23project.wtebackend.dto.DishDto;
import net.summer23project.wtebackend.dto.DishIngredientAmountDto;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Liyang
 */
@Service
public class DishDetailsMapper {
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
}
