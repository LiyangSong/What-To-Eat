package net.summer23project.wtebackend.service.impl;

import lombok.AllArgsConstructor;
import net.summer23project.wtebackend.dto.IngredientCreateDto;
import net.summer23project.wtebackend.dto.IngredientReturnDto;
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

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public IngredientReturnDto create(IngredientCreateDto ingredientCreateDto) {
        return null;
        //Ingredient ingredient = ingredientMapper.mapToIngredient(ingredientDto);
        //Ingredient savedIngredient = ingredientRepository.save(ingredient);
        //return ingredientMapper.mapToIngredientDto(savedIngredient);
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public IngredientReturnDto getById(Long ingredientId) {
        return null;
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public List<IngredientReturnDto> getByName(String ingredientName) {
        return null;
        //Ingredient ingredient = ingredientRepository.findByName(ingredientName)
        //        .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "Ingredient does not exist with given ingredientName: " + ingredientName));
        //return ingredientMapper.mapToIngredientDto(ingredient);
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public List<IngredientReturnDto> getAll() {
        return null;
        //List<Ingredient> ingredients = ingredientRepository.findAll();
        //return ingredients.stream().map(
        //        ingredientMapper::mapToIngredientDto)
        //        .collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public IngredientReturnDto update(Long ingredientId, IngredientCreateDto ingredientCreateDto) {
        return null;
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public void delete(Long ingredientId) {
        //Ingredient ingredient = ingredientRepository.findByName(ingredientName)
        //        .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "Ingredient does not exist with given ingredientName: " + ingredientName));
        //ingredientRepository.delete(ingredient);
    }
}
