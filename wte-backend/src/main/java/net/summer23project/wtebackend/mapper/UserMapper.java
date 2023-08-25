package net.summer23project.wtebackend.mapper;

import lombok.AllArgsConstructor;
import net.summer23project.wtebackend.dto.UserReturnDto;
import net.summer23project.wtebackend.entity.User;
import net.summer23project.wtebackend.exception.ApiException;
import net.summer23project.wtebackend.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

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
