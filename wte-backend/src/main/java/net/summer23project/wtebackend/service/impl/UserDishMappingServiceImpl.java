package net.summer23project.wtebackend.service.impl;

import lombok.AllArgsConstructor;
import net.summer23project.wtebackend.dto.UserDishMappingDto;
import net.summer23project.wtebackend.entity.Dish;
import net.summer23project.wtebackend.entity.User;
import net.summer23project.wtebackend.entity.UserDishMapping;
import net.summer23project.wtebackend.exception.ApiException;
import net.summer23project.wtebackend.mapper.UserDishMappingMapper;
import net.summer23project.wtebackend.repository.DishRepository;
import net.summer23project.wtebackend.repository.UserDishMappingRepository;
import net.summer23project.wtebackend.repository.UserRepository;
import net.summer23project.wtebackend.service.UserDishMappingService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Liyang
 */
@Service
@AllArgsConstructor
public class UserDishMappingServiceImpl implements UserDishMappingService {
    private final UserDishMappingRepository userDishMappingRepository;
    private final UserRepository userRepository;
    private final DishRepository dishRepository;
    private final UserDishMappingMapper userDishMappingMapper;

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public UserDishMappingDto create(UserDishMappingDto userDishMappingDto) {

        Long userId = userDishMappingDto.getUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "User does not exist with given userId: " + userId));

        Long dishId = userDishMappingDto.getDishId();
        Dish dish = dishRepository.findById(dishId)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Dish does not exist with given dishId: " + dishId));

        UserDishMapping mapping = new UserDishMapping();
        mapping.setUser(user);
        mapping.setDish(dish);

        UserDishMapping savedMapping = userDishMappingRepository.save(mapping);
        return userDishMappingMapper.mapToUserDishMappingDto(savedMapping);
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public List<UserDishMappingDto> getByUserName(String userName) {

        Long userId = userRepository.findByName(userName)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "User does not exist with given userName: " + userName))
                .getId();

        List<UserDishMapping> userDishMappings = userDishMappingRepository.findByUserId(userId)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "UserDishMapping does not exist with given userId: " + userId));

        return userDishMappings.stream().map(
                userDishMappingMapper::mapToUserDishMappingDto
        ).toList();
    }
}
