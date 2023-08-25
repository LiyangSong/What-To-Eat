package net.summer23project.wtebackend.mapper;

import lombok.AllArgsConstructor;
import net.summer23project.wtebackend.dto.UserDishMappingDto;
import net.summer23project.wtebackend.entity.Dish;
import net.summer23project.wtebackend.entity.User;
import net.summer23project.wtebackend.entity.UserDishMapping;
import net.summer23project.wtebackend.exception.ApiException;
import net.summer23project.wtebackend.repository.DishRepository;
import net.summer23project.wtebackend.repository.UserDishMappingRepository;
import net.summer23project.wtebackend.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author Liyang
 */
@Component
public class UserDishMappingMapper {

    public UserDishMappingDto mapToUserDishMappingDto (
            UserDishMapping userDishMapping) {

        return new UserDishMappingDto(
                userDishMapping.getUser().getId(),
                userDishMapping.getDish().getId()
        );
    }
}
