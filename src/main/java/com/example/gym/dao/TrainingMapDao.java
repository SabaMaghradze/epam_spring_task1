package com.example.gym.dao;

import com.example.gym.model.Training;
import com.example.gym.storage.InMemoryStorage;

import java.util.List;
import java.util.stream.Collectors;

public class TrainingMapDao extends MapCrudDao<Training> implements TrainingDao {

    private InMemoryStorage<Training> storage;

    public void setStorage(InMemoryStorage<Training> storage) {
        this.storage = storage;
        super.setStorage(storage);
    }

    @Override
    public List<Training> findByTrainee(Long traineeId) {
        return storage.getData().values().stream()
                .filter(t -> traineeId.equals(t.getTraineeId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Training> findByTrainer(Long trainerId) {
        return storage.getData().values().stream()
                .filter(t -> trainerId.equals(t.getTrainerId()))
                .collect(Collectors.toList());
    }
}
