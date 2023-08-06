package net.summer23project.wtebackend.repository;

import net.summer23project.wtebackend.entity.DishIngredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishIngredientRepository extends JpaRepository<DishIngredient, Long> {
}
