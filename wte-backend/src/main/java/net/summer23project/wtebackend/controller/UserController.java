package net.summer23project.wtebackend.controller;

import lombok.AllArgsConstructor;
import net.summer23project.wtebackend.dto.RegisterDto;
import net.summer23project.wtebackend.dto.UserDto;
import net.summer23project.wtebackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Liyang
 */
@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
@AllArgsConstructor
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> createUser(@RequestBody RegisterDto registerDto){
        UserDto createdUser = userService.registerUser(registerDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
}
