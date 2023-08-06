package net.summer23project.wtebackend.mapper;

import net.summer23project.wtebackend.dto.DishDto;
import net.summer23project.wtebackend.entity.Dish;

public class DishMapper {
    public static DishDto mapToDishDto(Dish dish){
        return new DishDto(
                dish.getId(),
                dish.getName(),
                dish.getIngredients()
        );
    }

    public static Dish mapToDish(DishDto dishDto){
        return new Dish(
                dishDto.getId(),
                dishDto.getName(),
                dishDto.getIngredients()
        );
    }
}
