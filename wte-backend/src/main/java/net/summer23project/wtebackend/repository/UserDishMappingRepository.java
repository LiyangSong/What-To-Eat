package net.summer23project.wtebackend.repository;

import net.summer23project.wtebackend.entity.UserDishMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Liyang
 */
public interface UserDishMappingRepository extends JpaRepository<UserDishMapping, Long> {
    Optional<List<UserDishMapping>> findByUserId(Long userId);
    Boolean existsByUserIdAndDishId(Long userId, Long dishId);
}
