package net.summer23project.wtebackend.controller;

import lombok.AllArgsConstructor;
import net.summer23project.wtebackend.dto.*;
import net.summer23project.wtebackend.exception.ApiException;
import net.summer23project.wtebackend.facade.DishFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private final DishFacade dishFacade;

    // Post http://localhost:8080/api/dishes/create
    @PostMapping("create")
    @Transactional(rollbackFor = ApiException.class)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<DishDetailsReturnDto> createDish(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody DishDetailsCreateDto dishDetailsCreateDto) {

        DishDetailsReturnDto dishDetailsReturnDto = dishFacade.createDish(
                dishDetailsCreateDto, userDetails.getUsername());
        return new ResponseEntity<>(dishDetailsReturnDto, HttpStatus.CREATED);
    }

    // Post http://localhost:8080/api/dishes/add/id={id}
    @PostMapping("add/id={id}")
    @Transactional(rollbackFor = ApiException.class)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<UserDishMappingDto> addDish(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable("id") Long dishId) {

        UserDishMappingDto userDishMappingDto = dishFacade.addDish(dishId, userDetails.getUsername());
        return new ResponseEntity<>(userDishMappingDto, HttpStatus.CREATED);
    }

    // Delete http://localhost:8080/api/dishes/remove/id={id}
    @DeleteMapping("remove/id={id}")
    @Transactional(rollbackFor = ApiException.class)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<String> removeDish(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable("id") Long dishId) {

        String response = dishFacade.removeDish(dishId, userDetails.getUsername());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Get http://localhost:8080/api/dishes/get/id={id}
    @GetMapping("get/id={id}")
    @Transactional(rollbackFor = ApiException.class)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<DishDetailsReturnDto> getDishById(
            @PathVariable("id") Long dishId){

        DishDetailsReturnDto dishDetailsReturnDto = dishFacade.getDishById(dishId);
        return new ResponseEntity<>(dishDetailsReturnDto, HttpStatus.OK);
    }

    // Get http://localhost:8080/api/dishes/get/name={name}
    @GetMapping("get/name={name}")
    @Transactional(rollbackFor = ApiException.class)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<List<DishDetailsReturnDto>> getDishesByName(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable("name") String dishName){

        List<DishDetailsReturnDto> dishDetailsReturnDtos = dishFacade.getDishesByName(dishName, userDetails.getUsername());
        return new ResponseEntity<>(dishDetailsReturnDtos, HttpStatus.OK);
    }

    // Get http://localhost:8080/api/dishes/getAll
    @GetMapping("getAll")
    @Transactional(rollbackFor = ApiException.class)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<List<DishDetailsReturnDto>> getAllDishes(
            @AuthenticationPrincipal UserDetails userDetails) {

        List<DishDetailsReturnDto> dishDetailsReturnDtos = dishFacade.getAllDishes(userDetails.getUsername());
        return new ResponseEntity<>(dishDetailsReturnDtos, HttpStatus.OK);
    }

    // Put http://localhost:8080/api/dishes/update/id={id}
    @PutMapping("update/id={id}")
    @Transactional(rollbackFor = ApiException.class)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<DishDetailsReturnDto> updateDish(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable("id") Long dishId,
            @RequestBody DishDetailsCreateDto udpatedDishDetailsCreateDto){

        DishDetailsReturnDto dishDetailsReturnDto = dishFacade.updateDish(
                dishId, udpatedDishDetailsCreateDto, userDetails.getUsername());
        return new ResponseEntity<>(dishDetailsReturnDto, HttpStatus.OK);
    }

    // Delete http://localhost:8080/api/dishes/delete/id={id}
    @DeleteMapping("delete/id={id}")
    @Transactional(rollbackFor = ApiException.class)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<String> deleteDish(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable("id") Long dishId){

        String response = dishFacade.deleteDish(dishId, userDetails.getUsername());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}