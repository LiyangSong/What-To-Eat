package net.summer23project.wtebackend.repository;

import net.summer23project.wtebackend.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Liyang
 */
public interface DishRepository extends JpaRepository<Dish, Long> {
    Optional<Dish> findByName(String name);
}
