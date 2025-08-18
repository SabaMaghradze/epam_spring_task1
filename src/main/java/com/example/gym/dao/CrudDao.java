package com.example.gym.dao;

import java.util.List;
import java.util.Optional;

public interface CrudDao<T> {

    T create(Long id, T entity);

    T update(Long id, T entity);

    void delete(Long id);

    Optional<T> findById(Long id);

    List<T> findAll();
}
