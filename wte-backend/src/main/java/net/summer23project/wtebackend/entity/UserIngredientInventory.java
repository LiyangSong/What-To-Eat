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
    private double ingredientAmount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

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

        UserIngredientInventory userIngredientInventory = ( UserIngredientInventory) obj;
        if (userIngredientInventory.getUser() == null || this.getUser() == null ||
                userIngredientInventory.getIngredient() == null || this.getIngredient() == null) {
            return false;
        }

        return Objects.equals(userIngredientInventory.getUser(), this.getUser()) &&
                Objects.equals(userIngredientInventory.getIngredient(), this.getIngredient());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getUser(), this.getIngredient());
    }
}
