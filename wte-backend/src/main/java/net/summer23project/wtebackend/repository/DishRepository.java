package net.summer23project.wtebackend.repository;

import net.summer23project.wtebackend.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishRepository extends JpaRepository<Dish, Long> {
}
