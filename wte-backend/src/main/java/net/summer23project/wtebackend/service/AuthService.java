package net.summer23project.wtebackend.service;

import net.summer23project.wtebackend.dto.RegisterDto;

import javax.naming.NameNotFoundException;

/**
 * @author Liyang
 */
public interface AuthService {
    String register(RegisterDto registerDto) throws NameNotFoundException;
}
