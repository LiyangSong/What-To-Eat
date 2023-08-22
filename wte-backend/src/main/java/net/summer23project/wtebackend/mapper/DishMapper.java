package net.summer23project.wtebackend.mapper;

import lombok.AllArgsConstructor;
import net.summer23project.wtebackend.dto.DishDto;
import net.summer23project.wtebackend.entity.Dish;
import net.summer23project.wtebackend.exception.ApiException;
import net.summer23project.wtebackend.repository.DishRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * @author Liyang
 */
@Service
@AllArgsConstructor
public class DishMapper {
    private final DishRepository dishRepository;

    public DishDto mapToDishDto(Dish dish) {
        return new DishDto(dish.getName());
    }

    public Dish mapToDish(DishDto dishDto) {
        String dishName = dishDto.getName();
        if(dishRepository.existsByName(dishName)) {
            throw new ApiException(HttpStatus.CONFLICT, "Dish already exists with given dishName: " + dishName);
        }

        Dish dish = new Dish();
        dish.setName(dishName);
        return dish;
    }
}
