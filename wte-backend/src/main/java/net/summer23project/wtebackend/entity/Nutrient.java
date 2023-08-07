package net.summer23project.wtebackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.summer23project.wtebackend.enums.UnitType;

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

    @Column(name = "nutrient_name", nullable = false, unique = true)
    private String name;

    @Column(name = "nutrient_unit", nullable = false)
    private UnitType unit;

    @OneToMany(mappedBy = "nutrient")
    private Set<IngredientNutrient> ingredientNutrients = new HashSet<>();
}
