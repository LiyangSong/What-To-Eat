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

        return null;
        //DishIngredientAmount dishIngredientAmount = dishIngredientAmountMapper.mapToDishIngredientAmount(dishIngredientAmountCreateDto);
        //
        //dishIngredientAmountRepository.delete(dishIngredientAmount);
        //
        //String updatedDishName = updatedDishIngredientAmountCreateDto.getDishName();
        //Dish updatedDish = dishRepository.findByName(updatedDishName)
        //        .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "Dish does not exist with given dishName: " + updatedDishName));
        //
        //String updatedIngredientName = updatedDishIngredientAmountCreateDto.getIngredientName();
        //Ingredient updatedIngredient = ingredientRepository.findByName(updatedIngredientName)
        //        .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "Ingredient does not exist with given ingredientName: " + updatedIngredientName));
        //
        //dishIngredientAmount.setDish(updatedDish);
        //dishIngredientAmount.setIngredient(updatedIngredient);
        //dishIngredientAmount.setIngredientAmount(updatedDishIngredientAmountCreateDto.getIngredientAmount());
        //
        //DishIngredientAmount savedDishIngredientAmount = dishIngredientAmountRepository.save(dishIngredientAmount);
        //return dishIngredientAmountMapper.mapToDishIngredientAmountDto(savedDishIngredientAmount);
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
            List<Long> amountIds,
            List<DishIngredientAmountCreateDto> updatedAmountCreateDtos) {
        return null;
        //List<DishIngredientAmountCreateDto> savedUpdatedDtos = new ArrayList<>();
        //
        //for (DishIngredientAmountCreateDto updatedDto : updatedDishIngredientAmountCreateDtos) {
        //    Optional<DishIngredientAmountCreateDto> existingDtoOptional = dishIngredientAmountCreateDtos.stream()
        //            .filter(dto -> dto.equals(updatedDto))
        //            .findFirst();
        //
        //    if(existingDtoOptional.isPresent()) {
        //        DishIngredientAmountCreateDto existingDto = existingDtoOptional.get();
        //        savedUpdatedDtos.add(updateDishIngredientAmount(existingDto, updatedDto));
        //    } else {
        //        savedUpdatedDtos.add(createDishIngredientAmount(updatedDto));
        //    }
        //}
        //
        //for (DishIngredientAmountCreateDto existingDto : dishIngredientAmountCreateDtos) {
        //    if (updatedDishIngredientAmountCreateDtos.stream()
        //            .noneMatch(updatedDto -> updatedDto.equals(existingDto))) {
        //        deleteDishIngredientAmount(existingDto);
        //    }
        //}
        //
        //return savedUpdatedDtos;
    }
}
