package com.example.gym.facade;

import com.example.gym.service.TraineeService;
import com.example.gym.service.TrainerService;
import com.example.gym.service.TrainingService;

public class GymFacade {

    private final TraineeService traineeService;
    private final TrainerService trainerService;
    private final TrainingService trainingService;

    public GymFacade(TraineeService ts, TrainerService rs, TrainingService trns) {
        this.traineeService = ts;
        this.trainerService = rs;
        this.trainingService = trns;
    }

    public TraineeService trainees() {
        return traineeService;
    }

    public TrainerService trainers() {
        return trainerService;
    }

    public TrainingService trainings() {
        return trainingService;
    }

}
