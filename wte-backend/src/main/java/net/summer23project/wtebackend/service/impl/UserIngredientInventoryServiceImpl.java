package net.summer23project.wtebackend.service.impl;

import lombok.AllArgsConstructor;
import net.summer23project.wtebackend.dto.UserIngredientInventoryCreateDto;
import net.summer23project.wtebackend.dto.UserIngredientInventoryReturnDto;
import net.summer23project.wtebackend.entity.Ingredient;
import net.summer23project.wtebackend.entity.User;
import net.summer23project.wtebackend.entity.UserIngredientInventory;
import net.summer23project.wtebackend.exception.ApiException;
import net.summer23project.wtebackend.mapper.UserIngredientInventoryMapper;
import net.summer23project.wtebackend.repository.IngredientRepository;
import net.summer23project.wtebackend.repository.UserIngredientInventoryRepository;
import net.summer23project.wtebackend.repository.UserRepository;
import net.summer23project.wtebackend.service.UserIngredientInventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Liyang
 */
@Service
@AllArgsConstructor
public class UserIngredientInventoryServiceImpl implements UserIngredientInventoryService {
    private final UserRepository userRepository;
    private final IngredientRepository ingredientRepository;
    private final UserIngredientInventoryRepository userIngredientInventoryRepository;
    private final UserIngredientInventoryMapper userIngredientInventoryMapper;

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public UserIngredientInventoryReturnDto create(
            UserIngredientInventoryCreateDto inventoryCreateDto) {

        String userName = inventoryCreateDto.getUserName();
        User user = userRepository.findByName(userName)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "User does not exist with given userName: " + userName));

        Long ingredientId = inventoryCreateDto.getIngredientId();
        Ingredient ingredient = ingredientRepository.findById(ingredientId)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Ingredient does not exist with given ingredientId: " + ingredientId));

        UserIngredientInventory inventory = new UserIngredientInventory();
        inventory.setUser(user);
        inventory.setIngredient(ingredient);
        inventory.setIngredientInventory(inventoryCreateDto.getIngredientInventory());

        UserIngredientInventory savedInventory = userIngredientInventoryRepository.save(inventory);
        return userIngredientInventoryMapper.mapToUserIngredientInventoryReturnDto(savedInventory);
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public List<UserIngredientInventoryReturnDto> getByUserName(String userName) {
        Long userId = userRepository.findByName(userName)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "User does not exist with given userName: " + userName))
                .getId();
        List<UserIngredientInventory> inventories = userIngredientInventoryRepository.findByUserId(userId)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "UserIngredientInventory does not exist with given userId: " + userId));

        return inventories.stream()
                .map(userIngredientInventoryMapper::mapToUserIngredientInventoryReturnDto)
                .toList();
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public UserIngredientInventoryReturnDto getByUserNameAndIngredientId(
            String userName, Long ingredientId) {

        Long userId = userRepository.findByName(userName)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "User does not exist with given userName: " + userName))
                .getId();
        UserIngredientInventory inventory = userIngredientInventoryRepository.findByUserIdAndIngredientId(userId, ingredientId)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "UserIngredientInventory does not exist with given ingredientId: " + ingredientId));

        return userIngredientInventoryMapper.mapToUserIngredientInventoryReturnDto(inventory);
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public UserIngredientInventoryReturnDto update(
            Long inventoryId,
            UserIngredientInventoryCreateDto updatedInventoryCreateDto) {

        String userName = updatedInventoryCreateDto.getUserName();
        User user = userRepository.findByName(userName)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "User does not exist with given userName: " + userName));

        Long ingredientId = updatedInventoryCreateDto.getIngredientId();
        Ingredient ingredient = ingredientRepository.findById(ingredientId)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Ingredient does not exist with given ingredientId: " + ingredientId));

        UserIngredientInventory inventory = userIngredientInventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "UserIngredientInventory does not exist with given inventoryId: " + inventoryId));
        inventory.setUser(user);
        inventory.setIngredient(ingredient);
        inventory.setIngredientInventory(updatedInventoryCreateDto.getIngredientInventory());

        UserIngredientInventory savedInventory = userIngredientInventoryRepository.save(inventory);
        return userIngredientInventoryMapper.mapToUserIngredientInventoryReturnDto(savedInventory);
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public void delete(Long inventoryId) {
        UserIngredientInventory inventory = userIngredientInventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "UserIngredientInventory does not exist with given inventoryId: " + inventoryId));
        userIngredientInventoryRepository.delete(inventory);
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public boolean exist(String userName, Long ingredientId) {
        Long userId = userRepository.findByName(userName)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "User does not exist with given userName: " + userName))
                .getId();
        return userIngredientInventoryRepository.existsByUserIdAndIngredientId(
                userId, ingredientId
        );
    }
}
