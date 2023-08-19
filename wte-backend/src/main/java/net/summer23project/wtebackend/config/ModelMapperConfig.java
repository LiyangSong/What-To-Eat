package net.summer23project.wtebackend.config;

import lombok.AllArgsConstructor;
import net.summer23project.wtebackend.dto.*;
import net.summer23project.wtebackend.entity.*;
import net.summer23project.wtebackend.exception.ApiException;
import net.summer23project.wtebackend.repository.*;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * @author Liyang
 */
@Configuration
@AllArgsConstructor
public class ModelMapperConfig {
    private UnitRepository unitRepository;
    private DishRepository dishRepository;
    private IngredientRepository ingredientRepository;
    private NutrientRepository nutrientRepository;
    private UserRepository userRepository;

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setSkipNullEnabled(true);

        // Customize mapper from Ingredient to Dto

        TypeMap<Ingredient, IngredientDto> ingredientMapToDto = modelMapper.createTypeMap(Ingredient.class, IngredientDto.class);
        ingredientMapToDto.addMappings(mapper ->
            mapper.map(src -> src.getUnit().getId(), IngredientDto::setUnitId)
        );

        // Customize mapper from IngredientDto to Entity

        Converter<Long, Unit> unitIdToUnitConverter = context -> {
            Long unitId = context.getSource();
            return unitRepository.findById(unitId)
                    .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "Unit does not exist with given unitId: " + unitId));
        };

        TypeMap<IngredientDto, Ingredient> ingredientDtoMapToEntity = modelMapper.createTypeMap(IngredientDto.class, Ingredient.class);
        ingredientDtoMapToEntity.addMappings(mapper ->
            mapper.using(unitIdToUnitConverter).map(IngredientDto::getUnitId, Ingredient::setUnit)
        );


        // Customize mapper from DishIngredientAmount to Dto

        TypeMap<DishIngredientAmount, DishIngredientAmountDto> dishIngredientAmountMapToDto = modelMapper.createTypeMap(DishIngredientAmount.class, DishIngredientAmountDto.class);
        dishIngredientAmountMapToDto.addMappings(mapper -> {
            mapper.map(src -> src.getDish().getId(), DishIngredientAmountDto::setDishId);
            mapper.map(src -> src.getIngredient().getId(), DishIngredientAmountDto::setIngredientId);
        });

        // Customize mapper from DishIngredientAmountDto to Entity

        Converter<Long, Dish> dishIdToDishConverter = context -> {
            Long dishId = context.getSource();
            return dishRepository.findById(dishId)
                    .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "Dish does not exist with given dishId: " + dishId));
        };

        Converter<Long, Ingredient> ingredientIdToIngredientConverter = context -> {
            Long ingredientId = context.getSource();
            return ingredientRepository.findById(ingredientId)
                    .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "Ingredient does not exist with given ingredientId: " + ingredientId));
        };

        TypeMap<DishIngredientAmountDto, DishIngredientAmount> dishIngredientDtoMapToEntity = modelMapper.createTypeMap(DishIngredientAmountDto.class, DishIngredientAmount.class);
        dishIngredientDtoMapToEntity.addMappings(mapper -> {
            mapper.skip(DishIngredientAmount::setId);
            mapper.using(dishIdToDishConverter).map(DishIngredientAmountDto::getDishId, DishIngredientAmount::setDish);
            mapper.using(ingredientIdToIngredientConverter).map(DishIngredientAmountDto::getIngredientId, DishIngredientAmount::setIngredient);
        });

        // Customize mapper from IngredientNutrientAmount to Dto

        TypeMap<IngredientNutrientAmount, IngredientNutrientAmountDto> ingredientNutrientAmountMapToDto = modelMapper.createTypeMap(IngredientNutrientAmount.class, IngredientNutrientAmountDto.class);
        ingredientNutrientAmountMapToDto.addMappings(mapper -> {
            mapper.map(src -> src.getIngredient().getId(), IngredientNutrientAmountDto::setIngredientId);
            mapper.map(src -> src.getNutrient().getId(), IngredientNutrientAmountDto::setNutrientId);
        });

        // Customize mapper from IngredientNutrientAmountDto to Entity

        Converter<Long, Nutrient> nutrientIdToNutrientConverter = context -> {
            Long nutrientId = context.getSource();
            return nutrientRepository.findById(nutrientId)
                    .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "Nutrient does not exist with given dishId: " + nutrientId));
        };

        TypeMap<IngredientNutrientAmountDto, IngredientNutrientAmount> ingredientNutrientAmountDtoMapToEntity = modelMapper.createTypeMap(IngredientNutrientAmountDto.class, IngredientNutrientAmount.class);
        ingredientNutrientAmountDtoMapToEntity.addMappings(mapper -> {
            mapper.skip(IngredientNutrientAmount::setId);
            mapper.using(ingredientIdToIngredientConverter).map(IngredientNutrientAmountDto::getIngredientId, IngredientNutrientAmount::setIngredient);
            mapper.using(nutrientIdToNutrientConverter).map(IngredientNutrientAmountDto::getNutrientId, IngredientNutrientAmount::setNutrient);
        });

        // Customize mapper from User to Dto

        TypeMap<User, UserDto> userMapToDto = modelMapper.createTypeMap(User.class, UserDto.class);
        userMapToDto.addMappings(mapper ->
            mapper.map(src -> src.getGender().getId(), UserDto::setGenderId)
        );

        // Note: Not necessary to map from UserDto to User, not created here

        // Customize mapper from UserIngredientInventory to Dto

        TypeMap<UserIngredientInventory, UserIngredientInventoryDto> userIngredientInventoryMapToDto = modelMapper.createTypeMap(UserIngredientInventory.class, UserIngredientInventoryDto.class);
        userIngredientInventoryMapToDto.addMappings(mapper -> {
            mapper.map(src -> src.getUser().getId(), UserIngredientInventoryDto::setUserId);
            mapper.map(src -> src.getIngredient().getId(), UserIngredientInventoryDto::setIngredientId);
        });

        // Customize mapper from UserIngredientInventoryDto to Entity

        Converter<Long, User> userIdToUserConverter = context -> {
            Long userId = context.getSource();
            return userRepository.findById(userId)
                    .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "User does not exist with given userId: " + userId));
        };

        TypeMap<UserIngredientInventoryDto, UserIngredientInventory> userIngredientInventoryDtoMapToEntity = modelMapper.createTypeMap(UserIngredientInventoryDto.class, UserIngredientInventory.class);
        userIngredientInventoryDtoMapToEntity.addMappings(mapper -> {
            mapper.skip(UserIngredientInventory::setId);
            mapper.using(userIdToUserConverter).map(UserIngredientInventoryDto::getUserId, UserIngredientInventory::setUser);
            mapper.using(ingredientIdToIngredientConverter).map(UserIngredientInventoryDto::getIngredientId, UserIngredientInventory::setIngredient);
        });

        return modelMapper;
    }
}
