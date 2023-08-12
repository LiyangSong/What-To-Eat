package net.summer23project.wtebackend.service.impl;

import lombok.AllArgsConstructor;
import net.summer23project.wtebackend.dto.RegisterDto;
import net.summer23project.wtebackend.entity.Role;
import net.summer23project.wtebackend.entity.User;
import net.summer23project.wtebackend.exception.APIException;
import net.summer23project.wtebackend.repository.GenderRepository;
import net.summer23project.wtebackend.repository.RoleRepository;
import net.summer23project.wtebackend.repository.UserRepository;
import net.summer23project.wtebackend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.NameNotFoundException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Liyang
 */
@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private GenderRepository genderRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String register(RegisterDto registerDto) throws NameNotFoundException {
        if(userRepository.existsByName(registerDto.getName())) {
            throw new APIException(HttpStatus.BAD_REQUEST, "User name already exists!");
        }

        if(userRepository.existsByEmail(registerDto.getEmail())) {
            throw new APIException(HttpStatus.BAD_REQUEST, "User email already exists!");
        }

        User user = new User();
        user.setName(registerDto.getName());
        user.setEmail(registerDto.getEmail());
        user.setAge(registerDto.getAge());
        user.setGender(genderRepository.findById(registerDto.getGenderId())
                .orElseThrow(() -> new NameNotFoundException("Gender name not exists!")));
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role role = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new NameNotFoundException("Role name not exists!"));
        roles.add(role);
        user.setRoles(roles);

        userRepository.save(user);

        return "User " + user.getName() + " Registered Successfully!";
    }
}
