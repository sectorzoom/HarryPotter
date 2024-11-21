package org.example.harrypotter.controllers;

import org.example.harrypotter.entities.House;
import org.example.harrypotter.services.HouseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
        return "houses";
    }

    @GetMapping("/house/{name}")
    public String getHouseDetails(@PathVariable String name, Model model) {
        House house = houseService.getHouseByName(name);
        model.addAttribute("house", house);
        return "house-details";
    }

    @GetMapping("/houses/create")
    public String createHouse(Model model) {
        model.addAttribute("house", new House());
        return "house-create";
    }
    @PostMapping("/houses/create")
    public String createHouse(@ModelAttribute("house") House house) {
        houseService.addHouse(house);
        return "redirect:/houses";
    }


    @GetMapping("/house/update/{name}")
    public String updateHouse(@PathVariable String name, Model model) {
        House house = houseService.getHouseByName(name);
        if (house == null) {
            throw new IllegalArgumentException("House not found");
        }
        model.addAttribute("house", house); // Pasar el objeto House al modelo
        return "house-update"; // Renderiza el template house-update.html
    }

    @PostMapping("/house/update/{name}")
    public String updateHouse(@PathVariable String name, @ModelAttribute House house) {
        houseService.updateHouse(name, house); // Actualiza la casa y los estudiantes asociados
        return "redirect:/houses";
    }



    @GetMapping("/house/delete/{name}")
    public String deleteHouse(@PathVariable String name) {
        houseService.deleteHouse(name);
        return "redirect:/houses";
    }

}
