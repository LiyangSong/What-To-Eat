package net.summer23project.wtebackend.repository;

import net.summer23project.wtebackend.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Liyang
 */
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    Ingredient findIngredientById(Long id);
    Ingredient findIngredientByName(String name);
}
