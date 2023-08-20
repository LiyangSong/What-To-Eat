package net.summer23project.wtebackend.service.impl;

import lombok.AllArgsConstructor;
import net.summer23project.wtebackend.dto.IngredientDto;
import net.summer23project.wtebackend.entity.Ingredient;
import net.summer23project.wtebackend.exception.ApiException;
import net.summer23project.wtebackend.mapper.IngredientMapper;
import net.summer23project.wtebackend.repository.IngredientRepository;
import net.summer23project.wtebackend.service.IngredientService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

/**
 * @author Yue, Liyang
 */
@Service
@AllArgsConstructor
public class IngredientServiceImpl implements IngredientService {
    private IngredientRepository ingredientRepository;
    private IngredientMapper ingredientMapper;

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public IngredientDto createIngredient(IngredientDto ingredientDto) {
        Ingredient ingredient = ingredientMapper.mapToIngredient(ingredientDto);
        Ingredient savedIngredient = ingredientRepository.save(ingredient);
        return ingredientMapper.mapToIngredientDto(savedIngredient);
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public IngredientDto getIngredientById(Long ingredientId) {
        return null;
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public List<IngredientDto> getAllIngredients() {
        return null;
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public IngredientDto updateIngredient(Long ingredientId, IngredientDto updatedIngredient) {
        return null;
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public void deleteIngredient(Long ingredientId) {

    }
}
