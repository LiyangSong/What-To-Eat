package net.summer23project.wtebackend.service;

import net.summer23project.wtebackend.dto.UserDishMappingDto;

import java.util.List;

/**
 * @author Liyang
 */
public interface UserDishMappingService {
    UserDishMappingDto create(UserDishMappingDto userDishMappingDto);
    List<UserDishMappingDto> getByUserName(String userName);
    void delete(UserDishMappingDto userDishMappingDto);
    boolean exist(String userName, Long dishId);
}
