package net.summer23project.wtebackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Nutrients")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Nutrient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nutrient_id")
    private Long id;

    @Column(name = "nutrient_name")
    private String name;

    // many-to-many relationship between ingredients and nutrients
    @ManyToMany(mappedBy = "nutrients")
    private Set<Ingredient> ingredients = new HashSet<>();
}
