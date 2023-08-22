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
@Table(name = "Units")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "unit_id")
    private Long id;

    @Column(name = "unit_name", nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "unit", cascade = {CascadeType.MERGE})
    private Set<Ingredient> ingredients = new HashSet<>();

    @OneToMany(mappedBy = "unit", cascade = {CascadeType.MERGE})
    private Set<Nutrient> nutrients = new HashSet<>();

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Unit unit = (Unit) obj;
        if (unit.getName() == null || this.getName() == null) {
            return false;
        }

        return  Objects.equals(unit.getName(), this.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.getName());
    }
}
