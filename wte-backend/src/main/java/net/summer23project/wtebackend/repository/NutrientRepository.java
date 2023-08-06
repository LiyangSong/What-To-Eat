package net.summer23project.wtebackend.repository;

import net.summer23project.wtebackend.entity.Nutrient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NutrientRepository extends JpaRepository<Nutrient, Long> {
}
