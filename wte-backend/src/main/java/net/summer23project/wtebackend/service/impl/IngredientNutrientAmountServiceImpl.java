package net.summer23project.wtebackend.service.impl;

import lombok.AllArgsConstructor;
import net.summer23project.wtebackend.dto.IngredientNutrientAmountCreateDto;
import net.summer23project.wtebackend.dto.IngredientNutrientAmountReturnDto;
import net.summer23project.wtebackend.entity.Ingredient;
import net.summer23project.wtebackend.entity.IngredientNutrientAmount;
import net.summer23project.wtebackend.entity.Nutrient;
import net.summer23project.wtebackend.exception.ApiException;
import net.summer23project.wtebackend.mapper.IngredientNutrientAmountMapper;
import net.summer23project.wtebackend.repository.IngredientNutrientAmountRepository;
import net.summer23project.wtebackend.repository.IngredientRepository;
import net.summer23project.wtebackend.repository.NutrientRepository;
import net.summer23project.wtebackend.service.IngredientNutrientAmountService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Liyang
 */
@Service
@AllArgsConstructor
public class IngredientNutrientAmountServiceImpl implements IngredientNutrientAmountService {
    private final IngredientRepository ingredientRepository;
    private final NutrientRepository nutrientRepository;
    private final IngredientNutrientAmountRepository ingredientNutrientAmountRepository;
    private final IngredientNutrientAmountMapper ingredientNutrientAmountMapper;

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public IngredientNutrientAmountReturnDto create(
            IngredientNutrientAmountCreateDto amountCreateDto) {

        Long ingredientId = amountCreateDto.getIngredientId();
        Ingredient ingredient = ingredientRepository.findById(ingredientId)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Ingredient does not exist with given ingredientId: " + ingredientId));

        Long nutrientId = amountCreateDto.getNutrientId();
        Nutrient nutrient = nutrientRepository.findById(nutrientId)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Nutrient does not exist with given nutrientId: " + nutrientId));

        IngredientNutrientAmount amount = new IngredientNutrientAmount();
        amount.setIngredient(ingredient);
        amount.setNutrient(nutrient);
        amount.setNutrientAmount(amountCreateDto.getNutrientAmount());

        IngredientNutrientAmount savedAmount = ingredientNutrientAmountRepository.save(amount);
        return ingredientNutrientAmountMapper.mapToIngredientNutrientAmountReturnDto(savedAmount);
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
