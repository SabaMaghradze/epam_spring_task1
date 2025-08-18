package com.example.gym.dao;

import com.example.gym.model.Trainer;
import com.example.gym.storage.InMemoryStorage;

import java.util.List;
import java.util.stream.Collectors;

public class TrainerMapDao extends MapCrudDao<Trainer> implements TrainerDao {

    private InMemoryStorage<Trainer> storage;

    public void setStorage(InMemoryStorage<Trainer> storage) {
        this.storage = storage;
        super.setStorage(storage);
    }

    @Override
    public List<Trainer> findByName(String first, String last) {
        return storage.getData().values().stream()
                .filter(t -> first.equalsIgnoreCase(t.getFirstName()) &&
                        last.equalsIgnoreCase(t.getLastName()))
                .collect(Collectors.toList());
    }
}
