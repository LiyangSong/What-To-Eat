package net.summer23project.wtebackend.controller;

import lombok.AllArgsConstructor;
import net.summer23project.wtebackend.dto.IngredientDetailsCreateDto;
import net.summer23project.wtebackend.dto.IngredientDetailsReturnDto;
import net.summer23project.wtebackend.dto.UserIngredientInventoryReturnDto;
import net.summer23project.wtebackend.exception.ApiException;
import net.summer23project.wtebackend.facade.IngredientFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


/**
 * @author Liyang
 */
@RestController
@RequestMapping("/api/ingredients")
@CrossOrigin("*")
@AllArgsConstructor
public class IngredientController {
    private final IngredientFacade ingredientFacade;

    // Post http://localhost:8080/api/ingredients/create
    @PostMapping("create")
    @Transactional(rollbackFor = ApiException.class)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<IngredientDetailsReturnDto> createIngredient(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody IngredientDetailsCreateDto ingredientDetailsCreateDto) {

        IngredientDetailsReturnDto ingredientDetailsReturnDto = ingredientFacade.createIngredient(
                ingredientDetailsCreateDto, userDetails.getUsername());
        return new ResponseEntity<>(ingredientDetailsReturnDto, HttpStatus.CREATED);
    }

    // Post http://localhost:8080/api/ingredients/add/id={id}
    @PostMapping("add/id={id}")
    @Transactional(rollbackFor = ApiException.class)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<UserIngredientInventoryReturnDto> addIngredient(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable("id") Long ingredientId) {

        UserIngredientInventoryReturnDto inventoryReturnDto = ingredientFacade.addIngredient(
                ingredientId, userDetails.getUsername());
        return new ResponseEntity<>(inventoryReturnDto, HttpStatus.CREATED);
    }

    // Delete http://localhost:8080/api/ingredients/remove/id={id}
    @DeleteMapping("remove/id={id}")
    @Transactional(rollbackFor = ApiException.class)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<String> removeIngredient(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable("id") Long ingredientId) {

        String response = ingredientFacade.removeIngredient(ingredientId, userDetails.getUsername());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //@GetMapping("{name}")
    //@Transactional(rollbackFor = ApiException.class)
    //@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    //public ResponseEntity<IngredientDetailsDto> getIngredientByName(
    //        @PathVariable("name") String ingredientName) {
    //
    //    IngredientDto ingredientDto = ingredientService.getIngredientByName(ingredientName);
    //    IngredientDetailsDto ingredientDetailsDto = ingredientDetailsMapper.mapToIngredientDetailsDto(ingredientDto);
    //    return new ResponseEntity<>(ingredientDetailsDto, HttpStatus.OK);
    //}
    //
    //@GetMapping
    //@Transactional(rollbackFor = ApiException.class)
    //@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    //public ResponseEntity<List<IngredientDetailsDto>> getAllIngredients() {
    //    List<IngredientDetailsDto> ingredientDetailsDtos = ingredientService.getAllIngredients()
    //            .stream().map(ingredientDetailsMapper::mapToIngredientDetailsDto)
    //            .collect(Collectors.toList());
    //
    //    return new ResponseEntity<>(ingredientDetailsDtos, HttpStatus.OK);
    //}
    //
    //@PutMapping("{name}")
    //@Transactional(rollbackFor = ApiException.class)
    //@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    //public ResponseEntity<IngredientDetailsDto> updateIngredient(
    //        @PathVariable("name") String ingredientName,
    //        @RequestBody DishReturnDto updateIngredientDetailsDto){
    //
    //
    //
    //    return null;
    //}

    // Delete http://localhost:8080/api/ingredients/delete/id={id}
    @DeleteMapping("delete/id={id}")
    @Transactional(rollbackFor = ApiException.class)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<String> deleteIngredient(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable("id") Long ingredientId){

        String response = ingredientFacade.deleteIngredient(ingredientId, userDetails.getUsername());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
