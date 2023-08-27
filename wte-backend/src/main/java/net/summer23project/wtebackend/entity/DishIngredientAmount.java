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
    @JoinColumn(name = "dish_id")
    private Dish dish;

    @ManyToOne
    @JoinColumn(name = "ingredient_id")
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
