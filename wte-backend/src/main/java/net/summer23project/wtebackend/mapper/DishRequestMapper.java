package net.summer23project.wtebackend.mapper;

import lombok.AllArgsConstructor;
import net.summer23project.wtebackend.dto.DishDto;
import net.summer23project.wtebackend.dto.DishIngredientAmountDto;
import net.summer23project.wtebackend.exception.ApiException;
import net.summer23project.wtebackend.repository.DishRepository;
import net.summer23project.wtebackend.repository.IngredientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Liyang
 */
@Service
@AllArgsConstructor
public class DishRequestMapper {
    private DishRepository dishRepository;
    private IngredientRepository ingredientRepository;

    // request format will be a JSON, e.g.
    // {"dishName": "Sandwich", "ingredient": [{"ingredientName": "bread", "ingredientAmount": 2}, ...]}

    public DishDto mapToDishDto(Map<String, Object> requestBody) {
        String dishName = (String) requestBody.get("dishName");
        DishDto dishDto = new DishDto();
        dishDto.setName(dishName);
        return dishDto;
    }

    public List<DishIngredientAmountDto> mapToDishIngredientAmountDtos(Map<String, Object> requestBody) {
        String dishName = (String) requestBody.get("dishName");
        Long dishId = dishRepository.findByName(dishName)
                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "Dish does not exist with given dishName: " + dishName))
                .getId();

        List<Map<String, Object>> ingredientMaps = (List<Map<String, Object>>) requestBody.get("ingredients");
        List<DishIngredientAmountDto> dishIngredientAmountDtos = new ArrayList<>();

        for (Map<String, Object> ingredientMap : ingredientMaps) {
            String ingredientName = (String) ingredientMap.get("ingredientName");
            Long ingredientId = ingredientRepository.findByName(ingredientName)
                    .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "Ingredient does not exist with given ingredientName: " + ingredientName))
                    .getId();
            int ingredientAmount = (int) ingredientMap.get("ingredientAmount");

            DishIngredientAmountDto dishIngredientAmountDto = new DishIngredientAmountDto(
                    dishId, ingredientId, ingredientAmount
            );
            dishIngredientAmountDtos.add(dishIngredientAmountDto);
        }

        return dishIngredientAmountDtos;
    }
}
