package net.summer23project.wtebackend.facade;

import net.summer23project.wtebackend.dto.DishDetailsCreateDto;
import net.summer23project.wtebackend.dto.DishDetailsReturnDto;

import java.util.List;

public interface DishFacade {
    DishDetailsReturnDto createDish(DishDetailsCreateDto dishDetailsCreateDto, String userName);
    String addDish(Long dishId, String userName);
    DishDetailsReturnDto getDishById(Long dishId);
    List<DishDetailsReturnDto> getDishesByName(String dishName, String userName);
    void updateDish(Long dishId, DishDetailsCreateDto updatedDishDetailsCreateDto);
    void deleteDish(Long dishId);
}
