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
@Table(name = "User_Ingredient_Inventories")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserIngredientInventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_ingredient_inventory_id")
    private Long id;

    @Column(name = "ingredient_amount", nullable = false)
    private int ingredientAmount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;
}
