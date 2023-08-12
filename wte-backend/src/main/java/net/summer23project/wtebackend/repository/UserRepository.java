package net.summer23project.wtebackend.repository;

import net.summer23project.wtebackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * @author Liyang
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);
    Boolean existsByName(String name);
    Optional<User> findByNameOrEmail(String name, String Email);
    Boolean existsByEmail(String email);
}
