package com.example.gym.dao;

import com.example.gym.model.Training;

import java.util.List;

public interface TrainingDao extends CrudDao<Training> {

    List<Training> findByTrainee(Long traineeId);

    List<Training> findByTrainer(Long trainerId);
}
