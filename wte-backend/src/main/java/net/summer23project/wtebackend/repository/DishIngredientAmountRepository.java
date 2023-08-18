package net.summer23project.wtebackend.repository;

import net.summer23project.wtebackend.entity.DishIngredientAmount;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Liyang
 */
public interface DishIngredientAmountRepository extends JpaRepository<DishIngredientAmount, Long> {
}
