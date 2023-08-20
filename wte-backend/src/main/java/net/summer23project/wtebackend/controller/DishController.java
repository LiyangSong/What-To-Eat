package net.summer23project.wtebackend.controller;

import lombok.AllArgsConstructor;
import net.summer23project.wtebackend.dto.DishDetailsDto;
import net.summer23project.wtebackend.dto.DishDto;
import net.summer23project.wtebackend.dto.DishIngredientAmountDto;
import net.summer23project.wtebackend.exception.ApiException;
import net.summer23project.wtebackend.mapper.DishDetailsMapper;
import net.summer23project.wtebackend.service.DishIngredientAmountService;
import net.summer23project.wtebackend.service.DishService;
import net.summer23project.wtebackend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    private DishDetailsMapper dishDetailsMapper;

    // Post http://localhost:8080/api/dishes
    @PostMapping
    @Transactional(rollbackFor = ApiException.class)
    public ResponseEntity<DishDetailsDto> createDish(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody DishDetailsDto dishDetailsDto) {

        DishDto dishDto = dishDetailsMapper.mapToDishDto(dishDetailsDto);
        DishDto savedDishDto = dishService.createDish(dishDto);

        String userName = userDetails.getUsername();
        userService.updateUserWithAddedDish(userName, savedDishDto);

        List<DishIngredientAmountDto> dishIngredientAmountDtos = dishDetailsMapper.mapToDishIngredientAmountDtos(dishDetailsDto);
        List<DishIngredientAmountDto> savedDishIngredientAmountDtos = new ArrayList<>();
        for (DishIngredientAmountDto dishIngredientAmountDto : dishIngredientAmountDtos) {
            DishIngredientAmountDto savedDishIngredientAmountDto = dishIngredientAmountService.createDishIngredientAmount(dishIngredientAmountDto);
            savedDishIngredientAmountDtos.add(savedDishIngredientAmountDto);
        }

        DishDetailsDto savedDishDetailsDto = dishDetailsMapper.mapToDishDetailsDto(
                savedDishDto, savedDishIngredientAmountDtos
        );

        return new ResponseEntity<>(savedDishDetailsDto, HttpStatus.CREATED);
    }

    // Get http://localhost:8080/api/dishes/{name}
    @GetMapping("{name}")
    @Transactional(rollbackFor = ApiException.class)
    public ResponseEntity<DishDetailsDto> getDishByName(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable("name") String dishName){

        String userName = userDetails.getUsername();
        List<DishDto> userDishes = userService.getDishesByUserName(userName);
        DishDto dishDto = dishService.getDishByName(dishName);

        boolean dishExistsInUserDishes = userDishes.stream()
                .anyMatch(userDish -> userDish.getName().equals(dishDto.getName()));

        if (!dishExistsInUserDishes) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Dish does not exist in current user's dishes with given dishName: " + dishName);
        }

        DishDetailsDto dishDetailsDto = dishDetailsMapper.mapToDishDetailsDto(dishDto);
        return new ResponseEntity<>(dishDetailsDto, HttpStatus.OK);
    }

    @GetMapping
    @Transactional(rollbackFor = ApiException.class)
    public ResponseEntity<List<DishDetailsDto>> getAllDishes(@AuthenticationPrincipal UserDetails userDetails){
        String userName = userDetails.getUsername();
        List<DishDto> dishDtos = userService.getDishesByUserName(userName);

        List<DishDetailsDto> dishDetailsDtos = dishDtos.stream().map(dishDto ->
            dishDetailsMapper.mapToDishDetailsDto(dishDto)
        ).collect(Collectors.toList());
        return new ResponseEntity<>(dishDetailsDtos, HttpStatus.OK);
    }

    @PutMapping("{name}")
    @Transactional(rollbackFor = ApiException.class)
    public ResponseEntity<DishDetailsDto> updateDish(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable("name") String dishName,
            @RequestBody DishDetailsDto updateDishDetailsDto){
        return null;
    }

    @DeleteMapping("{id}")
    @Transactional(rollbackFor = ApiException.class)
    public ResponseEntity<String> deleteDish(
            @PathVariable("id") Long dishId,
            @AuthenticationPrincipal UserDetails userDetails){
        dishService.deleteDish(dishId, userDetails.getUsername());
        return ResponseEntity.ok("Dish deleted successfully!");
    }

}
