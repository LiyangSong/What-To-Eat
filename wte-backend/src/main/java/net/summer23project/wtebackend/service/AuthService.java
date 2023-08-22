package net.summer23project.wtebackend.service;

import net.summer23project.wtebackend.dto.LoginDto;
import net.summer23project.wtebackend.dto.RegisterDto;
import net.summer23project.wtebackend.dto.UserDto;

/**
 * @author Liyang
 */
public interface AuthService {
    UserDto register(RegisterDto registerDto);
    String login(LoginDto loginDto);
}
