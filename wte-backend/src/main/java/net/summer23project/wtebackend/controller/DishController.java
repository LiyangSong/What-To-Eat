package net.summer23project.wtebackend.controller;

import lombok.AllArgsConstructor;
import net.summer23project.wtebackend.dto.DishDto;
import net.summer23project.wtebackend.dto.DishIngredientAmountDto;
import net.summer23project.wtebackend.entity.Dish;
import net.summer23project.wtebackend.entity.User;
import net.summer23project.wtebackend.exception.ApiException;
import net.summer23project.wtebackend.mapper.DishRequestMapper;
import net.summer23project.wtebackend.service.DishIngredientAmountService;
import net.summer23project.wtebackend.service.DishService;
import net.summer23project.wtebackend.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * @author Yue, Liyang
 */
@RestController
@RequestMapping("/api/dishes")
@CrossOrigin("*")
@AllArgsConstructor
public class DishController {
    private DishService dishService;
    private UserService userService;
    private DishIngredientAmountService dishIngredientAmountService;
    private DishRequestMapper dishRequestMapper;
    private ModelMapper modelMapper;

    // Post http://localhost:8080/api/dishes
    @PostMapping
    @Transactional(rollbackFor = ApiException.class)
    public ResponseEntity<DishDto> createDish(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody Map<String, Object> requestBody) {

        DishDto dishDto = dishRequestMapper.mapToDishDto(requestBody);
        DishDto savedDishDto = dishService.createDish(dishDto);

        String userName = userDetails.getUsername();
        userService.updateUserWithAddedDish(userName, savedDishDto);

        List<DishIngredientAmountDto> dishIngredientAmountDtos = dishRequestMapper.mapToDishIngredientAmountDtos(requestBody);
        for (DishIngredientAmountDto dishIngredientAmountDto : dishIngredientAmountDtos) {
            dishIngredientAmountService.createDishIngredientAmount(dishIngredientAmountDto);
        }

        return new ResponseEntity<>(savedDishDto, HttpStatus.CREATED);
    }

    // Get http://localhost:8080/api/dishes/{name}
    @GetMapping("{name}")
    @Transactional(rollbackFor = ApiException.class)
    public ResponseEntity<DishDto> getDishByName(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable("name") String dishName){
        try {
            String username = userDetails.getUsername();
            DishDto dishDto = dishService.getDishByName(dishName, username);
            return new ResponseEntity<>(dishDto, HttpStatus.OK);
        } catch (ApiException e) {
            return new ResponseEntity<>(null, e.getStatus());
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    @Transactional(rollbackFor = ApiException.class)
    public ResponseEntity<List<DishDto>> getAllDishes(@AuthenticationPrincipal UserDetails userDetails){
        try {
            List<DishDto> userDishes = dishService.getAllDishes(userDetails.getUsername());
            return new ResponseEntity<>(userDishes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("{name}")
    @Transactional(rollbackFor = ApiException.class)
    public ResponseEntity<DishDto> updateDish(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable("name") String dishName,
            @RequestBody DishDto updateDishDto){
        return null;
    }

    @DeleteMapping("{id}")
    @Transactional(rollbackFor = ApiException.class)
    public ResponseEntity<String> deleteDish(@PathVariable("id") Long dishId,@AuthenticationPrincipal UserDetails userDetails){
        dishService.deleteDish(dishId, userDetails.getUsername());
        return ResponseEntity.ok("Dish deleted successfully!");
    }

}
