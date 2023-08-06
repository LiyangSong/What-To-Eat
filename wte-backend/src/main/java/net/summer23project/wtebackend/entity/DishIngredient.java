package net.summer23project.wtebackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Dish_Ingredient_Content")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DishIngredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dish_ingredient_id")
    private Long id;

    @Column(name = "ingredient_number")
    private int ingredientNumber;

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "dish_id")
    private Dish dish;

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;
}
