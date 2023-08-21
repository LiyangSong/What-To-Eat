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
    private int ingredientAmount;

    @ManyToOne
    @JoinColumn(name = "dish_id")
    private Dish dish;

    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;
}
