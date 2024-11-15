package org.example.harrypotter.controllers;

import org.example.harrypotter.entities.Student;
import org.example.harrypotter.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/student")
    public String getStudentDetails(@RequestParam String name, Model model) {
        model.addAttribute("student", studentService.getStudentByName(name));
        return "student-details"; // Thymeleaf template "student-details.html"
    }

    @GetMapping("/house/{name}/students")
    public String getStudentsByHouse(@PathVariable String name, Model model) {
        List<Student> students = studentService.getStudentsByHouse(name);
        model.addAttribute("students", students);
        model.addAttribute("houseName", name);
        return "students-by-house"; // Thymeleaf template "students-by-house.html"
    }
}
