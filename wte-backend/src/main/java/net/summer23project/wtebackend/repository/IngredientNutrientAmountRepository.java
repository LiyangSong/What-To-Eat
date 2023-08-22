package net.summer23project.wtebackend.repository;

import net.summer23project.wtebackend.entity.IngredientNutrientAmount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Liyang
 */
public interface IngredientNutrientAmountRepository extends JpaRepository<IngredientNutrientAmount, Long> {
    Optional<List<IngredientNutrientAmount>> findByIngredientId(Long ingredientId);
    Boolean existsByIngredientIdAndNutrientId(Long ingredientId, Long nutrientId);
}
