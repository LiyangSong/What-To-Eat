package net.summer23project.wtebackend.facade;

import net.summer23project.wtebackend.dto.UserLoginDto;
import net.summer23project.wtebackend.dto.UserRegisterDto;
import net.summer23project.wtebackend.dto.UserReturnDto;
import org.springframework.security.core.userdetails.User;

/**
 * @author Liyang
 */
public interface UserFacade {
    UserReturnDto register(UserRegisterDto userRegisterDto);
    String login(UserLoginDto userLoginDto);
    UserReturnDto getByName(String userName);
    UserReturnDto update(UserRegisterDto userRegisterDto);
    String delete(String userName);
}
