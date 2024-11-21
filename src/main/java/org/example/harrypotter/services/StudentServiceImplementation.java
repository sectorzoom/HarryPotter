package org.example.harrypotter.services;

import org.example.harrypotter.entities.Student;
import org.example.harrypotter.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImplementation implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImplementation(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getStudents() {
        return studentRepository.getStudents();
    }

    @Override
    public Student getStudentByName(String name) {
        return studentRepository.getStudent(name);
    }

    @Override
    public List<Student> getStudentsByHouse(String house) {
        return studentRepository.getStudentsByHouse(house);
    }

    @Override
    public void addStudent(Student student) {
        studentRepository.addStudent(student);
    }

    @Override
    public void updateStudent(Student student, String name) {
        studentRepository.updateStudent(student, name); // Llama directamente al método del repositorio
    }

    @Override
    public void deleteStudent(String name) {
        studentRepository.getStudents().removeIf(student -> student.getName().equals(name));
    }
    @Override
    public List<Student> getAllStudents() {
        return studentRepository.getStudents();
    }


}
