package net.summer23project.wtebackend.repository;

import net.summer23project.wtebackend.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Liyang
 */
public interface DishRepository extends JpaRepository<Dish, Long> {
    Dish findDishById(Long id);
    Dish findDishByName(String name);

}
