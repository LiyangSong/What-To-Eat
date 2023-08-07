package net.summer23project.wtebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.summer23project.wtebackend.entity.Gender;
import net.summer23project.wtebackend.entity.Role;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String password;
    private String email;
    private Integer age;
    private Gender gender;
    private Role role;
}
