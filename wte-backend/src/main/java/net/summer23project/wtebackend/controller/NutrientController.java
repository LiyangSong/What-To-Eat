package net.summer23project.wtebackend.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import net.summer23project.wtebackend.entity.Nutrient;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/nutrients")
@CrossOrigin("*")
@AllArgsConstructor
public class NutrientController {
}
