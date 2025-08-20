package com.example.gym.storage;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryStorage<T> {

    private final Map<Long, T> data = new ConcurrentHashMap<>();

    public Map<Long, T> getData () {
        return data;
    }
}
