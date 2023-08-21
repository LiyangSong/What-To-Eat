package net.summer23project.wtebackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
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
    @JoinColumn(name = "gender_id")
    private Gender gender;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private Set<UserDishMapping> userDishMappings = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private Set<UserRoleMapping> userRoleMappings = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private Set<UserIngredientInventory> userIngredientInventories = new HashSet<>();
}
