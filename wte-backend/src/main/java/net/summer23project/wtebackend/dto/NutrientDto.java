package net.summer23project.wtebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Liyang
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NutrientDto {
    private Long id;
    private String name;
    private Long unitId;
    private Set<Long> ingredientNutrientIds = new HashSet<>();
}
