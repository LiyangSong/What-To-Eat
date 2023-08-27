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
@Table(name = "Ingredient_Nutrient_Amounts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IngredientNutrientAmount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_nutrient_amount_id")
    private Long id;

    @Column(name = "nutrient_amount", nullable = false)
    private double nutrientAmount;

    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    @ManyToOne
    @JoinColumn(name = "nutrient_id")
    private Nutrient nutrient;

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        IngredientNutrientAmount ingredientNutrientAmount = (IngredientNutrientAmount) obj;
        if (ingredientNutrientAmount.getIngredient() == null || this.getIngredient() == null ||
                ingredientNutrientAmount.getNutrient() == null || this.getNutrient() == null) {
            return false;
        }

        return Objects.equals(ingredientNutrientAmount.getIngredient(), this.getIngredient()) &&
                Objects.equals(ingredientNutrientAmount.getNutrient(), this.getNutrient());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getIngredient(), this.getNutrient());
    }

}
