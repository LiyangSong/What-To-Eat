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

    @Column(name = "ingredient_inventory", nullable = false)
    private double ingredientInventory;

    @ManyToOne
    @JoinColumn(
            name = "user_id",
            nullable = false,
            foreignKey = @ForeignKey(
                    name = "fk_userIngredientInventory_user",
                    foreignKeyDefinition = "FOREIGN KEY (user_id) REFERENCES users(user_id) ON UPDATE CASCADE ON DELETE CASCADE"
            )
    )
    private User user;

    @ManyToOne
    @JoinColumn(
            name = "ingredient_id",
            nullable = false,
            foreignKey = @ForeignKey(
                    name = "fk_userIngredientInventory_ingredient",
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

        UserIngredientInventory userIngredientInventory = (UserIngredientInventory) obj;
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
