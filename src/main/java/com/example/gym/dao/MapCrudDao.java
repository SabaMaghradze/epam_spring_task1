package com.example.gym.dao;

import com.example.gym.storage.InMemoryStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MapCrudDao<T> implements CrudDao<T> {

    private InMemoryStorage<T> storage;

    public void setStorage(InMemoryStorage<T> storage) {
        this.storage = storage;
    }

    @Override
    public T create(Long id, T entity) {
        storage.getData().put(id, entity);
        return entity;
    }

    @Override
    public T update(Long id, T entity) {
        storage.getData().put(id, entity);
        return entity;
    }

    @Override
    public void delete(Long id) {
        storage.getData().remove(id);
    }

    @Override
    public Optional<T> findById(Long id) {
        return Optional.ofNullable(storage.getData().get(id));
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(storage.getData().values());
    }
}
