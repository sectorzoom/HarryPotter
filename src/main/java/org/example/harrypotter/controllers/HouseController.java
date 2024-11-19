package org.example.harrypotter.controllers;

import org.example.harrypotter.entities.House;
import org.example.harrypotter.services.HouseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class HouseController {

    private final HouseService houseService;

    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    @GetMapping("/houses")
    public String getAllHouses(Model model) {
        List<House> houses = houseService.getHouses();
        model.addAttribute("houses", houses);
        return "houses"; // Thymeleaf template "houses.html"
    }

    @GetMapping("/house/{name}")
    public String getHouseDetails(@PathVariable String name, Model model) {
        model.addAttribute("house", houseService.getHouseByName(name));
        return "house-details"; // Thymeleaf template "house-details.html"
    }
}
