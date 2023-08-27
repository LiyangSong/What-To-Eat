package net.summer23project.wtebackend.mapper;

import net.summer23project.wtebackend.dto.UserDishMappingDto;
import net.summer23project.wtebackend.entity.UserDishMapping;
import org.springframework.stereotype.Component;

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
