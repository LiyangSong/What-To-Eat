package net.summer23project.wtebackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Ingredient_Nutrient_Content")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IngredientNutrient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_nutrient_id")
    private Long id;

    @Column(name = "nutrient_number", nullable = false)
    private int nutrientNumber;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL )
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL )
    @JoinColumn(name = "nutrient_id")
    private Nutrient nutrient;
}
