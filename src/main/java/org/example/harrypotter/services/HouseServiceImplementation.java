package org.example.harrypotter.services;

import org.example.harrypotter.entities.House;
import org.example.harrypotter.repositories.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseServiceImplementation implements HouseService {

    private final HouseRepository houseRepository;

    public HouseServiceImplementation(HouseRepository houseRepository) {
        this.houseRepository = houseRepository;
    }

    @Override
    public List<House> getHouses() {
        return houseRepository.getHouses();
    }

    @Override
    public House getHouseByName(String name) {
        return houseRepository.getHouse(name);
    }
}

