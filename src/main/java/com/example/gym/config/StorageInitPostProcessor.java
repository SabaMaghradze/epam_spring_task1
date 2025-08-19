package com.example.gym.config;

import com.example.gym.model.Trainee;
import com.example.gym.model.Trainer;
import com.example.gym.storage.InMemoryStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicLong;


public class StorageInitPostProcessor implements BeanPostProcessor {

    private static final Logger log = LoggerFactory.getLogger(StorageInitPostProcessor.class);

    @Value("${init.data.file}")
    private String initDataFile;

    private final ResourceLoader loader = new DefaultResourceLoader();
    private final AtomicLong traineeId = new AtomicLong(100);
    private final AtomicLong trainerId = new AtomicLong(200);

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        try {
            if ("traineeStorage".equals(beanName)) {
                InMemoryStorage<Trainee> st = (InMemoryStorage<Trainee>) bean;
                loadTrainees(st);
            } else if ("trainerStorage".equals(beanName)) {
                InMemoryStorage<Trainer> st = (InMemoryStorage<Trainer>) bean;
                loadTrainers(st);
            }
        } catch (Exception e) {
            log.warn("Init data load failed for {}: {}", beanName, e.getMessage());
        }
        return bean;
    }

    private void loadTrainees(InMemoryStorage<Trainee> st) throws Exception {
        Resource r = loader.getResource(initDataFile);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(r.getInputStream()))) {
            br.lines().filter(l -> l.startsWith("TRAINEE")).forEach(l -> {
                String[] a = l.split(",", -1);
                Trainee t = new Trainee();
                t.setId(traineeId.getAndIncrement());
                t.setFirstName(a[2]); t.setLastName(a[3]);
                t.setDateOfBirth(LocalDate.parse(a[4]));
                t.setAddress(a[5]);
                t.setUsername(a[2]+"."+a[3]); // base; real service will generate when creating new ones
                t.setPassword("initInit00");
                st.getData().put(t.getId(), t);
            });
        }
    }

    private void loadTrainers(InMemoryStorage<Trainer> st) throws Exception {
        Resource r = loader.getResource(initDataFile);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(r.getInputStream()))) {
            br.lines().filter(l -> l.startsWith("TRAINER")).forEach(l -> {
                String[] a = l.split(",", -1);
                Trainer t = new Trainer();
                t.setId(trainerId.getAndIncrement());
                t.setFirstName(a[2]); t.setLastName(a[3]);
                t.setSpecialization(a[4]);
                t.setUsername(a[2]+"."+a[3]);
                t.setPassword("initInit00");
                st.getData().put(t.getId(), t);
            });
        }
    }
}
