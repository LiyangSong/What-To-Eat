package net.summer23project.wtebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.summer23project.wtebackend.entity.Ingredient;
import net.summer23project.wtebackend.enums.UnitType;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NutrientDto {
    private Long id;
    private String name;
    private UnitType unit;
    private Set<Ingredient> ingredients;
}
