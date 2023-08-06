package net.summer23project.wtebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.summer23project.wtebackend.entity.Dish;
import net.summer23project.wtebackend.entity.Nutrient;
import net.summer23project.wtebackend.enums.UnitType;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IngredientDto {
    private Long id;
    private String name;
    private UnitType unit;
    private Set<Nutrient> nutrients;
    private Set<Dish> dishes;
}
