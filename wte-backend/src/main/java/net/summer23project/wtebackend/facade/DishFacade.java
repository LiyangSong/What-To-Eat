package net.summer23project.wtebackend.facade;

import net.summer23project.wtebackend.dto.DishDetailsCreateDto;
import net.summer23project.wtebackend.dto.DishDetailsReturnDto;
import net.summer23project.wtebackend.dto.UserDishMappingDto;

import java.util.List;

/**
 * @author Liyang
 */
public interface DishFacade {
    DishDetailsReturnDto create(DishDetailsCreateDto dishDetailsCreateDto, String userName);
    DishDetailsReturnDto getById(Long dishId);
    List<DishDetailsReturnDto> getByName(String dishName, String userName);
    List<DishDetailsReturnDto> getAll(String userName);
    DishDetailsReturnDto update(
            Long dishId, DishDetailsCreateDto updatedDishDetailsCreateDto, String userName);
    String delete(Long dishId, String userName);
    UserDishMappingDto add(Long dishId, String userName);
    String remove(Long dishId, String userName);
}
