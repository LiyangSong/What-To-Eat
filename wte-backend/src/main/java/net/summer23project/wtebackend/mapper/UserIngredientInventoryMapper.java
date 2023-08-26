package net.summer23project.wtebackend.mapper;

import lombok.AllArgsConstructor;
import net.summer23project.wtebackend.dto.UserIngredientInventoryReturnDto;
import net.summer23project.wtebackend.entity.Ingredient;
import net.summer23project.wtebackend.entity.User;
import net.summer23project.wtebackend.entity.UserIngredientInventory;
import net.summer23project.wtebackend.exception.ApiException;
import net.summer23project.wtebackend.repository.IngredientRepository;
import net.summer23project.wtebackend.repository.UserIngredientInventoryRepository;
import net.summer23project.wtebackend.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * @author Liyang
 */
@Component
@AllArgsConstructor
public class UserIngredientInventoryMapper {
    //private final UserRepository userRepository;
    //private final IngredientRepository ingredientRepository;
    //private final UserIngredientInventoryRepository userIngredientInventoryRepository;
    //
    //public UserIngredientInventoryReturnDto mapToUserIngredientInventoryDto(
    //        UserIngredientInventory userIngredientInventory) {
    //
    //    return new UserIngredientInventoryReturnDto(
    //            userIngredientInventory.getUser().getName(),
    //            userIngredientInventory.getIngredient().getName(),
    //            userIngredientInventory.getIngredientAmount()
    //    );
    //}
    //
    //public UserIngredientInventory mapToUserIngredientInventory(
    //        UserIngredientInventoryReturnDto userIngredientInventoryReturnDto) {
    //
    //    String userName = userIngredientInventoryReturnDto.getUserName();
    //    User user = userRepository.findByName(userName)
    //            .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "User does not exist with given userName: " + userName));
    //    Long userId = user.getId();
    //
    //    String ingredientName = userIngredientInventoryReturnDto.getIngredientName();
    //    Ingredient ingredient = ingredientRepository.findByName(ingredientName)
    //            .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "Ingredient does not exist with given ingredientName: " + ingredientName));
    //    Long ingredientId = ingredient.getId();
    //
    //    if (userIngredientInventoryRepository.existsByUserIdAndIngredientId(userId, ingredientId)) {
    //        throw new ApiException(HttpStatus.CONFLICT, "UserUserInventory already exists with given userName: " + userName + " and ingredientName: " + ingredientName);
    //    }
    //
    //    UserIngredientInventory userIngredientInventory = new UserIngredientInventory();
    //    userIngredientInventory.setUser(user);
    //    userIngredientInventory.setIngredient(ingredient);
    //    userIngredientInventory.setIngredientAmount(userIngredientInventoryReturnDto.getIngredientAmount());
    //
    //    return userIngredientInventory;
    //}
}
