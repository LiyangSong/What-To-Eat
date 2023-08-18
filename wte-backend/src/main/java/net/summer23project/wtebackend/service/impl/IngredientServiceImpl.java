package net.summer23project.wtebackend.service.impl;

import lombok.AllArgsConstructor;
import net.summer23project.wtebackend.dto.IngredientDto;
import net.summer23project.wtebackend.entity.Ingredient;
import net.summer23project.wtebackend.repository.IngredientRepository;
import net.summer23project.wtebackend.service.IngredientService;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;


import java.util.List;

/**
 * @author Yue, Liyang
 */
@Service
@AllArgsConstructor
public class IngredientServiceImpl implements IngredientService {
    private IngredientRepository ingredientRepository;
    private ModelMapper modelMapper;

    @Override
    public IngredientDto createIngredient(IngredientDto ingredientDto, String userName) {
        Ingredient ingredient = modelMapper.map(ingredientDto, Ingredient.class);
        Ingredient savedIngredient = ingredientRepository.save(ingredient);
        return modelMapper.map(savedIngredient, IngredientDto.class);
    }

    @Override
    public IngredientDto getIngredientById(Long ingredientId) {
        return null;
    }

    @Override
    public List<IngredientDto> getAllIngredients() {
        return null;
    }

    @Override
    public IngredientDto updateIngredient(Long ingredientId, IngredientDto updatedIngredient) {
        return null;
    }

    @Override
    public void deleteIngredient(Long ingredientId) {

    }
}
