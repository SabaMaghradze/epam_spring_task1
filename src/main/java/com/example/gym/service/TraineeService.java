package com.example.gym.service;

import com.example.gym.dao.TraineeDao;
import com.example.gym.model.Trainee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class TraineeService {

    private static final Logger log = LoggerFactory.getLogger(TraineeService.class);
    private final AtomicLong idSeq = new AtomicLong(1);

    private TraineeDao traineeDao;

    public void setTraineeDao(TraineeDao traineeDao) {
        this.traineeDao = traineeDao;
    }

    private String generateUniqueUsername(String first, String last) {
        String base = UsernamePasswordUtil.baseUsername(first, last);
        List<Trainee> same = traineeDao.findByName(first, last);
        if (same.isEmpty()) return base;
        return base + (same.size()+1); // John.Smith2, Smith3, etc.
    }

    public Trainee create(Trainee t) {
        t.setId(idSeq.getAndIncrement());
        t.setUsername(generateUniqueUsername(t.getFirstName(), t.getLastName()));
        t.setPassword(UsernamePasswordUtil.randomPassword10());
        traineeDao.create(t.getId(), t);
        log.info("Created Trainee id={} username={}", t.getId(), t.getUsername());
        return t;
    }

    public Trainee update(Long id, Trainee t) {
        t.setId(id);
        traineeDao.update(id, t);
        log.info("Updated Trainee id={}", id);
        return t;
    }

    public void delete(Long id) {
        traineeDao.delete(id);
        log.info("Deleted Trainee id={}", id);
    }

    public Trainee get(Long id) { return traineeDao.findById(id).orElse(null); }
    public List<Trainee> list() { return traineeDao.findAll(); }


}
