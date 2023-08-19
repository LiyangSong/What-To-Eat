package net.summer23project.wtebackend.config;

import lombok.AllArgsConstructor;
import net.summer23project.wtebackend.dto.DishDto;
import net.summer23project.wtebackend.dto.DishIngredientAmountDto;
import net.summer23project.wtebackend.dto.IngredientDto;
import net.summer23project.wtebackend.entity.Dish;
import net.summer23project.wtebackend.entity.DishIngredientAmount;
import net.summer23project.wtebackend.entity.Ingredient;
import net.summer23project.wtebackend.entity.Unit;
import net.summer23project.wtebackend.exception.ApiException;
import net.summer23project.wtebackend.repository.DishRepository;
import net.summer23project.wtebackend.repository.IngredientRepository;
import net.summer23project.wtebackend.repository.UnitRepository;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Set;

/**
 * @author Liyang
 */
@Configuration
@AllArgsConstructor
public class ModelMapperConfig {
    private UnitRepository unitRepository;
    private DishRepository dishRepository;
    private IngredientRepository ingredientRepository;

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setSkipNullEnabled(true);

        // Customize mapper from Ingredient to Dto

        TypeMap<Ingredient, IngredientDto> ingredientMapToDto = modelMapper.createTypeMap(Ingredient.class, IngredientDto.class);
        ingredientMapToDto.addMappings(mapper -> {
            mapper.skip(IngredientDto::setId);
            mapper.map(src -> src.getUnit().getId(), IngredientDto::setUnitId);
        });

        // Customize mapper from IngredientDto to Entity

        Converter<Long, Unit> unitIdToUnitConverter = context -> {
            Long unitId = context.getSource();
            return unitRepository.findById(unitId)
                    .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "Unit does not exist with given unitId: " + unitId));
        };

        TypeMap<IngredientDto, Ingredient> ingredientDtoMapToEntity = modelMapper.createTypeMap(IngredientDto.class, Ingredient.class);
        ingredientDtoMapToEntity.addMappings(mapper -> {
            mapper.skip(Ingredient::setId);
            mapper.using(unitIdToUnitConverter).map(IngredientDto::getUnitId, Ingredient::setUnit);
        });


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

        return modelMapper;
    }
}
