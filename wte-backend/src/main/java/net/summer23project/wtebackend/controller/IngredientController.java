package net.summer23project.wtebackend.controller;

import lombok.AllArgsConstructor;
import net.summer23project.wtebackend.dto.DishDetailsDto;
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
import java.util.stream.Collectors;


/**
 * @author Yue
 */
@RestController
@RequestMapping("/api/ingredients")
@CrossOrigin("*")
@AllArgsConstructor
public class IngredientController {
    private final IngredientService ingredientService;
    private final IngredientNutrientAmountService ingredientNutrientAmountService;
    private final IngredientDetailsMapper ingredientDetailsMapper;

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

    @GetMapping("{name}")
    @Transactional(rollbackFor = ApiException.class)
    public ResponseEntity<IngredientDetailsDto> getIngredientByName(
            @PathVariable("name") String ingredientName) {

        IngredientDto ingredientDto = ingredientService.getIngredientByName(ingredientName);
        IngredientDetailsDto ingredientDetailsDto = ingredientDetailsMapper.mapToIngredientDetailsDto(ingredientDto);
        return new ResponseEntity<>(ingredientDetailsDto, HttpStatus.OK);
    }

    @GetMapping
    @Transactional(rollbackFor = ApiException.class)
    public ResponseEntity<List<IngredientDetailsDto>> getAllIngredients() {
        List<IngredientDetailsDto> ingredientDetailsDtos = ingredientService.getAllIngredients()
                .stream().map(ingredientDetailsMapper::mapToIngredientDetailsDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(ingredientDetailsDtos, HttpStatus.OK);
    }

    @PutMapping("{name}")
    @Transactional(rollbackFor = ApiException.class)
    public ResponseEntity<IngredientDetailsDto> updateIngredient(
            @PathVariable("name") String ingredientName,
            @RequestBody DishDetailsDto updateIngredientDetailsDto){



        return null;
    }

    @DeleteMapping("{name}")
    @Transactional(rollbackFor = ApiException.class)
    public ResponseEntity<String> deleteIngredient(
            @PathVariable("name") String ingredientName){

        ingredientService.deleteIngredient(ingredientName);
        return new ResponseEntity<>("Delete ingredient " + ingredientName + " successfully", HttpStatus.NO_CONTENT);
    }
}
