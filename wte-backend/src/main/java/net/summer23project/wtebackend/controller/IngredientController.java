package net.summer23project.wtebackend.controller;

import lombok.AllArgsConstructor;
import net.summer23project.wtebackend.dto.IngredientDetailsCreateDto;
import net.summer23project.wtebackend.dto.IngredientDetailsReturnDto;
import net.summer23project.wtebackend.dto.UserIngredientInventoryCreateDto;
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

import java.util.List;

/**
 * @author Liyang
 */
@RestController
@RequestMapping("/api/ingredients")
@CrossOrigin("*")
@AllArgsConstructor
public class IngredientController {
    private final IngredientFacade ingredientFacade;

    // POST http://localhost:8080/api/ingredients/create
    @PostMapping("create")
    @Transactional(rollbackFor = ApiException.class)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<IngredientDetailsReturnDto> createIngredient(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody IngredientDetailsCreateDto ingredientDetailsCreateDto) {

        IngredientDetailsReturnDto ingredientDetailsReturnDto = ingredientFacade.create(
                ingredientDetailsCreateDto, userDetails.getUsername());
        return new ResponseEntity<>(ingredientDetailsReturnDto, HttpStatus.CREATED);
    }

    // GET http://localhost:8080/api/ingredients/get/id={id}
    @GetMapping("get/id={id}")
    @Transactional(rollbackFor = ApiException.class)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<IngredientDetailsReturnDto> getIngredientById(
            @PathVariable("id") Long ingredientId) {

        IngredientDetailsReturnDto ingredientDetailsReturnDto = ingredientFacade.getById(ingredientId);
        return new ResponseEntity<>(ingredientDetailsReturnDto, HttpStatus.OK);
    }

    // GET http://localhost:8080/api/ingredients/get/name={name}
    @GetMapping("get/name={name}")
    @Transactional(rollbackFor = ApiException.class)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<List<IngredientDetailsReturnDto>> getIngredientsByName(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable("name") String ingredientName) {

        List<IngredientDetailsReturnDto> ingredientDetailsReturnDtos = ingredientFacade.getByName(
                ingredientName, userDetails.getUsername());
        return new ResponseEntity<>(ingredientDetailsReturnDtos, HttpStatus.OK);
    }

    // GET http://localhost:8080/api/ingredients/getAll
    @GetMapping("getAll")
    @Transactional(rollbackFor = ApiException.class)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<List<IngredientDetailsReturnDto>> getAllIngredients(
            @AuthenticationPrincipal UserDetails userDetails) {

        List<IngredientDetailsReturnDto> ingredientDetailsReturnDtos = ingredientFacade.getAll(userDetails.getUsername());
        return new ResponseEntity<>(ingredientDetailsReturnDtos, HttpStatus.OK);
    }

    // PUT http://localhost:8080/api/ingredients/update/id={id}
    @PutMapping("update/id={id}")
    @Transactional(rollbackFor = ApiException.class)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<IngredientDetailsReturnDto> updateIngredient(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable("id") Long ingredientId,
            @RequestBody IngredientDetailsCreateDto updatedIngredientDetailsCreateDto){

        IngredientDetailsReturnDto ingredientDetailsReturnDto = ingredientFacade.update(
                ingredientId, updatedIngredientDetailsCreateDto, userDetails.getUsername());
        return new ResponseEntity<>(ingredientDetailsReturnDto, HttpStatus.OK);
    }

    // DELETE http://localhost:8080/api/ingredients/delete/id={id}
    @DeleteMapping("delete/id={id}")
    @Transactional(rollbackFor = ApiException.class)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<String> deleteIngredient(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable("id") Long ingredientId){

        String response = ingredientFacade.delete(ingredientId, userDetails.getUsername());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // POST http://localhost:8080/api/ingredients/add/id={id}
    @PostMapping("add/id={id}")
    @Transactional(rollbackFor = ApiException.class)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<UserIngredientInventoryReturnDto> addIngredient(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable("id") Long ingredientId) {

        UserIngredientInventoryReturnDto inventoryReturnDto = ingredientFacade.add(
                ingredientId, userDetails.getUsername());
        return new ResponseEntity<>(inventoryReturnDto, HttpStatus.CREATED);
    }

    // GET http://localhost:8080/api/ingredients/getInventory/id={id}
    @GetMapping("getInventory/id={id}")
    @Transactional(rollbackFor = ApiException.class)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<UserIngredientInventoryReturnDto> getIngredientInventory(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable("id") Long ingredientId) {

        UserIngredientInventoryReturnDto inventoryReturnDto = ingredientFacade.getInventory(
                ingredientId, userDetails.getUsername());
        return new ResponseEntity<>(inventoryReturnDto, HttpStatus.OK);
    }

    // PUT http://localhost:8080/api/ingredients/updateInventory/id={id}
    @PutMapping("updateInventory/id={id}")
    @Transactional(rollbackFor = ApiException.class)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<UserIngredientInventoryReturnDto> updateIngredientInventory(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable("id") Long ingredientId,
            @RequestBody UserIngredientInventoryCreateDto updatedInventoryCreateDto) {

        UserIngredientInventoryReturnDto inventoryReturnDto = ingredientFacade.updateInventory(
                ingredientId, updatedInventoryCreateDto, userDetails.getUsername());
        return new ResponseEntity<>(inventoryReturnDto, HttpStatus.OK);
    }

    // DELETE http://localhost:8080/api/ingredients/remove/id={id}
    @DeleteMapping("remove/id={id}")
    @Transactional(rollbackFor = ApiException.class)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<String> removeIngredient(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable("id") Long ingredientId) {

        String response = ingredientFacade.remove(ingredientId, userDetails.getUsername());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
