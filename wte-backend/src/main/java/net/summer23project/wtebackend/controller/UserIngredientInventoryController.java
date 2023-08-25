package net.summer23project.wtebackend.controller;

import lombok.AllArgsConstructor;
import net.summer23project.wtebackend.dto.UserIngredientInventoryDto;
import net.summer23project.wtebackend.exception.ApiException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Liyang
 */
@RestController
@RequestMapping("/api/ingredient_inventories")
@CrossOrigin("*")
@AllArgsConstructor
public class UserIngredientInventoryController {
    //@PostMapping
    //@Transactional(rollbackFor = ApiException.class)
    //public ResponseEntity<UserIngredientInventoryDto> createUserIngredientInventory(
    //        @AuthenticationPrincipal UserDetails userDetails,
    //        @RequestBody Map<String, Object> requestBody){
    //    return null;
    //}
    //
    //
    //@GetMapping("{name}")
    //@Transactional(rollbackFor = ApiException.class)
    //public ResponseEntity<UserIngredientInventoryDto> getUserIngredientInventoryByIngredientName(
    //        @AuthenticationPrincipal UserDetails userDetails,
    //        @PathVariable("name") String ingredientName){
    //    return null;
    //}
}
