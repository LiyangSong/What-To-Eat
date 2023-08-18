package net.summer23project.wtebackend.controller;

import lombok.AllArgsConstructor;
import net.summer23project.wtebackend.dto.DishDto;
import net.summer23project.wtebackend.dto.DishIngredientAmountDto;
import net.summer23project.wtebackend.exception.ApiException;
import net.summer23project.wtebackend.mapper.DishRequestMapper;
import net.summer23project.wtebackend.service.DishIngredientAmountService;
import net.summer23project.wtebackend.service.DishService;
import net.summer23project.wtebackend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    // http://localhost:8080/api/dishes/create
    @PostMapping("/create")
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

    @GetMapping("{id}")
    @Transactional(rollbackFor = ApiException.class)
    public ResponseEntity<DishDto> getDishById(@PathVariable("id") Long dishId){
        DishDto dishDto=dishService.getDishById(dishId);
        return ResponseEntity.ok(dishDto);
    }

    @GetMapping
    @Transactional(rollbackFor = ApiException.class)
    public ResponseEntity<List<DishDto>> getAllDish(@AuthenticationPrincipal UserDetails userDetails){
        List<DishDto> dishes=dishService.getAllDishes(userDetails.getUsername());
        return ResponseEntity.ok(dishes);
    }

    @PutMapping("{id}")
    @Transactional(rollbackFor = ApiException.class)
    public ResponseEntity<DishDto> updateDish(@PathVariable("id") Long dishId,@RequestBody DishDto updatedishDto){
        DishDto dishDto=dishService.updateDish(dishId,updatedishDto);
        return ResponseEntity.ok(dishDto);
    }

    @DeleteMapping("{id}")
    @Transactional(rollbackFor = ApiException.class)
    public ResponseEntity<String> deleteDish(@PathVariable("id") Long dishId,@AuthenticationPrincipal UserDetails userDetails){
        dishService.deleteDish(dishId, userDetails.getUsername());
        return ResponseEntity.ok("Dish deleted successfully!");
    }

}
