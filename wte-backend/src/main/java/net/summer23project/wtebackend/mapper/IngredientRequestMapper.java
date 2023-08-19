package net.summer23project.wtebackend.mapper;

import lombok.AllArgsConstructor;
import net.summer23project.wtebackend.dto.IngredientDto;
import net.summer23project.wtebackend.dto.IngredientNutrientAmountDto;
import net.summer23project.wtebackend.exception.ApiException;
import net.summer23project.wtebackend.repository.IngredientRepository;
import net.summer23project.wtebackend.repository.NutrientRepository;
import net.summer23project.wtebackend.repository.UnitRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class IngredientRequestMapper {
    private UnitRepository unitRepository;
    private IngredientRepository ingredientRepository;
    private NutrientRepository nutrientRepository;

    // request format will be a JSON, e.g.
    // {"ingredientName": "Lettuce", "unitName": "G", "nutrients": [{"nutrientName": "Sodium", "nutrientAmount": 80}, ...]}

    public IngredientDto mapToIngredientDto(Map<String, Object> requestBody) {
        String ingredientName = (String) requestBody.get("ingredientName");
        String unitName = (String) requestBody.get("unitName");
        Long unitId = unitRepository.findByName(unitName)
                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "Unit does not exist with given unitName: " + unitName))
                .getId();

        IngredientDto ingredientDto = new IngredientDto();
        ingredientDto.setName(ingredientName);
        ingredientDto.setUnitId(unitId);

        return ingredientDto;
    }

    public List<IngredientNutrientAmountDto> mapToIngredientNutrientAmountDtos (Map<String, Object> requestBody){
        String ingredientName = (String) requestBody.get("ingredientName");
        Long ingredientId = ingredientRepository.findByName(ingredientName)
                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "Ingredient does not exist with given ingredientName: " + ingredientName))
                .getId();

        List<Map<String, Object>> nutrientMaps = (List<Map<String, Object>>) requestBody.get("nutrients");
        List<IngredientNutrientAmountDto> ingredientNutrientAmountDtos= new ArrayList<>();

        for (Map<String, Object> nutrientMap: nutrientMaps) {
            String nutrientName = (String) nutrientMap.get("nutrientName");
            Long nutrientId = nutrientRepository.findByName(nutrientName)
                    .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "Nutrient does not exist with given nutrientName: " + nutrientName))
                    .getId();
            int nutrientAmount = (int) nutrientMap.get("nutrientAmount");

            IngredientNutrientAmountDto ingredientNutrientAmountDto = new IngredientNutrientAmountDto(
                    ingredientId, nutrientId, nutrientAmount
            );
            ingredientNutrientAmountDtos.add(ingredientNutrientAmountDto);
        }

        return ingredientNutrientAmountDtos;
    }
}
