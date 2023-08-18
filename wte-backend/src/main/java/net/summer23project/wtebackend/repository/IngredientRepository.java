package net.summer23project.wtebackend.repository;

import net.summer23project.wtebackend.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Liyang
 */
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    Optional<Ingredient> findByName(String name);
}
