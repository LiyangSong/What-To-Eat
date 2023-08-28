package net.summer23project.wtebackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Liyang
 */
@Entity
@Table(name = "Nutrients")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Nutrient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nutrient_id")
    private Long id;

    @Column(name = "nutrient_name", nullable = false, unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(
            name = "unit_id",
            nullable = false,
            foreignKey = @ForeignKey(
                    name = "fk_nutrient_unit",
                    foreignKeyDefinition = "FOREIGN KEY (unit_id) REFERENCES units(unit_id) ON UPDATE CASCADE"
            )
    )
    private Unit unit;

    @OneToMany(mappedBy = "nutrient", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private Set<IngredientNutrientAmount> ingredientNutrientAmounts = new HashSet<>();

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Nutrient nutrient = (Nutrient) obj;
        if (nutrient.getId() == null || this.getId() == null) {
            return false;
        }

        return  Objects.equals(nutrient.getId(), this.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.getId());
    }
}
