package com.example.gym.service;

import com.example.gym.dao.TrainingDao;
import com.example.gym.model.Training;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class TrainingService {

    private static final Logger log = LoggerFactory.getLogger(TrainingService.class);
    private final AtomicLong idSeq = new AtomicLong(1);
    private TrainingDao trainingDao;

    public void setTrainingDao(TrainingDao trainingDao) { this.trainingDao = trainingDao; }

    public Training create(Training t) {
        t.setId(idSeq.getAndIncrement());
        trainingDao.create(t.getId(), t);
        log.info("Created Training id={} for trainee={} trainer={}", t.getId(), t.getTraineeId(), t.getTrainerId());
        return t;
    }

    public Training get(Long id) {
        return trainingDao.findById(id).orElse(null);
    }

    public List<Training> listByTrainee(Long traineeId) {
        return trainingDao.findByTrainee(traineeId);
    }

    public List<Training> listByTrainer(Long trainerId) {
        return trainingDao.findByTrainer(trainerId);
    }
}
