package net.summer23project.wtebackend.service;

import net.summer23project.wtebackend.dto.IngredientCreateDto;
import net.summer23project.wtebackend.dto.IngredientReturnDto;

import java.util.List;

/**
 * @author Liyang, Yue
 */
public interface IngredientService {
    IngredientReturnDto create(IngredientCreateDto ingredientCreateDto);
    IngredientReturnDto getById(Long ingredientId);
    List<IngredientReturnDto> getByName(String ingredientName);
    List<IngredientReturnDto> getAll();
    IngredientReturnDto update(Long ingredientId, IngredientCreateDto ingredientCreateDto);
    void delete(Long ingredientId);

}
