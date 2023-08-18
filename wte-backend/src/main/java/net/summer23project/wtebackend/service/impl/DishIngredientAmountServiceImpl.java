package net.summer23project.wtebackend.service.impl;

import lombok.AllArgsConstructor;
import net.summer23project.wtebackend.dto.DishIngredientAmountDto;
import net.summer23project.wtebackend.entity.DishIngredientAmount;
import net.summer23project.wtebackend.exception.ApiException;
import net.summer23project.wtebackend.repository.DishIngredientAmountRepository;
import net.summer23project.wtebackend.service.DishIngredientAmountService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Liyang
 */
@Service
@AllArgsConstructor
public class DishIngredientAmountServiceImpl implements DishIngredientAmountService {
    private DishIngredientAmountRepository dishIngredientAmountRepository;
    private ModelMapper modelMapper;

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public DishIngredientAmountDto createDishIngredientAmount(DishIngredientAmountDto dishIngredientAmountDto) {
        DishIngredientAmount dishIngredientAmount = modelMapper.map(dishIngredientAmountDto, DishIngredientAmount.class);
        DishIngredientAmount savedDishIngredientAmount = dishIngredientAmountRepository.save(dishIngredientAmount);
        return modelMapper.map(savedDishIngredientAmount, DishIngredientAmountDto.class);
    }
}
