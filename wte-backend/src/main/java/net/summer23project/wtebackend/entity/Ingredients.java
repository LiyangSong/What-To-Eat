package net.summer23project.wtebackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.summer23project.wtebackend.enums.UnitType;

import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Ingredients")
public class Ingredients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_id")
    private Long id;

    @Column(name = "ingredient_name")
    private String name;

    @Column(name = "ingredient_unit")
    private UnitType unit;

    @OneToMany(mappedBy = "ingredient")
    private Set<DishIngredient> dishIngredients = new HashSet<>();

    @OneToMany(mappedBy = "ingredient")
    private Set<IngredientNutrient> ingredientNutrients = new HashSet<>();
}

