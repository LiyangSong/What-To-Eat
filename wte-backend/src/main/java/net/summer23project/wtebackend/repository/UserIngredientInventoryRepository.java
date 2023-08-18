package net.summer23project.wtebackend.repository;

import net.summer23project.wtebackend.entity.UserIngredientInventory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Liyang
 */
public interface UserIngredientInventoryRepository extends JpaRepository<UserIngredientInventory, Long> {
}
