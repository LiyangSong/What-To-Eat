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
@Table(name = "User_Dish_Mappings")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDishMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_dish_mapping_id")
    private Long id;

    @ManyToOne
    @JoinColumn(
            name = "user_id",
            nullable = false,
            foreignKey = @ForeignKey(
                    name = "fk_userDishMapping_user",
                    foreignKeyDefinition = "FOREIGN KEY (user_id) REFERENCES users(user_id) ON UPDATE CASCADE ON DELETE CASCADE"
            )
    )
    private User user;

    @ManyToOne
    @JoinColumn(
            name = "dish_id",
            nullable = false,
            foreignKey = @ForeignKey(
                    name = "fk_userDishMapping_dish",
                    foreignKeyDefinition = "FOREIGN KEY (dish_id) REFERENCES dishes(dish_id) ON UPDATE CASCADE ON DELETE CASCADE"
            )
    )
    private Dish dish;

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        UserDishMapping userDishMapping = ( UserDishMapping) obj;
        if (userDishMapping.getUser() == null || this.getUser() == null ||
                userDishMapping.getDish() == null || this.getDish() == null) {
            return false;
        }

        return Objects.equals(userDishMapping.getUser(), this.getUser()) &&
                Objects.equals(userDishMapping.getDish(), this.getDish());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getUser(), this.getDish());
    }
}
