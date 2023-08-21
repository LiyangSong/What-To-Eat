package net.summer23project.wtebackend.service.impl;

import lombok.AllArgsConstructor;
import net.summer23project.wtebackend.dto.IngredientNutrientAmountDto;
import net.summer23project.wtebackend.entity.IngredientNutrientAmount;
import net.summer23project.wtebackend.exception.ApiException;
import net.summer23project.wtebackend.mapper.IngredientNutrientMapper;
import net.summer23project.wtebackend.repository.IngredientNutrientAmountRepository;
import net.summer23project.wtebackend.service.IngredientNutrientAmountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Liyang
 */
@Service
@AllArgsConstructor
public class IngredientNutrientAmountServiceImpl implements IngredientNutrientAmountService {
    private final IngredientNutrientMapper ingredientNutrientMapper;
    private final IngredientNutrientAmountRepository ingredientNutrientAmountRepository;

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public IngredientNutrientAmountDto createIngredientNutrientAmount(IngredientNutrientAmountDto ingredientNutrientAmountDto) {
        IngredientNutrientAmount ingredientNutrientAmount = ingredientNutrientMapper.mapToIngredientNutrientAmount(ingredientNutrientAmountDto);
        IngredientNutrientAmount savedIngredientNutrientAmount = ingredientNutrientAmountRepository.save(ingredientNutrientAmount);
        return ingredientNutrientMapper.mapToIngredientNutrientAmountDto(savedIngredientNutrientAmount);
    }
}
