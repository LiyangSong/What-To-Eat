package net.summer23project.wtebackend.service;

import net.summer23project.wtebackend.dto.UserReturnDto;
import net.summer23project.wtebackend.dto.UserRegisterDto;

/**
 * @author Liyang
 */
public interface UserService {
    UserReturnDto create(UserRegisterDto userRegisterDto);
    UserReturnDto getById(Long userId);
    UserReturnDto getByName(String userName);
    UserReturnDto update(Long userId, UserRegisterDto updatedUserRegisterDto);
    void delete(Long userId);
}
