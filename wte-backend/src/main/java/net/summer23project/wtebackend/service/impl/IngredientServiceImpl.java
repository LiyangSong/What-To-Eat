package net.summer23project.wtebackend.service.impl;

import lombok.AllArgsConstructor;
import net.summer23project.wtebackend.dto.IngredientCreateDto;
import net.summer23project.wtebackend.dto.IngredientReturnDto;
import net.summer23project.wtebackend.entity.Ingredient;
import net.summer23project.wtebackend.entity.Unit;
import net.summer23project.wtebackend.exception.ApiException;
import net.summer23project.wtebackend.mapper.IngredientMapper;
import net.summer23project.wtebackend.repository.IngredientRepository;
import net.summer23project.wtebackend.repository.UnitRepository;
import net.summer23project.wtebackend.service.IngredientService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Liyang
 */
@Service
@AllArgsConstructor
public class IngredientServiceImpl implements IngredientService {
    private final UnitRepository unitRepository;
    private final IngredientRepository ingredientRepository;
    private final IngredientMapper ingredientMapper;

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public IngredientReturnDto create(IngredientCreateDto ingredientCreateDto) {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(ingredientCreateDto.getName());

        String unitName = ingredientCreateDto.getUnitName();
        Unit unit = unitRepository.findByName(unitName)
                        .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Unit does not exist with given unitName: " + unitName));
        ingredient.setUnit(unit);

        Ingredient savedIngredient = ingredientRepository.save(ingredient);
        return ingredientMapper.mapToIngredientReturnDto(savedIngredient);
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public IngredientReturnDto getById(Long ingredientId) {
        Ingredient ingredient = ingredientRepository.findById(ingredientId)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Ingredient does not exist with given ingredientId: " + ingredientId));
        return ingredientMapper.mapToIngredientReturnDto(ingredient);
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public List<IngredientReturnDto> getByName(String ingredientName) {
        List<Ingredient> ingredients = ingredientRepository.findAllByNameContainingIgnoreCase(ingredientName)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Ingredient does not exist with given ingredientName: " + ingredientName));
        return ingredients.stream()
                .map(ingredientMapper::mapToIngredientReturnDto)
                .toList();
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public List<IngredientReturnDto> getAll() {
        List<Ingredient> ingredients = ingredientRepository.findAll();
        return ingredients.stream()
                .map(ingredientMapper::mapToIngredientReturnDto)
                .toList();
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public IngredientReturnDto update(Long ingredientId, IngredientCreateDto updatedIngredientCreateDto) {
        Ingredient ingredient = ingredientRepository.findById(ingredientId)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Ingredient does not exist with given ingredientId: " + ingredientId));
        ingredient.setName(updatedIngredientCreateDto.getName());

        String unitName = updatedIngredientCreateDto.getUnitName();
        Unit unit = unitRepository.findByName(unitName)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Unit does not exist with given unitName: " + unitName));
        ingredient.setUnit(unit);

        return ingredientMapper.mapToIngredientReturnDto(ingredient);
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public void delete(Long ingredientId) {
        Ingredient ingredient = ingredientRepository.findById(ingredientId)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Ingredient does not exist with given ingredientId: " + ingredientId));
        ingredientRepository.delete(ingredient);
    }
}
