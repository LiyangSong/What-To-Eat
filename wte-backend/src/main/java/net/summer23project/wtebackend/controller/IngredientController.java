package net.summer23project.wtebackend.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import net.summer23project.wtebackend.dto.IngredientDto;
import net.summer23project.wtebackend.service.IngredientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/ingredients")
@CrossOrigin("*")
@AllArgsConstructor
public class IngredientController {
    private IngredientService ingredientService;

    // Build Add Ingredient REST API
    @PostMapping
    public ResponseEntity<IngredientDto> createIngredient(@RequestBody IngredientDto ingredientDto){
        IngredientDto savedIngredient=ingredientService.createIngredient(ingredientDto);
        return new ResponseEntity<>(savedIngredient, HttpStatus.CREATED);
    }
}
