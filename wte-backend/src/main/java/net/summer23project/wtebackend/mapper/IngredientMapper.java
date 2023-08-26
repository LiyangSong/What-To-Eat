package net.summer23project.wtebackend.mapper;

import lombok.AllArgsConstructor;
import net.summer23project.wtebackend.dto.IngredientReturnDto;
import net.summer23project.wtebackend.entity.Ingredient;
import org.springframework.stereotype.Component;

/**
 * @author Liyang
 */
@Component
@AllArgsConstructor
public class IngredientMapper {

    public IngredientReturnDto mapToIngredientReturnDto(Ingredient ingredient) {
        return new IngredientReturnDto(
                ingredient.getId(),
                ingredient.getName(),
                ingredient.getUnit().getName()
        );
    }
}
