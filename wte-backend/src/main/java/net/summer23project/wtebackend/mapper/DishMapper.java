package net.summer23project.wtebackend.mapper;

import lombok.AllArgsConstructor;
import net.summer23project.wtebackend.dto.DishReturnDto;
import net.summer23project.wtebackend.entity.Dish;
import net.summer23project.wtebackend.exception.ApiException;
import net.summer23project.wtebackend.repository.DishRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

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
