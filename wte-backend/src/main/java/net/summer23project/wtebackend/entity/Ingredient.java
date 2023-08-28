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

    @Column(name = "ingredient_name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(
            name = "unit_id",
            nullable = false,
            foreignKey = @ForeignKey(
                    name = "fk_ingredient_unit",
                    foreignKeyDefinition = "FOREIGN KEY (unit_id) REFERENCES units(unit_id) ON UPDATE CASCADE"
            )
    )
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
        if (ingredient.getId() == null || this.getId() == null) {
            return false;
        }

        return  Objects.equals(ingredient.getId(), this.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.getId());
    }
}
