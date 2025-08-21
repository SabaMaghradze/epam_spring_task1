package com.example.gym.service;

import com.example.gym.dao.TrainerDao;
import com.example.gym.model.Trainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class TrainerService {

    private static final Logger log = LoggerFactory.getLogger(TrainerService.class);
    private final AtomicLong idSeq = new AtomicLong(1);
    private TrainerDao trainerDao;

    public void setTrainerDao(TrainerDao trainerDao) {
        this.trainerDao = trainerDao;
    }

    public Trainer create(Trainer t) {
        t.setId(idSeq.getAndIncrement());
        t.setUsername(UsernamePasswordUtil.generateUniqueUsername(
                t.getFirstName(),
                t.getLastName(),
                () -> trainerDao.findByName(t.getFirstName(), t.getLastName())
        ));
        t.setPassword(UsernamePasswordUtil.randomPassword10());
        trainerDao.create(t.getId(), t);
        log.info("Created Trainer id={} username={}", t.getId(), t.getUsername());
        return t;
    }

    public Trainer update(Long id, Trainer t) {
        t.setId(id);
        trainerDao.update(id, t);
        log.info("Updated Trainer id={}", id);
        return t;
    }

    public Trainer get(Long id) {
        return trainerDao.findById(id).orElse(null);
    }

    public List<Trainer> list() {
        return trainerDao.findAll();
    }
}
