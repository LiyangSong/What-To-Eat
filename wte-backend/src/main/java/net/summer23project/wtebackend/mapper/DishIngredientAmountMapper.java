package net.summer23project.wtebackend.mapper;

import lombok.AllArgsConstructor;
import net.summer23project.wtebackend.dto.DishIngredientAmountDto;
import net.summer23project.wtebackend.entity.Dish;
import net.summer23project.wtebackend.entity.DishIngredientAmount;
import net.summer23project.wtebackend.entity.Ingredient;
import net.summer23project.wtebackend.exception.ApiException;
import net.summer23project.wtebackend.repository.DishIngredientAmountRepository;
import net.summer23project.wtebackend.repository.DishRepository;
import net.summer23project.wtebackend.repository.IngredientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * @author Liyang
 */
@Service
@AllArgsConstructor
public class DishIngredientAmountMapper {
    private final DishRepository dishRepository;
    private final IngredientRepository ingredientRepository;
    private final DishIngredientAmountRepository dishIngredientAmountRepository;

    public DishIngredientAmountDto mapToDishIngredientAmountDto(
            DishIngredientAmount dishIngredientAmount) {

        return new DishIngredientAmountDto(
                dishIngredientAmount.getDish().getName(),
                dishIngredientAmount.getIngredient().getName(),
                dishIngredientAmount.getIngredientAmount()
        );
    }

    public DishIngredientAmount mapToDishIngredientAmount(
            DishIngredientAmountDto dishIngredientAmountDto) {

        String dishName = dishIngredientAmountDto.getDishName();
        Dish dish = dishRepository.findByName(dishName)
                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "Dish does not exist with given dishName: " + dishName));
        Long dishId = dish.getId();

        String ingredientName = dishIngredientAmountDto.getIngredientName();
        Ingredient ingredient = ingredientRepository.findByName(ingredientName)
                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "Ingredient does not exist with given ingredientName: " + ingredientName));
        Long ingredientId = ingredient.getId();

        if (dishIngredientAmountRepository.existsByDishIdAndIngredientId(dishId, ingredientId)) {
            throw new ApiException(HttpStatus.CONFLICT, "DishIngredientAmount already exists with given dishName: " + dishName + " and ingredientName: " + ingredientName);
        }

        DishIngredientAmount dishIngredientAmount = new DishIngredientAmount();
        dishIngredientAmount.setDish(dish);
        dishIngredientAmount.setIngredient(ingredient);
        dishIngredientAmount.setIngredientAmount(dishIngredientAmountDto.getIngredientAmount());

        return dishIngredientAmount;
    }
}
