package net.summer23project.wtebackend.service;

import net.summer23project.wtebackend.dto.LoginDto;
import net.summer23project.wtebackend.dto.RegisterDto;
import net.summer23project.wtebackend.dto.UserDto;
import net.summer23project.wtebackend.entity.User;

/**
 * @author Liyang
 */
public interface UserService {
    UserDto mapUserToUserDto(User user);
    UserDto getUserById(Long id);
    UserDto getUserByName(String name);
    UserDto getUserByEmail(String email);
    UserDto registerUser(RegisterDto registerDto);
    UserDto loginUser(LoginDto loginDto);
    boolean passwordMatches(User user, String password);
}
