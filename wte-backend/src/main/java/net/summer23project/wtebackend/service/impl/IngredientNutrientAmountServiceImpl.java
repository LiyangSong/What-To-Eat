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
        List<IngredientNutrientAmount> amounts = ingredientNutrientAmountRepository.findByIngredientId(ingredientId)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "IngredientNutrientAmount does not exist with given ingredientId: " + ingredientId));
        return amounts.stream()
                .map(ingredientNutrientAmountMapper::mapToIngredientNutrientAmountReturnDto)
                .toList();
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public IngredientNutrientAmountReturnDto update(
            Long amountId,
            IngredientNutrientAmountCreateDto updatedAmountCreateDto) {

        IngredientNutrientAmount amount = ingredientNutrientAmountRepository.findById(amountId)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "IngredientNutrientAmount does not exist with given amountId: " + amountId));

        Long ingredientId = updatedAmountCreateDto.getIngredientId();
        Ingredient ingredient = ingredientRepository.findById(ingredientId)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Ingredient does not exist with given ingredientId: " + ingredientId));

        Long nutrientId = updatedAmountCreateDto.getNutrientId();
        Nutrient nutrient = nutrientRepository.findById(nutrientId)
                        .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Nutrient does not exist with given nutrientId: " + nutrientId));

        amount.setIngredient(ingredient);
        amount.setNutrient(nutrient);
        amount.setNutrientAmount(updatedAmountCreateDto.getNutrientAmount());

        IngredientNutrientAmount savedAmount = ingredientNutrientAmountRepository.save(amount);
        return ingredientNutrientAmountMapper.mapToIngredientNutrientAmountReturnDto(savedAmount);
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public void delete(Long amountId) {
        IngredientNutrientAmount amount = ingredientNutrientAmountRepository.findById(amountId)
                        .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "IngredientNutrientAmount does not exist with given amountId: " + amountId));
        ingredientNutrientAmountRepository.delete(amount);
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public List<IngredientNutrientAmountReturnDto> updateList(
            Long ingredientId, 
            List<IngredientNutrientAmountCreateDto> updatedAmountCreateDtos) {

        List<IngredientNutrientAmount> amounts = ingredientNutrientAmountRepository.findByIngredientId(ingredientId)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "IngredientNutrientAmount does not exist with given ingredientId: " + ingredientId));

        for (int i = 0; i < Math.min(amounts.size(), updatedAmountCreateDtos.size()); i++) {
            update(amounts.get(i).getId(), updatedAmountCreateDtos.get(i));
        }

        if (amounts.size() > updatedAmountCreateDtos.size()) {
            for (int i = updatedAmountCreateDtos.size(); i < amounts.size(); i++) {
                delete(amounts.get(i).getId());
            }
        }

        if (amounts.size() < updatedAmountCreateDtos.size()) {
            for (int i = amounts.size(); i < updatedAmountCreateDtos.size(); i++) {
                create(updatedAmountCreateDtos.get(i));
            }
        }

        return getByIngredientId(ingredientId);
    }
}
