package net.summer23project.wtebackend.repository;

import net.summer23project.wtebackend.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
}
