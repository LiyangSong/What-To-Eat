package net.summer23project.wtebackend.service.impl;

import lombok.AllArgsConstructor;
import net.summer23project.wtebackend.dto.LoginDto;
import net.summer23project.wtebackend.dto.RegisterDto;
import net.summer23project.wtebackend.dto.UserDto;
import net.summer23project.wtebackend.entity.Role;
import net.summer23project.wtebackend.entity.User;
import net.summer23project.wtebackend.entity.UserRoleMapping;
import net.summer23project.wtebackend.exception.ApiException;
import net.summer23project.wtebackend.mapper.UserMapper;
import net.summer23project.wtebackend.repository.RoleRepository;
import net.summer23project.wtebackend.repository.UserRepository;
import net.summer23project.wtebackend.repository.UserRoleMappingRepository;
import net.summer23project.wtebackend.security.JwtTokenProvider;
import net.summer23project.wtebackend.service.AuthService;
import org.springframework.http.HttpStatus;
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
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleMappingRepository userRoleMappingRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserMapper userMapper;

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public UserDto register(RegisterDto registerDto) {
        User user = userMapper.mapToUser(registerDto);
        User savedUser = userRepository.save(user);

        UserRoleMapping userRoleMapping = new UserRoleMapping();

        Role role = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "Role id not exists!"));
        userRoleMapping.setUser(user);
        userRoleMapping.setRole(role);

        userRoleMappingRepository.save(userRoleMapping);

        return userMapper.mapToUserDto(savedUser);
    }

    @Override
    public String login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getNameOrEmail(),
                loginDto.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        //return "User " + loginDto.getNameOrEmail() + " logged in successfully!";
        return token;
    }
}
