package net.summer23project.wtebackend.service.impl;

import lombok.AllArgsConstructor;
import net.summer23project.wtebackend.dto.LoginDto;
import net.summer23project.wtebackend.dto.RegisterDto;
import net.summer23project.wtebackend.entity.Role;
import net.summer23project.wtebackend.entity.User;
import net.summer23project.wtebackend.exception.ApiException;
import net.summer23project.wtebackend.repository.GenderRepository;
import net.summer23project.wtebackend.repository.RoleRepository;
import net.summer23project.wtebackend.repository.UserRepository;
import net.summer23project.wtebackend.security.JwtTokenProvider;
import net.summer23project.wtebackend.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Liyang
 */
@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private GenderRepository genderRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;

    @Override
    @Transactional
    public String register(RegisterDto registerDto) {
        if(userRepository.existsByName(registerDto.getName())) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "User name already exists!");
        }

        if(userRepository.existsByEmail(registerDto.getEmail())) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "User email already exists!");
        }

        User user = new User();
        user.setName(registerDto.getName());
        user.setEmail(registerDto.getEmail());
        user.setAge(registerDto.getAge());
        user.setGender(genderRepository.findById(registerDto.getGenderId())
                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "Gender id not exists!")));
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role role = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "Role id not exists!"));
        roles.add(role);
        user.setRoles(roles);

        userRepository.save(user);

        return "User " + user.getName() + " Registered Successfully!";
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
