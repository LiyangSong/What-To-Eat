package net.summer23project.wtebackend.mapper;

import net.summer23project.wtebackend.dto.DishReturnDto;
import net.summer23project.wtebackend.entity.Dish;
import org.springframework.stereotype.Component;

/**
 * @author Liyang
 */
@Component
public class DishMapper {

    public DishReturnDto mapToDishReturnDto(Dish dish) {
        return new DishReturnDto(
                dish.getId(),
                dish.getName()
        );
    }
}
