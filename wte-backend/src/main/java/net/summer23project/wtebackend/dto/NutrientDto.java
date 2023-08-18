package net.summer23project.wtebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}
