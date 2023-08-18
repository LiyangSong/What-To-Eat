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
@Table(name = "Ingredients")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_id")
    private Long id;

    @Column(name = "ingredient_name", nullable = false, unique = true)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL )
    @JoinColumn(name = "unit_id")
    private Unit unit;

    @OneToMany(mappedBy = "ingredient")
    private Set<DishIngredientAmount> dishIngredientAmounts = new HashSet<>();

    @OneToMany(mappedBy = "ingredient")
    private Set<IngredientNutrientAmount> ingredientNutrientAmounts = new HashSet<>();

    @OneToMany(mappedBy = "ingredient")
    private Set<UserIngredientInventory> userIngredientInventories = new HashSet<>();

}
