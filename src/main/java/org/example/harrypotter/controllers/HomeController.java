package org.example.harrypotter.controllers;

import org.example.harrypotter.entities.House;
import org.example.harrypotter.entities.Student;
import org.example.harrypotter.services.HouseService;
import org.example.harrypotter.services.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Random;

@Controller
public class HomeController {

    private final HouseService houseService;
    private final StudentService studentService;

    public HomeController(HouseService houseService, StudentService studentService) {
        this.houseService = houseService;
        this.studentService = studentService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<House> houses = houseService.getHouses();
        List<Student> students = studentService.getStudents();
        Random random = new Random();
        House randomHouse = houses.get(random.nextInt(houses.size()));
        Student randomStudent = students.get(random.nextInt(students.size()));
        model.addAttribute("randomHouse", randomHouse);
        model.addAttribute("randomStudent", randomStudent);
        return "home";
    }
}
