package net.summer23project.wtebackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

/**
 * @author Liyang
 */
@Entity
@Table(name = "Dish_Ingredient_Amounts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DishIngredientAmount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dish_ingredient_amount_id")
    private Long id;

    @Column(name = "ingredient_amount", nullable = false)
    private double ingredientAmount;

    @ManyToOne
    @JoinColumn(
            name = "dish_id",
            nullable = false,
            foreignKey = @ForeignKey(
                    name = "fk_dishIngredientAmount_dish",
                    foreignKeyDefinition = "FOREIGN KEY (dish_id) REFERENCES dishes(dish_id) ON UPDATE CASCADE ON DELETE CASCADE"
            )
    )
    private Dish dish;

    @ManyToOne
    @JoinColumn(
            name = "ingredient_id",
            nullable = false,
            foreignKey = @ForeignKey(
                    name = "fk_dishIngredientAmount_ingredient",
                    foreignKeyDefinition = "FOREIGN KEY (ingredient_id) REFERENCES ingredients(ingredient_id) ON UPDATE CASCADE ON DELETE CASCADE"
            )
    )
    private Ingredient ingredient;

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        DishIngredientAmount dishIngredientAmount = (DishIngredientAmount) obj;
        if (dishIngredientAmount.getDish() == null || this.getDish() == null ||
                dishIngredientAmount.getIngredient() == null || this.getIngredient() == null) {
            return false;
        }

        return Objects.equals(dishIngredientAmount.getDish(), this.getDish()) &&
                Objects.equals(dishIngredientAmount.getIngredient(), this.getIngredient());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getDish(), this.getIngredient());
    }
}
