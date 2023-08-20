package net.summer23project.wtebackend.controller;

import lombok.AllArgsConstructor;
import net.summer23project.wtebackend.dto.IngredientDetailsDto;
import net.summer23project.wtebackend.dto.IngredientDto;
import net.summer23project.wtebackend.dto.IngredientNutrientAmountDto;
import net.summer23project.wtebackend.exception.ApiException;
import net.summer23project.wtebackend.mapper.IngredientDetailsMapper;
import net.summer23project.wtebackend.service.IngredientNutrientAmountService;
import net.summer23project.wtebackend.service.IngredientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


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
    private IngredientDetailsMapper ingredientDetailsMapper;

    // Post http://localhost:8080/api/ingredients
    @PostMapping
    @Transactional(rollbackFor = ApiException.class)
    public ResponseEntity<IngredientDetailsDto> createIngredient(
            @RequestBody IngredientDetailsDto ingredientDetailsDto) {

        IngredientDto ingredientDto = ingredientDetailsMapper.mapToIngredientDto(ingredientDetailsDto);
        IngredientDto savedIngredientDto = ingredientService.createIngredient(ingredientDto);

        List<IngredientNutrientAmountDto> ingredientNutrientAmountDtos = ingredientDetailsMapper.mapToIngredientNutrientAmountDtos(ingredientDetailsDto);
        List<IngredientNutrientAmountDto> savedIngredientNutrientAmountDtos = new ArrayList<>();
        for (IngredientNutrientAmountDto ingredientNutrientAmountDto : ingredientNutrientAmountDtos) {
            IngredientNutrientAmountDto savedIngredientNutrientAmountDto = ingredientNutrientAmountService.createIngredientNutrientAmount(ingredientNutrientAmountDto);
            savedIngredientNutrientAmountDtos.add(savedIngredientNutrientAmountDto);
        }

        IngredientDetailsDto savedIngredientDetailsDto = ingredientDetailsMapper.mapToIngredientDetailsDto(
                savedIngredientDto, savedIngredientNutrientAmountDtos
        );

        return new ResponseEntity<>(savedIngredientDetailsDto, HttpStatus.CREATED);
    }
}
