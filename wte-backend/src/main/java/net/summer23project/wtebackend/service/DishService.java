package net.summer23project.wtebackend.service;

import net.summer23project.wtebackend.dto.DishCreateDto;
import net.summer23project.wtebackend.dto.DishReturnDto;

import java.util.List;

/**
 * @author Yue, Liyang
 */
public interface DishService {
    DishReturnDto create(DishCreateDto dishCreateDto);
    DishReturnDto getById(Long dishId);
    List<DishReturnDto> getByName(String dishName);
    List<DishReturnDto> getAll();
    DishReturnDto update(Long dishId, String dishName);
    void delete(Long dishId);

}
