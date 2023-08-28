package net.summer23project.wtebackend.service.impl;

import lombok.AllArgsConstructor;
import net.summer23project.wtebackend.dto.UserReturnDto;
import net.summer23project.wtebackend.dto.UserRegisterDto;
import net.summer23project.wtebackend.entity.Gender;
import net.summer23project.wtebackend.entity.Role;
import net.summer23project.wtebackend.entity.User;
import net.summer23project.wtebackend.exception.ApiException;
import net.summer23project.wtebackend.mapper.UserMapper;
import net.summer23project.wtebackend.repository.GenderRepository;
import net.summer23project.wtebackend.repository.RoleRepository;
import net.summer23project.wtebackend.repository.UserRepository;
import net.summer23project.wtebackend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Liyang
 */
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final GenderRepository genderRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public UserReturnDto create(UserRegisterDto userRegisterDto) {
        User user = new User();

        String userName = userRegisterDto.getName();
        if (userRepository.existsByName(userName)) {
            throw new ApiException(HttpStatus.CONFLICT, "UserName: " + userName + " already exists!");
        }

        String email = userRegisterDto.getEmail();
        if (userRepository.existsByEmail(email)) {
            throw new ApiException(HttpStatus.CONFLICT, "Email: " + email + " already exists!");
        }

        user.setName(userName);
        user.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));
        user.setEmail(email);
        user.setAge(userRegisterDto.getAge());

        String genderName = userRegisterDto.getGenderName();
        Gender gender = genderRepository.findByName(genderName)
                        .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Gender does not exist with given genderName: " + genderName));
        user.setGender(gender);

        String roleName = userRegisterDto.getRoleName();
        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Role does not exist with given roleName: " + roleName));
        user.setRole(role);

        User savedUser = userRepository.save(user);
        return userMapper.mapToUserReturnDto(savedUser);
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public UserReturnDto getById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "User does not exist with given userId: " + userId));
        return userMapper.mapToUserReturnDto(user);
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public UserReturnDto getByName(String userName) {
        User user = userRepository.findByName(userName)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "User does not exist with given userName: " + userName));
        return userMapper.mapToUserReturnDto(user);
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public UserReturnDto update(Long userId, UserRegisterDto updatedUserRegisterDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "User does not exist with given userId: " + userId));
        user.setName(updatedUserRegisterDto.getName());
        user.setPassword(updatedUserRegisterDto.getPassword());
        user.setEmail(updatedUserRegisterDto.getEmail());
        user.setAge(updatedUserRegisterDto.getAge());

        String genderName = updatedUserRegisterDto.getGenderName();
        Gender gender = genderRepository.findByName(genderName)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Gender does not exist with given genderName: " + genderName));
        user.setGender(gender);

        String roleName = updatedUserRegisterDto.getRoleName();
        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Role does not exist with given roleName: " + roleName));
        user.setRole(role);

        User savedUser = userRepository.save(user);
        return userMapper.mapToUserReturnDto(savedUser);
    }

    @Override
    @Transactional(rollbackFor = ApiException.class)
    public void delete(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "User does not exist with given userId: " + userId));
        userRepository.delete(user);
    }
}
