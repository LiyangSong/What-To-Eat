package net.summer23project.wtebackend.mapper;

import lombok.AllArgsConstructor;
import net.summer23project.wtebackend.dto.RegisterDto;
import net.summer23project.wtebackend.dto.UserDto;
import net.summer23project.wtebackend.entity.User;
import net.summer23project.wtebackend.exception.ApiException;
import net.summer23project.wtebackend.repository.GenderRepository;
import net.summer23project.wtebackend.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Liyang
 */
@Service
@AllArgsConstructor
public class UserMapper {
    private final UserRepository userRepository;
    private final GenderRepository genderRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDto mapToUserDto(User user) {
        return new UserDto(
                user.getName(),
                user.getEmail(),
                user.getAge(),
                user.getGender().getName()
        );
    }

    public User mapToUser(RegisterDto registerDto) {
        String userName = registerDto.getName();
        if(userRepository.existsByName(userName)) {
            throw new ApiException(HttpStatus.CONFLICT, "User already exists with given userName: " + userName);
        }

        String userEmail = registerDto.getEmail();
        if(userRepository.existsByEmail(userEmail)) {
            throw new ApiException(HttpStatus.CONFLICT, "User already exists with given userEmail: " + userEmail);
        }

        User user = new User();
        user.setName(registerDto.getName());
        user.setEmail(registerDto.getEmail());
        user.setAge(registerDto.getAge());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        String genderName = registerDto.getGenderName();
        user.setGender(genderRepository.findByName(genderName)
                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "Gender does not exists with given genderName: " + genderName)));

        return user;
    }
}
