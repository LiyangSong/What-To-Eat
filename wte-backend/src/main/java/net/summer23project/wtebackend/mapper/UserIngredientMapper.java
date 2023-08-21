package net.summer23project.wtebackend.mapper;

import lombok.AllArgsConstructor;
import net.summer23project.wtebackend.dto.UserIngredientInventoryDto;
import net.summer23project.wtebackend.entity.Ingredient;
import net.summer23project.wtebackend.entity.User;
import net.summer23project.wtebackend.entity.UserIngredientInventory;
import net.summer23project.wtebackend.exception.ApiException;
import net.summer23project.wtebackend.repository.IngredientRepository;
import net.summer23project.wtebackend.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * @author Liyang
 */
@Service
@AllArgsConstructor
public class UserIngredientMapper {
    private final UserRepository userRepository;
    private final IngredientRepository ingredientRepository;

    public UserIngredientInventoryDto mapToUserIngredientInventoryDto(
            UserIngredientInventory userIngredientInventory) {

        return new UserIngredientInventoryDto(
                userIngredientInventory.getUser().getName(),
                userIngredientInventory.getIngredient().getName(),
                userIngredientInventory.getIngredientAmount()
        );
    }

    public UserIngredientInventory mapToUserIngredientInventory(
            UserIngredientInventoryDto userIngredientInventoryDto) {

        UserIngredientInventory userIngredientInventory = new UserIngredientInventory();

        String userName = userIngredientInventoryDto.getUserName();
        User user = userRepository.findByName(userName)
                        .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "User does not exist with given userName: " + userName));
        userIngredientInventory.setUser(user);

        String ingredientName = userIngredientInventoryDto.getIngredientName();
        Ingredient ingredient = ingredientRepository.findByName(ingredientName)
                        .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "Ingredient does not exist with given ingredientName: " + ingredientName));
        userIngredientInventory.setIngredient(ingredient);

        userIngredientInventory.setIngredientAmount(userIngredientInventoryDto.getIngredientAmount());

        return userIngredientInventory;
    }
}
