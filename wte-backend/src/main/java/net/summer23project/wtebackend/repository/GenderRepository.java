package net.summer23project.wtebackend.repository;

import net.summer23project.wtebackend.entity.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Liyang
 */
public interface GenderRepository extends JpaRepository<Gender, Long> {
}
