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
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "dish_id")
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
