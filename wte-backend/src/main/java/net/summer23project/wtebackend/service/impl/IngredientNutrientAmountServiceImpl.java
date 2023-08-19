package net.summer23project.wtebackend.service.impl;

import lombok.AllArgsConstructor;
import net.summer23project.wtebackend.dto.IngredientNutrientAmountDto;
import net.summer23project.wtebackend.entity.IngredientNutrientAmount;
import net.summer23project.wtebackend.exception.ApiException;
import net.summer23project.wtebackend.repository.IngredientNutrientAmountRepository;
import net.summer23project.wtebackend.service.IngredientNutrientAmountService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Liyang
 */
@Service
@AllArgsConstructor
public class IngredientNutrientAmountServiceImpl implements IngredientNutrientAmountService {
    private ModelMapper modelMapper;
    private IngredientNutrientAmountRepository ingredientNutrientAmountRepository;

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public IngredientNutrientAmountDto createIngredientNutrientAmount(IngredientNutrientAmountDto ingredientNutrientAmountDto) {
        IngredientNutrientAmount ingredientNutrientAmount = modelMapper.map(ingredientNutrientAmountDto, IngredientNutrientAmount.class);
        IngredientNutrientAmount savedIngredientNutrientAmount = ingredientNutrientAmountRepository.save(ingredientNutrientAmount);
        System.out.println(ingredientNutrientAmount.getIngredient().getId());
        System.out.println(ingredientNutrientAmount.getNutrient().getId());
        return modelMapper.map(savedIngredientNutrientAmount, IngredientNutrientAmountDto.class);
    }
}
