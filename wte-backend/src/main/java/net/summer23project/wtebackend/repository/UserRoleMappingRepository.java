package net.summer23project.wtebackend.repository;

import net.summer23project.wtebackend.entity.UserRoleMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Liyang
 */
public interface UserRoleMappingRepository extends JpaRepository<UserRoleMapping, Long> {
    Optional<List<UserRoleMapping>> findByUserId(Long userId);
    Boolean existsByUserIdAndRoleId(Long userId, Long roleId);
}
