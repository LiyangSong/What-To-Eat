package net.summer23project.wtebackend.service;

import net.summer23project.wtebackend.dto.UserDishMappingDto;

import java.util.List;

/**
 * @author Liyang
 */
public interface UserDishMappingService {
    UserDishMappingDto createUserDishMapping(UserDishMappingDto userDishMappingDto);
    List<UserDishMappingDto> getUserDishMappingDtosByUserName(String userName);
}
