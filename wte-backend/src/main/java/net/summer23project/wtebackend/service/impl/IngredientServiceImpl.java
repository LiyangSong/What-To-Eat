package net.summer23project.wtebackend.service.impl;

import lombok.AllArgsConstructor;
import net.summer23project.wtebackend.dto.IngredientDto;
import net.summer23project.wtebackend.entity.Ingredient;
import net.summer23project.wtebackend.mapper.IngredientMapper;
import net.summer23project.wtebackend.repository.IngredientRepository;
import net.summer23project.wtebackend.service.IngredientService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class IngredientServiceImpl implements IngredientService {

    private IngredientRepository ingredientRepository;

    @Override
    public IngredientDto createIngredient(IngredientDto ingredientDto) {
        Ingredient ingredient= IngredientMapper.mapToIngredient(ingredientDto);
        Ingredient savedIngredient=ingredientRepository.save(ingredient);
        return IngredientMapper.mapToIngredientDto(savedIngredient);
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
