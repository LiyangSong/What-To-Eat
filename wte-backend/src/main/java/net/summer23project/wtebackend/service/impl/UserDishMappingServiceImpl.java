package net.summer23project.wtebackend.service.impl;

import lombok.AllArgsConstructor;
import net.summer23project.wtebackend.dto.UserDishMappingDto;
import net.summer23project.wtebackend.entity.UserDishMapping;
import net.summer23project.wtebackend.exception.ApiException;
import net.summer23project.wtebackend.mapper.UserDishMappingMapper;
import net.summer23project.wtebackend.repository.UserDishMappingRepository;
import net.summer23project.wtebackend.repository.UserRepository;
import net.summer23project.wtebackend.service.UserDishMappingService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Liyang
 */
@Service
@AllArgsConstructor
public class UserDishMappingServiceImpl implements UserDishMappingService {
    private final UserDishMappingRepository userDishMappingRepository;
    private final UserRepository userRepository;
    private final UserDishMappingMapper userDishMappingMapper;

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public UserDishMappingDto createUserDishMapping(UserDishMappingDto userDishMappingDto) {
        UserDishMapping userDishMapping = userDishMappingMapper.mapToUserDishMapping(userDishMappingDto);
        UserDishMapping savedUserDishMapping = userDishMappingRepository.save(userDishMapping);
        return userDishMappingMapper.mapToUserDishMappingDto(savedUserDishMapping);
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public List<UserDishMappingDto> getUserDishMappingDtosByUserName(
            String userName) {

        Long userId = userRepository.findByName(userName)
                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "User does not exist with given userName: " + userName))
                .getId();

        List<UserDishMapping> userDishMappings = userDishMappingRepository.findByUserId(userId)
                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "UserDishMapping does not exist with given userId: " + userId));

        return userDishMappings.stream().map(
                userDishMappingMapper::mapToUserDishMappingDto
        ).collect(Collectors.toList());
    }
}
