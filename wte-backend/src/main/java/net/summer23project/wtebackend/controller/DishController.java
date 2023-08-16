package net.summer23project.wtebackend.controller;

import lombok.AllArgsConstructor;
import net.summer23project.wtebackend.dto.DishDto;
import net.summer23project.wtebackend.entity.Dish;
import net.summer23project.wtebackend.entity.User;
import net.summer23project.wtebackend.exception.ApiException;
import net.summer23project.wtebackend.repository.DishRepository;
import net.summer23project.wtebackend.repository.UserRepository;
import net.summer23project.wtebackend.service.DishService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Liyang
 */
@RestController
@RequestMapping("/api/dishes")
@CrossOrigin("*")
@AllArgsConstructor
public class DishController {
    private DishService dishService;

    // Build Add Dish REST API, test url: Post 127.0.0.1:8080/api/dishes
    @PostMapping
    @Transactional(rollbackFor = ApiException.class)
    public ResponseEntity<DishDto> createDish(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody DishDto dishDto) {
        String userName = userDetails.getUsername();
        DishDto savedDish=dishService.createDish(dishDto,userName);
        return new ResponseEntity<>(savedDish, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<DishDto> getDishById(@PathVariable("id") Long dishId){
        DishDto dishDto=dishService.getDishById(dishId);
        return ResponseEntity.ok(dishDto);
    }

    @GetMapping
    public ResponseEntity<List<DishDto>> getAllDish(@AuthenticationPrincipal UserDetails userDetails){
        List<DishDto> dishes=dishService.getAllDishes(userDetails.getUsername());
        return ResponseEntity.ok(dishes);
    }

    @PutMapping("{id}")
    public ResponseEntity<DishDto> updateDish(@PathVariable("id") Long dishId,@RequestBody DishDto updatedishDto){
        DishDto dishDto=dishService.updateDish(dishId,updatedishDto);
        return ResponseEntity.ok(dishDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteDish(@PathVariable("id") Long dishId,@AuthenticationPrincipal UserDetails userDetails){
        dishService.deleteDish(dishId, userDetails.getUsername());
        return ResponseEntity.ok("Dish deleted successfully!");
    }

}
