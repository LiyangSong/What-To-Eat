package net.summer23project.wtebackend.mapper;

import lombok.AllArgsConstructor;
import net.summer23project.wtebackend.dto.UserIngredientInventoryReturnDto;
import net.summer23project.wtebackend.entity.UserIngredientInventory;
import org.springframework.stereotype.Component;

/**
 * @author Liyang
 */
@Component
@AllArgsConstructor
public class UserIngredientInventoryMapper {

    public UserIngredientInventoryReturnDto mapToUserIngredientInventoryReturnDto(
            UserIngredientInventory userIngredientInventory) {

        return new UserIngredientInventoryReturnDto(
                userIngredientInventory.getId(),
                userIngredientInventory.getUser().getName(),
                userIngredientInventory.getIngredient().getId(),
                userIngredientInventory.getIngredient().getName(),
                userIngredientInventory.getIngredientInventory()
        );
    }
}
