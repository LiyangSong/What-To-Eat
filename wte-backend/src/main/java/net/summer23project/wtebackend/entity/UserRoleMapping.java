package net.summer23project.wtebackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

/**
 * @author Liyang
 */
@Entity
@Table(name = "User_Role_Mappings")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_role_mapping_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        UserRoleMapping userRoleMapping = ( UserRoleMapping) obj;
        if (userRoleMapping.getUser() == null || this.getUser() == null ||
                userRoleMapping.getRole() == null || this.getRole() == null) {
            return false;
        }

        return Objects.equals(userRoleMapping.getUser(), this.getUser()) &&
                Objects.equals(userRoleMapping.getRole(), this.getRole());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getUser(), this.getRole());
    }
}
