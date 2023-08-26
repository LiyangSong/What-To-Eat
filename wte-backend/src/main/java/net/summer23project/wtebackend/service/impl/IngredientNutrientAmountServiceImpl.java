package net.summer23project.wtebackend.service.impl;

import lombok.AllArgsConstructor;
import net.summer23project.wtebackend.dto.IngredientNutrientAmountCreateDto;
import net.summer23project.wtebackend.dto.IngredientNutrientAmountReturnDto;
import net.summer23project.wtebackend.exception.ApiException;
import net.summer23project.wtebackend.mapper.IngredientNutrientAmountMapper;
import net.summer23project.wtebackend.repository.IngredientNutrientAmountRepository;
import net.summer23project.wtebackend.service.IngredientNutrientAmountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Liyang
 */
@Service
@AllArgsConstructor
public class IngredientNutrientAmountServiceImpl implements IngredientNutrientAmountService {
    private final IngredientNutrientAmountMapper ingredientNutrientAmountMapper;
    private final IngredientNutrientAmountRepository ingredientNutrientAmountRepository;

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public IngredientNutrientAmountReturnDto create(IngredientNutrientAmountCreateDto amountCreateDto) {
        return null;
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public List<IngredientNutrientAmountReturnDto> getByIngredientId(Long ingredientId) {
        return null;
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public IngredientNutrientAmountReturnDto update(Long amountId, IngredientNutrientAmountCreateDto updatedAmountCreateDto) {
        return null;
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public void delete(Long amountId) {

    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public List<IngredientNutrientAmountReturnDto> updateList(Long ingredientId, List<IngredientNutrientAmountCreateDto> updatedAmountCreateDtos) {
        return null;
    }
}
