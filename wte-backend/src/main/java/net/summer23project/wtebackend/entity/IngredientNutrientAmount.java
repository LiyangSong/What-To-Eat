package net.summer23project.wtebackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private int nutrientAmount;

    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    @ManyToOne
    @JoinColumn(name = "nutrient_id")
    private Nutrient nutrient;
}
