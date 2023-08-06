package net.summer23project.wtebackend.repository;

import net.summer23project.wtebackend.entity.IngredientNutrient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientNutrientRepository extends JpaRepository<IngredientNutrient, Long> {
}
