package net.summer23project.wtebackend.repository;

import net.summer23project.wtebackend.entity.IngredientNutrientAmount;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Liyang
 */
public interface IngredientNutrientAmountRepository extends JpaRepository<IngredientNutrientAmount, Long> {
}
