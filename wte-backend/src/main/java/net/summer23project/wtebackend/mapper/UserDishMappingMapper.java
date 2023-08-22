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
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDishMappingMapper {
    private final UserRepository userRepository;
    private final DishRepository dishRepository;
    private final UserDishMappingRepository userDishMappingRepository;

    public UserDishMappingDto mapToUserDishMappingDto (
            UserDishMapping userDishMapping) {

        return new UserDishMappingDto(
                userDishMapping.getUser().getName(),
                userDishMapping.getDish().getName()
        );
    }

    public UserDishMapping mapToUserDishMapping(
            UserDishMappingDto userDishMappingDto) {

        String userName = userDishMappingDto.getUserName();
        User user = userRepository.findByName(userName)
                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "User does not exist with given userName: " + userName));
        Long userId = user.getId();

        String dishName = userDishMappingDto.getDishName();
        Dish dish = dishRepository.findByName(dishName)
                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "Dish does not exist with given dishName: " + dishName));
        Long dishId = dish.getId();

        if (userDishMappingRepository.existsByUserIdAndDishId(userId, dishId)) {
            throw new ApiException(HttpStatus.CONFLICT, "UserDishMapping already exists with given userName: " + userName + " and dishName: " + dishName);
        }

        UserDishMapping userDishMapping = new UserDishMapping();
        userDishMapping.setUser(user);
        userDishMapping.setDish(dish);

        return userDishMapping;
    }
}
