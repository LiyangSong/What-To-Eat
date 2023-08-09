package net.summer23project.wtebackend.service.impl;

import net.summer23project.wtebackend.dto.LoginDto;
import net.summer23project.wtebackend.dto.RegisterDto;
import net.summer23project.wtebackend.dto.UserDto;
import net.summer23project.wtebackend.entity.Role;
import net.summer23project.wtebackend.entity.User;
import net.summer23project.wtebackend.repository.GenderRepository;
import net.summer23project.wtebackend.repository.RoleRepository;
import net.summer23project.wtebackend.repository.UserRepository;
import net.summer23project.wtebackend.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Liyang
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GenderRepository genderRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto mapUserToUserDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findUserById(id);
        return mapUserToUserDto(user);
    }

    @Override
    public UserDto getUserByName(String name) {
        User user = userRepository.findUserByName(name);
        return mapUserToUserDto(user);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepository.findUserByEmail(email);
        return mapUserToUserDto(user);
    }

    @Override
    public UserDto registerUser(RegisterDto registerDto){
        User user = new User();
        user.setName(registerDto.getName());
        user.setEmail(registerDto.getEmail());
        user.setAge(registerDto.getAge());
        user.setGender(genderRepository.findGenderById(registerDto.getGenderId()));
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Role defaultRole = roleRepository.findRoleByName("User");
        user.setRole(defaultRole);

        User savedUser = userRepository.save(user);
        return mapUserToUserDto(savedUser);
    }

    @Override
    public UserDto loginUser(LoginDto loginDto) {
        return null;
    }

    @Override
    public boolean passwordMatches(User user, String password) {
        return false;
    }

}
