package net.summer23project.wtebackend.repository;

import net.summer23project.wtebackend.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Liyang
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findRoleById(Long id);
    Role findRoleByName(String name);
}
