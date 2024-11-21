package org.example.harrypotter.repositories;

import lombok.Getter;
import org.example.harrypotter.entities.House;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Getter
@Repository
public class HouseRepository {
    private List<House> houses = new ArrayList<>();

    public HouseRepository() {
        houses.add(new House("Gryffindor", "Lion", "Godric Gryffindor"));
        houses.add(new House("Hufflepuff", "Badger", "Helga Hufflepuff"));
        houses.add(new House("Ravenclaw", "Eagle", "Rowena Ravenclaw"));
        houses.add(new House("Slytherin", "Serpent", "Salazar Slytherin"));
    }

    public House getHouse(String name) {
        for (House house : houses) {
            if (house.getName().equals(name)) {
                return house;
            }
        }
        return null;
    }

    public void addHouse(House house) {
        houses.add(house);
    }

    public void updateHouse(String name,House house) {
        for (int i = 0; i < houses.size(); i++) {
            if (houses.get(i).getName().equals(name)) {
                houses.set(i, house);
                break;
            }
        }
    }

    public void deleteHouse(String name) {
        houses.removeIf(house->house.getName().equals(name));
    }
}
