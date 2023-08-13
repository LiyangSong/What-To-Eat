package net.summer23project.wtebackend.controller;

import lombok.AllArgsConstructor;
import net.summer23project.wtebackend.dto.DishDto;
import net.summer23project.wtebackend.entity.Dish;
import net.summer23project.wtebackend.entity.User;
import net.summer23project.wtebackend.exception.ApiException;
import net.summer23project.wtebackend.repository.DishRepository;
import net.summer23project.wtebackend.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * @author Liyang
 */
@RestController
@RequestMapping("/api/dishes")
@CrossOrigin("*")
@AllArgsConstructor
public class DishController {
    private DishRepository dishRepository;
    private UserRepository userRepository;
    @PostMapping("/add")
    @Transactional(rollbackFor = ApiException.class)
    public ResponseEntity<String> addDish(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody DishDto dishDto) {

        Dish dish = new Dish();
        dish.setName(dishDto.getName());

        dishRepository.save(dish);

        String username = userDetails.getUsername();
        User user = userRepository.findByName(username)
                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "User name not exists!"));
        user.getDishes().add(dish);

        userRepository.save(user);

        return new ResponseEntity<>("New dish " + dish.getName() + " added!", HttpStatus.CREATED);

    }
}
