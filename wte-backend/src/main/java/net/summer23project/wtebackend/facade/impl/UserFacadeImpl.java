package net.summer23project.wtebackend.facade.impl;

import lombok.AllArgsConstructor;
import net.summer23project.wtebackend.dto.UserLoginDto;
import net.summer23project.wtebackend.dto.UserRegisterDto;
import net.summer23project.wtebackend.dto.UserReturnDto;
import net.summer23project.wtebackend.exception.ApiException;
import net.summer23project.wtebackend.security.JwtTokenProvider;
import net.summer23project.wtebackend.facade.UserFacade;
import net.summer23project.wtebackend.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Liyang
 */
@Service
@AllArgsConstructor
public class UserFacadeImpl implements UserFacade {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public UserReturnDto register(UserRegisterDto userRegisterDto) {
        return userService.create(userRegisterDto);
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public String login(UserLoginDto userLoginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userLoginDto.getNameOrEmail(),
                userLoginDto.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtTokenProvider.generateToken(authentication);
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public UserReturnDto getByName(String userName) {
        return null;
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public UserReturnDto update(UserRegisterDto userRegisterDto) {
        return null;
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public String delete(String userName) {
        return null;
    }
}
