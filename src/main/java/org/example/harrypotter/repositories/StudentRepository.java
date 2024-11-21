package org.example.harrypotter.repositories;

import lombok.Getter;
import org.example.harrypotter.entities.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Repository
public class StudentRepository {
    private final List<Student> students;
    public StudentRepository() {
        this.students = new ArrayList<>();
        HouseRepository houseRepository = new HouseRepository();
        students.add(new Student("Harry Potter", houseRepository.getHouse("Gryffindor"), "Stag"));
        students.add(new Student("Hermione Granger", houseRepository.getHouse("Gryffindor"), "Otter"));
        students.add(new Student("Ron Weasley", houseRepository.getHouse("Gryffindor"), "Jack Russell Terrier"));
        students.add(new Student("Draco Malfoy", houseRepository.getHouse("Slytherin"), "None"));
        students.add(new Student("Luna Lovegood", houseRepository.getHouse("Ravenclaw"), "Hare"));
        students.add(new Student("Cedric Diggory", houseRepository.getHouse("Hufflepuff"), "Labrador"));
        students.add(new Student("Cho Chang", houseRepository.getHouse("Ravenclaw"), "Swan"));
        students.add(new Student("Neville Longbottom", houseRepository.getHouse("Gryffindor"), "None"));
        students.add(new Student("Ginny Weasley", houseRepository.getHouse("Gryffindor"), "Horse"));
        students.add(new Student("Seamus Finnigan", houseRepository.getHouse("Gryffindor"), "Fox"));
        students.add(new Student("Pansy Parkinson", houseRepository.getHouse("Slytherin"), "None"));
    }

    public Student getStudent(String name) {
        for (Student student : students) {
            if (student.getName().equals(name)) {
                return student;
            }
        }
        return null;
    }

    public List<Student> getStudentsByHouse(String houseName) {
        List<Student> studentsByHouse = new ArrayList<>();
        for (Student student : students) {
            if (student.getHouse().getName().equals(houseName)) {
                studentsByHouse.add(student);
            }
        }
        return studentsByHouse;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void updateStudent(Student student, String name){
        for(Student stud:students){
            if (stud.getName().equals(name)) {
                stud.setHouse(student.getHouse());
                stud.setName(student.getName());
                stud.setPatronus(student.getPatronus());
            }
        }
    }

    public void saveAll(List<Student> updatedStudents) {
        for (Student student : updatedStudents) {
            for (int i = 0; i < students.size(); i++) {
                if (students.get(i).getName().equals(student.getName())) {
                    students.set(i, student);
                }
            }
        }
    }

}
