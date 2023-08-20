package net.summer23project.wtebackend.repository;

import net.summer23project.wtebackend.entity.UserIngredientInventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Liyang
 */
public interface UserIngredientInventoryRepository extends JpaRepository<UserIngredientInventory, Long> {
    Optional<UserIngredientInventory> findByUserIdAndIngredientId(Long userId, Long ingredientId);
}
