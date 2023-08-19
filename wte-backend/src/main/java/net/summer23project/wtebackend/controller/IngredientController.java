package net.summer23project.wtebackend.controller;

import lombok.AllArgsConstructor;
import net.summer23project.wtebackend.dto.IngredientDto;
import net.summer23project.wtebackend.dto.IngredientNutrientAmountDto;
import net.summer23project.wtebackend.exception.ApiException;
import net.summer23project.wtebackend.mapper.IngredientRequestMapper;
import net.summer23project.wtebackend.service.IngredientNutrientAmountService;
import net.summer23project.wtebackend.service.IngredientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


/**
 * @author Yue
 */
@RestController
@RequestMapping("/api/ingredients")
@CrossOrigin("*")
@AllArgsConstructor
public class IngredientController {
    private IngredientService ingredientService;
    private IngredientNutrientAmountService ingredientNutrientAmountService;
    private IngredientRequestMapper ingredientRequestMapper;

    // Post http://localhost:8080/api/ingredients
    @PostMapping
    @Transactional(rollbackFor = ApiException.class)
    public ResponseEntity<IngredientDto> createIngredient(
            @RequestBody Map<String, Object> requestBody) {

        IngredientDto ingredientDto = ingredientRequestMapper.mapToIngredientDto(requestBody);
        IngredientDto savedIngredientDto = ingredientService.createIngredient(ingredientDto);

        List<IngredientNutrientAmountDto> ingredientNutrientAmountDtos = ingredientRequestMapper.mapToIngredientNutrientAmountDtos(requestBody);
        for (IngredientNutrientAmountDto ingredientNutrientAmountDto : ingredientNutrientAmountDtos) {
            ingredientNutrientAmountService.createIngredientNutrientAmount(ingredientNutrientAmountDto);
        }

        return new ResponseEntity<>(savedIngredientDto, HttpStatus.CREATED);
    }
}
