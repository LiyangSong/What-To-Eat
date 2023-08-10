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
@Table(name = "Units")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "unit_id")
    private Long id;

    @Column(name = "unit_name", nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "unit")
    private Set<Ingredient> ingredients = new HashSet<>();

    @OneToMany(mappedBy = "unit")
    private Set<Nutrient> nutrients = new HashSet<>();
}
