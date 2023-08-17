package net.summer23project.wtebackend.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import net.summer23project.wtebackend.dto.IngredientDto;
import net.summer23project.wtebackend.service.IngredientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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

    // Build Add Ingredient REST API, test url: Post 127.0.0.1:8080/api/ingredients
    @PostMapping
    public ResponseEntity<IngredientDto> createIngredient(@AuthenticationPrincipal UserDetails userDetails, @RequestBody IngredientDto ingredientDto){
        String userName = userDetails.getUsername();
        IngredientDto savedIngredient=ingredientService.createIngredient(ingredientDto,userName);
        return new ResponseEntity<>(savedIngredient, HttpStatus.CREATED);
    }
}
