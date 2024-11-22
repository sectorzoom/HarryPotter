package org.example.harrypotter.services;

import org.example.harrypotter.entities.House;
import org.example.harrypotter.entities.Student;
import org.example.harrypotter.repositories.HouseRepository;
import org.example.harrypotter.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseServiceImplementation implements HouseService {

    private final HouseRepository houseRepository;
    private final StudentRepository studentRepository;

    public HouseServiceImplementation(HouseRepository houseRepository, StudentRepository studentRepository) {
        this.houseRepository = houseRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public List<House> getHouses() {
        return houseRepository.getHouses();
    }

    @Override
    public House getHouseByName(String name) {
        return houseRepository.getHouses()
                .stream()
                .filter(house -> house.getName().equals(name))
                .findFirst()
                .orElse(null);
    }


    @Override
    public void addHouse(House house) {
        houseRepository.addHouse(house);
    }

    @Override
    public void updateHouse(String oldName, House house) {
        // Actualiza la casa en el repositorio
        houseRepository.updateHouse(oldName, house);
        // Actualiza a los estudiantes asociados
        List<Student> students = studentRepository.getStudentsByHouse(oldName);
        for (Student student : students) {
            student.getHouse().setName(house.getName());
        }
        // Guarda los estudiantes actualizados (si es necesario)
        studentRepository.saveAll(students);
    }

    @Override
    public void deleteHouse(String name) {
        houseRepository.deleteHouse(name);
    }
}

