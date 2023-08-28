package net.summer23project.wtebackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Liyang
 */
@Entity
@Table(name = "Users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_name", nullable = false, unique = true)
    private String name;

    @Column(name = "user_password", nullable = false)
    private String password;

    @Column(name = "user_email", nullable = false, unique = true)
    private String email;

    @Column(name = "user_age")
    private Integer age;

    @ManyToOne
    @JoinColumn(
            name = "gender_id",
            nullable = false,
            foreignKey = @ForeignKey(
                    name = "fk_user_gender",
                    foreignKeyDefinition = "FOREIGN KEY (gender_id) REFERENCES genders(gender_id) ON UPDATE CASCADE"
            )
    )
    private Gender gender;

    @ManyToOne
    @JoinColumn(
            name = "role_id",
            nullable = false,
            foreignKey = @ForeignKey(
                    name = "fk_user_role",
                    foreignKeyDefinition = "FOREIGN KEY (role_id) REFERENCES roles(role_id) ON UPDATE CASCADE"
            )
    )
    private Role role;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private Set<UserDishMapping> userDishMappings = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private Set<UserIngredientInventory> userIngredientInventories = new HashSet<>();

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        User user = (User) obj;
        if (user.getId() == null || this.getId() == null) {
            return false;
        }

        return Objects.equals(user.getId(), this.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.getId());
    }
}
