package net.summer23project.wtebackend.service.impl;

import lombok.AllArgsConstructor;
import net.summer23project.wtebackend.dto.IngredientDto;
import net.summer23project.wtebackend.entity.Ingredient;
import net.summer23project.wtebackend.exception.ApiException;
import net.summer23project.wtebackend.mapper.IngredientMapper;
import net.summer23project.wtebackend.repository.IngredientRepository;
import net.summer23project.wtebackend.service.IngredientService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Yue, Liyang
 */
@Service
@AllArgsConstructor
public class IngredientServiceImpl implements IngredientService {
    private final IngredientRepository ingredientRepository;
    private final IngredientMapper ingredientMapper;

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public IngredientDto createIngredient(IngredientDto ingredientDto) {
        Ingredient ingredient = ingredientMapper.mapToIngredient(ingredientDto);
        Ingredient savedIngredient = ingredientRepository.save(ingredient);
        return ingredientMapper.mapToIngredientDto(savedIngredient);
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public IngredientDto getIngredientByName(String ingredientName) {
        Ingredient ingredient = ingredientRepository.findByName(ingredientName)
                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "Ingredient does not exist with given ingredientName: " + ingredientName));
        return ingredientMapper.mapToIngredientDto(ingredient);
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public List<IngredientDto> getAllIngredients() {
        List<Ingredient> ingredients = ingredientRepository.findAll();
        return ingredients.stream().map(
                ingredientMapper::mapToIngredientDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public IngredientDto updateIngredient(Long ingredientId, IngredientDto updatedIngredient) {
        return null;
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public void deleteIngredient(String ingredientName) {
        Ingredient ingredient = ingredientRepository.findByName(ingredientName)
                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "Ingredient does not exist with given ingredientName: " + ingredientName));
        ingredientRepository.delete(ingredient);
    }
}
