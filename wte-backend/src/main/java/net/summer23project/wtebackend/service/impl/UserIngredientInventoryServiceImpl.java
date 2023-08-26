package net.summer23project.wtebackend.service.impl;

import net.summer23project.wtebackend.dto.UserIngredientInventoryCreateDto;
import net.summer23project.wtebackend.dto.UserIngredientInventoryReturnDto;
import net.summer23project.wtebackend.service.UserIngredientInventoryService;

import java.util.List;

/**
 * @author Liyang
 */
public class UserIngredientInventoryServiceImpl implements UserIngredientInventoryService {

    @Override
    public UserIngredientInventoryReturnDto create(UserIngredientInventoryCreateDto inventoryCreateDto) {
        return null;
    }

    @Override
    public List<UserIngredientInventoryReturnDto> getByUserName(String userName) {
        return null;
    }

    @Override
    public UserIngredientInventoryReturnDto update(Long inventoryId, UserIngredientInventoryCreateDto updatedInventoryCreateDto) {
        return null;
    }

    @Override
    public void delete(Long inventoryId) {

    }

    @Override
    public List<UserIngredientInventoryReturnDto> updateList(String userName, List<UserIngredientInventoryCreateDto> updatedInventoryCreateDtos) {
        return null;
    }
}
