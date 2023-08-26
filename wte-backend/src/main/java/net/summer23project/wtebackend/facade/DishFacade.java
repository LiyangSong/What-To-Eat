package net.summer23project.wtebackend.facade;

import net.summer23project.wtebackend.dto.DishDetailsCreateDto;
import net.summer23project.wtebackend.dto.DishDetailsReturnDto;
import net.summer23project.wtebackend.dto.UserDishMappingDto;

import java.util.List;

/**
 * @author Liyang
 */
public interface DishFacade {
    DishDetailsReturnDto createDish(DishDetailsCreateDto dishDetailsCreateDto, String userName);
    UserDishMappingDto addDish(Long dishId, String userName);
    DishDetailsReturnDto getDishById(Long dishId);
    List<DishDetailsReturnDto> getDishesByName(String dishName, String userName);
    List<DishDetailsReturnDto> getAllDishes(String userName);
    DishDetailsReturnDto updateDish(
            Long dishId, DishDetailsCreateDto updatedDishDetailsCreateDto, String userName);
    String deleteDish(Long dishId, String userName);
}
