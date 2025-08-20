package com.example.gym.config;

import com.example.gym.dao.*;
import com.example.gym.facade.GymFacade;
import com.example.gym.service.TraineeService;
import com.example.gym.service.TrainerService;
import com.example.gym.service.TrainingService;
import com.example.gym.storage.InMemoryStorage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource("classpath:application.properties")
public class AppConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer pspc() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public InMemoryStorage<com.example.gym.model.Trainee> traineeStorage() {
        return new InMemoryStorage<>();
    }

    @Bean
    public InMemoryStorage<com.example.gym.model.Trainer> trainerStorage() {
        return new InMemoryStorage<>();
    }

    @Bean
    public InMemoryStorage<com.example.gym.model.Training> trainingStorage() {
        return new InMemoryStorage<>();
    }

    @Bean
    public TraineeDao traineeDao(InMemoryStorage<com.example.gym.model.Trainee> storage) {
        TraineeMapDao dao = new TraineeMapDao();
        dao.setStorage(storage);
        return dao;
    }

    @Bean
    public TrainerDao trainerDao(InMemoryStorage<com.example.gym.model.Trainer> storage) {
        TrainerMapDao dao = new TrainerMapDao();
        dao.setStorage(storage);
        return dao;
    }

    @Bean
    public TrainingDao trainingDao(InMemoryStorage<com.example.gym.model.Training> storage) {
        TrainingMapDao dao = new TrainingMapDao();
        dao.setStorage(storage);
        return dao;
    }

    @Bean
    public TraineeService traineeService(TraineeDao dao) {
        TraineeService s = new TraineeService();
        s.setTraineeDao(dao);
        return s;
    }

    @Bean
    public TrainerService trainerService(TrainerDao dao) {
        TrainerService s = new TrainerService();
        s.setTrainerDao(dao);
        return s;
    }

    @Bean
    public TrainingService trainingService(TrainingDao dao) {
        TrainingService s = new TrainingService();
        s.setTrainingDao(dao);
        return s;
    }

    @Bean
    public GymFacade gymFacade(TraineeService a, TrainerService b, TrainingService c) {
        return new GymFacade(a, b, c);
    }

    @Bean
    public static StorageInitPostProcessor storageInitPostProcessor() {
        return new StorageInitPostProcessor();
    }
}
