package net.summer23project.wtebackend.controller;

import lombok.AllArgsConstructor;
import net.summer23project.wtebackend.dto.*;
import net.summer23project.wtebackend.exception.ApiException;
import net.summer23project.wtebackend.facade.DishFacade;
import net.summer23project.wtebackend.mapper.DishDetailsMapper;
import net.summer23project.wtebackend.service.DishIngredientAmountService;
import net.summer23project.wtebackend.service.DishService;
import net.summer23project.wtebackend.service.UserDishMappingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private final DishFacade dishFacade;
    private final UserDishMappingService userDishMappingService;
    private final DishIngredientAmountService dishIngredientAmountService;
    private final DishDetailsMapper dishDetailsMapper;

    // Post http://localhost:8080/api/dishes
    @PostMapping
    @Transactional(rollbackFor = ApiException.class)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<DishDetailsReturnDto> createDish(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody DishDetailsCreateDto dishDetailsCreateDto) {

        DishDetailsReturnDto dishDetailsReturnDto = dishFacade.createDish(
                dishDetailsCreateDto, userDetails.getUsername());
        return new ResponseEntity<>(dishDetailsReturnDto, HttpStatus.CREATED);
    }

    // Get http://localhost:8080/api/dishes/{id}
    @GetMapping("{id}")
    @Transactional(rollbackFor = ApiException.class)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<DishDetailsReturnDto> getDishById(
            @PathVariable("id") Long dishId){

        DishDetailsReturnDto dishDetailsReturnDto = dishFacade.getDishById(dishId);
        return new ResponseEntity<>(dishDetailsReturnDto, HttpStatus.OK);
    }

    @GetMapping
    @Transactional(rollbackFor = ApiException.class)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<List<DishReturnDto>> getDishesByName(
            @AuthenticationPrincipal UserDetails userDetails){
        return null;
        //String userName = userDetails.getUsername();
        //List<UserDishMappingDto> userDishMappingDtos = userDishMappingService.getUserDishMappingDtosByUserName(userName);
        //
        //List<DishReturnDto> dishReturnDtos = userDishMappingDtos.stream().map(userDishMappingDto -> {
        //    DishReturnDto dishReturnDto = dishService.getDishByName(userDishMappingDto.getDishName());
        //    return dishDetailsMapper.mapToDishDetailsDto(dishReturnDto);
        //}).collect(Collectors.toList());
        //
        //return new ResponseEntity<>(dishReturnDtos, HttpStatus.OK);
    }

    @PutMapping("{name}")
    @Transactional(rollbackFor = ApiException.class)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<DishReturnDto> updateDish(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable("name") String dishName,
            @RequestBody DishReturnDto updateDishReturnDto){
        return null;
        //String userName = userDetails.getUsername();
        //List<UserDishMappingDto> userDishMappingDtos = userDishMappingService.getUserDishMappingDtosByUserName(userName);
        //DishReturnDto dishReturnDto = dishService.getDishByName(dishName);
        //
        //boolean dishExistsInUserDishMappings = userDishMappingDtos.stream()
        //        .anyMatch(userDishMappingDto -> userDishMappingDto.getDishName().equals(dishReturnDto.getName()));
        //
        //if (!dishExistsInUserDishMappings) {
        //    throw new ApiException(HttpStatus.BAD_REQUEST, "Dish does not exist in current user's dishes with given dishName: " + dishName);
        //}
        //
        //DishReturnDto updatedDishDto = dishDetailsMapper.mapToDishDto(updateDishReturnDto);
        //dishService.updateDish(dishName, updatedDishDto);
        //
        //List<DishIngredientAmountCreateDto> dishIngredientAmountCreateDtos = dishIngredientAmountService.getDishIngredientAmountDtosByDishName(dishName);
        //List<DishIngredientAmountCreateDto> updatedDishIngredientAmountCreateDtos = dishDetailsMapper.mapToDishIngredientAmountDtos(updateDishReturnDto);
        //
        //List<DishIngredientAmountCreateDto> savedUpdatedDtos = dishIngredientAmountService.updateDishIngredientAmountList(dishIngredientAmountCreateDtos, updatedDishIngredientAmountCreateDtos);
        //DishReturnDto updatedDishReturnDto =  dishDetailsMapper.mapToDishDetailsDto(dishReturnDto, savedUpdatedDtos);
        //
        //return new ResponseEntity<>(updatedDishReturnDto, HttpStatus.OK);
    }

    @DeleteMapping("{name}")
    @Transactional(rollbackFor = ApiException.class)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<String> deleteDish(
            @PathVariable("name") String dishName,
            @AuthenticationPrincipal UserDetails userDetails){
        return null;
    //    String userName = userDetails.getUsername();
    //    List<UserDishMappingDto> userDishMappingDtos = userDishMappingService.getUserDishMappingDtosByUserName(userName);
    //    DishReturnDto dishReturnDto = dishService.getDishByName(dishName);
    //
    //    boolean dishExistsInUserDishMappings = userDishMappingDtos.stream()
    //            .anyMatch(userDishMappingDto -> userDishMappingDto.getDishName().equals(dishReturnDto.getName()));
    //
    //    if (!dishExistsInUserDishMappings) {
    //        throw new ApiException(HttpStatus.BAD_REQUEST, "Dish does not exist in current user's dishes with given dishName: " + dishName);
    //    }
    //
    //    dishService.deleteDish(dishName);
    //    return new ResponseEntity<>("Delete dish " + dishName + " successfully", HttpStatus.NO_CONTENT);
    }
}