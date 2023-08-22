package net.summer23project.wtebackend.mapper;

import lombok.AllArgsConstructor;
import net.summer23project.wtebackend.dto.IngredientNutrientAmountDto;
import net.summer23project.wtebackend.entity.Ingredient;
import net.summer23project.wtebackend.entity.IngredientNutrientAmount;
import net.summer23project.wtebackend.entity.Nutrient;
import net.summer23project.wtebackend.exception.ApiException;
import net.summer23project.wtebackend.repository.IngredientNutrientAmountRepository;
import net.summer23project.wtebackend.repository.IngredientRepository;
import net.summer23project.wtebackend.repository.NutrientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * @author Liyang
 */
@Service
@AllArgsConstructor
public class IngredientNutrientAmountMapper {
    private final IngredientRepository ingredientRepository;
    private final NutrientRepository nutrientRepository;
    private final IngredientNutrientAmountRepository ingredientNutrientAmountRepository;

    public IngredientNutrientAmountDto mapToIngredientNutrientAmountDto(
            IngredientNutrientAmount ingredientNutrientAmount) {

        return new IngredientNutrientAmountDto(
                ingredientNutrientAmount.getIngredient().getName(),
                ingredientNutrientAmount.getNutrient().getName(),
                ingredientNutrientAmount.getNutrientAmount()
        );
    }

    public IngredientNutrientAmount mapToIngredientNutrientAmount(
            IngredientNutrientAmountDto ingredientNutrientAmountDto) {

        String ingredientName = ingredientNutrientAmountDto.getIngredientName();
        Ingredient ingredient = ingredientRepository.findByName(ingredientName)
                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "Ingredient does not exist with given ingredientName: " + ingredientName));
        Long ingredientId = ingredient.getId();

        String nutrientName = ingredientNutrientAmountDto.getNutrientName();
        Nutrient nutrient = nutrientRepository.findByName(nutrientName)
                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "Nutrient does not exist with given nutrientName: " + nutrientName));
        Long nutrientId = nutrient.getId();

        if (ingredientNutrientAmountRepository.existsByIngredientIdAndNutrientId(ingredientId, nutrientId)) {
            throw new ApiException(HttpStatus.CONFLICT, "IngredientIngredientAmount already exists with given ingredientName: " + ingredientName + " and nutrientName: " + nutrientName);
        }
        
        IngredientNutrientAmount ingredientNutrientAmount = new IngredientNutrientAmount();
        ingredientNutrientAmount.setIngredient(ingredient);
        ingredientNutrientAmount.setNutrient(nutrient);
        ingredientNutrientAmount.setNutrientAmount(ingredientNutrientAmountDto.getNutrientAmount());

        return ingredientNutrientAmount;
    }
}
