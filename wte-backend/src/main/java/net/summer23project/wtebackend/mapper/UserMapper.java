package net.summer23project.wtebackend.mapper;

import net.summer23project.wtebackend.dto.UserReturnDto;
import net.summer23project.wtebackend.entity.User;
import org.springframework.stereotype.Component;

/**
 * @author Liyang
 */
@Component
public class UserMapper {

    public UserReturnDto mapToUserReturnDto(User user) {
        return new UserReturnDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getAge(),
                user.getGender().getName()
        );
    }
}
