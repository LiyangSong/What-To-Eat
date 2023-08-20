package net.summer23project.wtebackend.repository;

import net.summer23project.wtebackend.entity.DishIngredientAmount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Liyang
 */
public interface DishIngredientAmountRepository extends JpaRepository<DishIngredientAmount, Long> {
    Optional<List<DishIngredientAmount>> findByDishId(Long dishId);
}
