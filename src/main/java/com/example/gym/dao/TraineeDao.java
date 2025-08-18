package com.example.gym.dao;

import com.example.gym.model.Trainee;

import java.util.List;

public interface TraineeDao extends CrudDao<Trainee> {

    List<Trainee> findByName(String first, String last);
}
