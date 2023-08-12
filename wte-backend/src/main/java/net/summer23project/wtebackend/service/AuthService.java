package net.summer23project.wtebackend.service;

import net.summer23project.wtebackend.dto.LoginDto;
import net.summer23project.wtebackend.dto.RegisterDto;

/**
 * @author Liyang
 */
public interface AuthService {
    String register(RegisterDto registerDto);
    String login(LoginDto loginDto);
}
