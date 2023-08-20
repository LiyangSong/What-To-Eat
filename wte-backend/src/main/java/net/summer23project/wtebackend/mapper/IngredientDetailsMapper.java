package net.summer23project.wtebackend.mapper;

import net.summer23project.wtebackend.dto.IngredientDetailsDto;
import net.summer23project.wtebackend.dto.IngredientDto;
import net.summer23project.wtebackend.dto.IngredientNutrientAmountDto;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Liyang
 */
@Service
public class IngredientDetailsMapper {
    public IngredientDto mapToIngredientDto(
            IngredientDetailsDto ingredientDetailsDto) {
        return new IngredientDto(
                ingredientDetailsDto.getIngredientName(),
                ingredientDetailsDto.getUnitName()
        );
    }

    public List<IngredientNutrientAmountDto> mapToIngredientNutrientAmountDtos(
            IngredientDetailsDto ingredientDetailsDto) {

        String ingredientName = ingredientDetailsDto.getIngredientName();
        List<Map<String, Object>> nutrientAmountMaps = ingredientDetailsDto.getNutrientAmountMaps();

        return nutrientAmountMaps.stream().map(nutrientAmountMap ->
            new IngredientNutrientAmountDto(
                    ingredientName,
                    (String) nutrientAmountMap.get("nutrientName"),
                    (int) nutrientAmountMap.get("nutrientAmount")
            )
        ).collect(Collectors.toList());
    }

    public IngredientDetailsDto mapToIngredientDetailsDto(
            IngredientDto ingredientDto,
            List<IngredientNutrientAmountDto> ingredientNutrientAmountDtos) {

        return new IngredientDetailsDto(
                ingredientDto.getName(),
                ingredientDto.getUnitName(),
                ingredientNutrientAmountDtos.stream().map(ingredientNutrientAmountDto -> {
                    String nutrientName = ingredientNutrientAmountDto.getNutrientName();
                    int nutrientAmount = ingredientNutrientAmountDto.getNutrientAmount();

                    Map<String, Object> nutrientAmountMap = new HashMap<>(0);
                    nutrientAmountMap.put("nutrientName", nutrientName);
                    nutrientAmountMap.put("nutrientAmount", nutrientAmount);

                    return nutrientAmountMap;
                }).collect(Collectors.toList())
        );
    }
}
