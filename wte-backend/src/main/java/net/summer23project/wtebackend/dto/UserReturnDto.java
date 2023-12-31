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
public class UserReturnDto {
    private Long id;
    private String name;
    private String email;
    private Integer age;
    private String genderName;
}
