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
@Table(name = "Dishes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dish_id")
    private Long id;

    @Column(name = "dish_name", nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "dish")
    private Set<DishIngredientAmount> dishIngredientAmounts = new HashSet<>();

    @ManyToMany(mappedBy = "dishes")
    private Set<User> users = new HashSet<>();
}
