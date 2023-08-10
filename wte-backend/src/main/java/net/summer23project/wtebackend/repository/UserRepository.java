package net.summer23project.wtebackend.repository;

import net.summer23project.wtebackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * @author Liyang
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserById(Long id);
    User findUserByName(String name);
    User findUserByEmail(String email);
}
