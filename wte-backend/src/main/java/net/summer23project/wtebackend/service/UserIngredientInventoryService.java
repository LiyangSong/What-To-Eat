package net.summer23project.wtebackend.service;

import net.summer23project.wtebackend.dto.UserIngredientInventoryCreateDto;
import net.summer23project.wtebackend.dto.UserIngredientInventoryReturnDto;

import java.util.List;

/**
 * @author Liyang
 */
public interface UserIngredientInventoryService {
    UserIngredientInventoryReturnDto create(
            UserIngredientInventoryCreateDto inventoryCreateDto);
    List<UserIngredientInventoryReturnDto> getByUserName(String userName);
    UserIngredientInventoryReturnDto getByUserNameAndIngredientId(
            String userName, Long ingredientId);
    UserIngredientInventoryReturnDto update(
            Long inventoryId,
            UserIngredientInventoryCreateDto updatedInventoryCreateDto);
    void delete(Long inventoryId);
    boolean exist(String userName, Long ingredientId);
}
