package com.example.gym.dao;

import com.example.gym.model.Trainer;

import java.util.List;

public interface TrainerDao extends CrudDao<Trainer> {
    List<Trainer> findByName(String first, String last);
}
