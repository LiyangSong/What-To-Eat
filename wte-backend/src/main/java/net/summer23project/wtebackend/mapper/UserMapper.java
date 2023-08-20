package net.summer23project.wtebackend.mapper;

import net.summer23project.wtebackend.dto.UserDto;
import net.summer23project.wtebackend.entity.User;
import org.springframework.stereotype.Service;

/**
 * @author Liyang
 */
@Service
public class UserMapper {
    public UserDto mapToUserDto(User user) {
        return new UserDto(
                user.getName(),
                user.getEmail(),
                user.getAge(),
                user.getGender().getName()
        );
    }
}
