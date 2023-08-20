package net.summer23project.wtebackend.mapper;

import net.summer23project.wtebackend.dto.DishDto;
import net.summer23project.wtebackend.entity.Dish;
import org.springframework.stereotype.Service;

/**
 * @author Liyang
 */
@Service
public class DishMapper {
    public DishDto mapToDishDto(Dish dish) {
        return new DishDto(dish.getName());
    }

    public Dish mapToDish(DishDto dishDto) {
        Dish dish = new Dish();
        dish.setName(dishDto.getName());
        return dish;
    }
}
