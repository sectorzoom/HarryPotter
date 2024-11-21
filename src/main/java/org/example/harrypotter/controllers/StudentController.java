package org.example.harrypotter.controllers;

import org.example.harrypotter.entities.House;
import org.example.harrypotter.entities.Student;
import org.example.harrypotter.services.HouseService;
import org.example.harrypotter.services.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StudentController {

    private final StudentService studentService;
    private final HouseService houseService;

    public StudentController(StudentService studentService, HouseService houseService) {
        this.studentService = studentService;
        this.houseService = houseService;

    }

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

    @GetMapping("/house/createStudent/{name}")
    public String createStudent(@PathVariable String name, Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("houseName", name); // Para identificar la casa
        return "student-create";
    }
    @PostMapping("/house/createStudent/{name}")
    public String createStudent(@PathVariable String name, @ModelAttribute Student student) {
        // Obtén la casa basada en el nombre de la URL
        House house = houseService.getHouseByName(name);
        if (house == null) {
            throw new IllegalArgumentException("House not found");
        }

        // Asocia la casa al estudiante
        student.setHouse(house);

        // Añade el estudiante al repositorio
        studentService.addStudent(student);

        // Redirige al listado de estudiantes de la casa
        return "redirect:/house/" + name + "/students";
    }



    @GetMapping("/student/update/{name}")
    public String updateStudent(@PathVariable String name, Model model) {
        Student student = studentService.getStudentByName(name);
        model.addAttribute("student", student);
        model.addAttribute("houses", houseService.getHouses());
        return "student-update";
    }
    @PostMapping("/student/update/{name}")
    public String updateStudent(@PathVariable String name, @ModelAttribute Student student, @RequestParam String houseName) {
        // Obtén el estudiante existente
        Student existingStudent = studentService.getStudentByName(name);

        // Actualiza los campos editables
        existingStudent.setName(student.getName()); // Actualiza el nombre del estudiante

        // Asocia la nueva casa
        House house = houseService.getHouseByName(houseName); // Busca la casa por nombre
        existingStudent.setHouse(house); // Asocia la casa al estudiante

        // Actualiza el patronus
        existingStudent.setPatronus(student.getPatronus());

        // Guarda los cambios
        studentService.updateStudent(existingStudent, name);

        // Redirige a los detalles del estudiante actualizado
        return "redirect:/student/" + student.getName();
    }
    @GetMapping("/students")
    public String listAllStudents(Model model) {
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students); // Pasar la lista de estudiantes al modelo
        return "students"; // Renderiza el template students.html
    }

    @GetMapping("/student/{name}")
    public String getStudentDetailsByName(@PathVariable String name, Model model) {
        model.addAttribute("student", studentService.getStudentByName(name));
        return "student-details"; // Thymeleaf template para mostrar los detalles
    }

    @GetMapping("/student/delete/{name}")
    public String deleteStudent(@PathVariable String name) {
        Student student = studentService.getStudentByName(name);
        if (student == null) {
            throw new IllegalArgumentException("Student not found");
        }
        studentService.deleteStudent(name);
        return "redirect:/house/" + student.getHouse().getName() + "/students";
    }



}
