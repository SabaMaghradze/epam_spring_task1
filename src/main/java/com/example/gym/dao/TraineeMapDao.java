package com.example.gym.dao;

import com.example.gym.model.Trainee;
import com.example.gym.storage.InMemoryStorage;

import java.util.List;
import java.util.stream.Collectors;

public class TraineeMapDao extends MapCrudDao<Trainee> implements TraineeDao {

    private InMemoryStorage<Trainee> storage;

    public void setStorage(InMemoryStorage<Trainee> storage) {
        this.storage = storage;
        super.setStorage(storage);
    }

    @Override
    public List<Trainee> findByName(String first, String last) {
        return storage.getData().values().stream()
                .filter(t -> first.equalsIgnoreCase(t.getFirstName())
                        && last.equalsIgnoreCase(t.getLastName()))
                .collect(Collectors.toList());
    }
}
