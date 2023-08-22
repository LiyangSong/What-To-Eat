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

    @ManyToOne
    @JoinColumn(name = "unit_id")
    private Unit unit;

    @OneToMany(mappedBy = "ingredient", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private Set<DishIngredientAmount> dishIngredientAmounts = new HashSet<>();

    @OneToMany(mappedBy = "ingredient", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private Set<IngredientNutrientAmount> ingredientNutrientAmounts = new HashSet<>();

    @OneToMany(mappedBy = "ingredient", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private Set<UserIngredientInventory> userIngredientInventories = new HashSet<>();

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Ingredient ingredient = (Ingredient) obj;
        if (ingredient.getName() == null || this.getName() == null) {
            return false;
        }

        return  Objects.equals(ingredient.getName(), this.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.getName());
    }
}
