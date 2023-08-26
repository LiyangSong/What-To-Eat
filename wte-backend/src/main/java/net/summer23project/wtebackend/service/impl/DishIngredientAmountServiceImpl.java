package net.summer23project.wtebackend.service.impl;

import lombok.AllArgsConstructor;
import net.summer23project.wtebackend.dto.DishIngredientAmountCreateDto;
import net.summer23project.wtebackend.dto.DishIngredientAmountReturnDto;
import net.summer23project.wtebackend.entity.Dish;
import net.summer23project.wtebackend.entity.DishIngredientAmount;
import net.summer23project.wtebackend.entity.Ingredient;
import net.summer23project.wtebackend.exception.ApiException;
import net.summer23project.wtebackend.mapper.DishIngredientAmountMapper;
import net.summer23project.wtebackend.repository.DishIngredientAmountRepository;
import net.summer23project.wtebackend.repository.DishRepository;
import net.summer23project.wtebackend.repository.IngredientRepository;
import net.summer23project.wtebackend.service.DishIngredientAmountService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Liyang
 */
@Service
@AllArgsConstructor
public class DishIngredientAmountServiceImpl implements DishIngredientAmountService {
    private final DishRepository dishRepository;
    private final IngredientRepository ingredientRepository;
    private final DishIngredientAmountRepository dishIngredientAmountRepository;
    private final DishIngredientAmountMapper dishIngredientAmountMapper;

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public DishIngredientAmountReturnDto create(DishIngredientAmountCreateDto amountCreateDto) {
        Long dishId = amountCreateDto.getDishId();
        Dish dish = dishRepository.findById(dishId)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Dish does not exist with given dishId: " + dishId));

        Long ingredientId = amountCreateDto.getIngredientId();
        Ingredient ingredient = ingredientRepository.findById(ingredientId)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Ingredient does not exist with ingredientId: " + ingredientId));

        DishIngredientAmount amount = new DishIngredientAmount();
        amount.setDish(dish);
        amount.setIngredient(ingredient);
        amount.setIngredientAmount(amountCreateDto.getIngredientAmount());

        DishIngredientAmount savedAmount = dishIngredientAmountRepository.save(amount);
        return dishIngredientAmountMapper.mapToDishIngredientAmountReturnDto(savedAmount);
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public List<DishIngredientAmountReturnDto> getByDishId(Long dishId) {
        List<DishIngredientAmount> amounts = dishIngredientAmountRepository.findByDishId(dishId)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "DishIngredientAmount does not exist with given dishId: " + dishId));
        return amounts.stream()
                .map(dishIngredientAmountMapper::mapToDishIngredientAmountReturnDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public DishIngredientAmountReturnDto update(
            Long amountId,
            DishIngredientAmountCreateDto updatedAmountCreateDto) {

        DishIngredientAmount amount = dishIngredientAmountRepository.findById(amountId)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "DishIngredientAmount does not exist with given amountId: " + amountId));

        Long dishId = updatedAmountCreateDto.getDishId();
        Dish dish = dishRepository.findById(dishId)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Dish does not exist with given dishId: " + dishId));

        Long ingredientId = updatedAmountCreateDto.getIngredientId();
        Ingredient ingredient = ingredientRepository.findById(ingredientId)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Ingredient does not exist with given ingredientId: " + ingredientId));

        amount.setDish(dish);
        amount.setIngredient(ingredient);
        amount.setIngredientAmount(updatedAmountCreateDto.getIngredientAmount());

        DishIngredientAmount savedAmount = dishIngredientAmountRepository.save(amount);
        return dishIngredientAmountMapper.mapToDishIngredientAmountReturnDto(savedAmount);
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public void delete(Long amountId) {
        DishIngredientAmount amount = dishIngredientAmountRepository.findById(amountId)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "DishIngredientAmount does not exist with given amountId: " + amountId));
        dishIngredientAmountRepository.delete(amount);
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public List<DishIngredientAmountReturnDto> updateList(
            Long dishId,
            List<DishIngredientAmountCreateDto> updatedAmountCreateDtos) {

        List<DishIngredientAmount> amounts = dishIngredientAmountRepository.findByDishId(dishId)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "DishIngredientAmount does not exist with given dishId: " + dishId));

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

        return getByDishId(dishId);
    }
}
