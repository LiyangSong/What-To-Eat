package net.summer23project.wtebackend.service.impl;

import lombok.AllArgsConstructor;
import net.summer23project.wtebackend.dto.IngredientDto;
import net.summer23project.wtebackend.entity.Ingredient;
import net.summer23project.wtebackend.entity.Unit;
import net.summer23project.wtebackend.exception.ResourceNotFoundException;
import net.summer23project.wtebackend.repository.IngredientRepository;
import net.summer23project.wtebackend.repository.UnitRepository;
import net.summer23project.wtebackend.service.IngredientService;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;


import java.util.List;

@Service
@AllArgsConstructor
public class IngredientServiceImpl implements IngredientService {

    private IngredientRepository ingredientRepository;

    private ModelMapper modelMapper;

    private UnitRepository unitRepository;

    //test url: Post 127.0.0.1:8080/api/ingredients
    @Override
    public IngredientDto createIngredient(IngredientDto ingredientDto) {
        Ingredient ingredient = modelMapper.map(ingredientDto, Ingredient.class);
        Unit unitEntity = unitRepository.findById(ingredientDto.getUnitId())
                .orElseThrow(() -> new ResourceNotFoundException("Unit not found with ID " + ingredientDto.getUnitId()));
        ingredient.setUnit(unitEntity);
        Ingredient savedIngredient = ingredientRepository.save(ingredient);
        IngredientDto savedIngredientDto = modelMapper.map(savedIngredient, IngredientDto.class);
        return savedIngredientDto;
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
