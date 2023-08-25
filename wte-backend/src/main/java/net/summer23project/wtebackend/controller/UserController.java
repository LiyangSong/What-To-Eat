package net.summer23project.wtebackend.controller;

import lombok.AllArgsConstructor;
import net.summer23project.wtebackend.dto.JwtAuthResponseDto;
import net.summer23project.wtebackend.dto.UserLoginDto;
import net.summer23project.wtebackend.dto.UserRegisterDto;
import net.summer23project.wtebackend.dto.UserReturnDto;
import net.summer23project.wtebackend.facade.UserFacade;
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
    private final UserFacade userFacade;

    @PostMapping("register")
    public ResponseEntity<UserReturnDto> register(@RequestBody UserRegisterDto userRegisterDto){
        UserReturnDto userReturnDto = userFacade.register(userRegisterDto);
        return new ResponseEntity<>(userReturnDto, HttpStatus.CREATED);
    }

    @PostMapping("login")
    public ResponseEntity<JwtAuthResponseDto> login(@RequestBody UserLoginDto userLoginDto){
        String token = userFacade.login(userLoginDto);

        JwtAuthResponseDto jwtAuthResponseDto = new JwtAuthResponseDto();
        jwtAuthResponseDto.setAccessToken(token);

        return new ResponseEntity<>(jwtAuthResponseDto, HttpStatus.OK);
    }

}
