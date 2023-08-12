package net.summer23project.wtebackend.repository;

import net.summer23project.wtebackend.entity.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Liyang
 */
public interface GenderRepository extends JpaRepository<Gender, Long> {
    Optional<Gender> findById(Long id);
    Optional<Gender> findByName(String name);
}
