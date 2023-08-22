package net.summer23project.wtebackend.mapper;

import lombok.AllArgsConstructor;
import net.summer23project.wtebackend.dto.IngredientDto;
import net.summer23project.wtebackend.entity.Ingredient;
import net.summer23project.wtebackend.entity.Unit;
import net.summer23project.wtebackend.exception.ApiException;
import net.summer23project.wtebackend.repository.IngredientRepository;
import net.summer23project.wtebackend.repository.UnitRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * @author Liyang
 */
@Service
@AllArgsConstructor
public class IngredientMapper {
    private final UnitRepository unitRepository;
    private final IngredientRepository ingredientRepository;

    public IngredientDto mapToIngredientDto(Ingredient ingredient) {
        return new IngredientDto(
                ingredient.getName(),
                ingredient.getUnit().getName()
        );
    }

    public Ingredient mapToIngredient(IngredientDto ingredientDto) {
        String ingredientName = ingredientDto.getName();
        if(ingredientRepository.existsByName(ingredientName)) {
            throw new ApiException(HttpStatus.CONFLICT, "Ingredient already exists with given ingredientName: " + ingredientName);
        }

        Ingredient ingredient = new Ingredient();
        ingredient.setName(ingredientName);

        String unitName = ingredientDto.getUnitName();
        Unit unit = unitRepository.findByName(unitName)
                        .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "Unit does not exist with given unitName: " + unitName));
        ingredient.setUnit(unit);

        return ingredient;
    }
}
