package net.summer23project.wtebackend.mapper;

import lombok.AllArgsConstructor;
import net.summer23project.wtebackend.dto.IngredientDetailsDto;
import net.summer23project.wtebackend.dto.IngredientDto;
import net.summer23project.wtebackend.dto.IngredientNutrientAmountDto;
import net.summer23project.wtebackend.exception.ApiException;
import net.summer23project.wtebackend.repository.IngredientNutrientAmountRepository;
import net.summer23project.wtebackend.repository.IngredientRepository;
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
public class IngredientDetailsMapper {
    private IngredientRepository ingredientRepository;
    private IngredientNutrientAmountRepository ingredientNutrientAmountRepository;

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

    // With only one argument, will retrieve nutrientAmount from database
    public IngredientDetailsDto mapToIngredientDetailsDto(
            IngredientDto ingredientDto) {

        String ingredientName = ingredientDto.getName();
        Long ingredientId = ingredientRepository.findByName(ingredientName)
                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "Ingredient does not exist with given ingredientName: " + ingredientName))
                .getId();

        List<IngredientNutrientAmountDto> ingredientNutrientAmountDtos = ingredientNutrientAmountRepository.findByIngredientId(ingredientId)
                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "IngredientNutrientAmount does not exist with given ingredientId: " + ingredientId))
                .stream().map(ingredientNutrientAmount ->
                        new IngredientNutrientAmountDto(
                                ingredientNutrientAmount.getIngredient().getName(),
                                ingredientNutrientAmount.getNutrient().getName(),
                                ingredientNutrientAmount.getNutrientAmount()
                        )
                ).collect(Collectors.toList());

        return mapToIngredientDetailsDto(ingredientDto, ingredientNutrientAmountDtos);
    }
}
