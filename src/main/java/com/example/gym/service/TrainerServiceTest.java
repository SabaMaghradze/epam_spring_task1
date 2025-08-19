package com.example.gym.service;

import com.example.gym.dao.TrainerDao;

import java.util.List;

import com.example.gym.model.Trainer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;


public class TrainerServiceTest {

    @Test
    void createsUsernameAndPasswordAndId() {
        TrainerDao dao = mock(TrainerDao.class);
        when(dao.findByName("John","Doe")).thenReturn(List.of()); // no duplicates

        TrainerService svc = new TrainerService();
        svc.setTrainerDao(dao);

        Trainer t = new Trainer();
        t.setFirstName("John");
        t.setLastName("Doe");

        Trainer created = svc.create(t);

        assertNotNull(created.getId());
        assertEquals("John.Doe", created.getUsername());
        assertNotNull(created.getPassword());
        assertEquals(10, created.getPassword().length());
        verify(dao).create(created.getId(), created);
    }

    @Test
    void appendsSerialWhenDuplicateNameExists() {
        TrainerDao dao = mock(TrainerDao.class);
        when(dao.findByName("John","Doe")).thenReturn(List.of(new Trainer(), new Trainer()));

        TrainerService trainerService = new TrainerService();
        trainerService.setTrainerDao(dao);

        Trainer t = new Trainer();
        t.setFirstName("John"); t.setLastName("Doe");

        Trainer created = trainerService.create(t);
        assertTrue(created.getUsername().startsWith("John.Doe"));
        assertTrue(created.getUsername().matches("John\\.Doe\\d+"));
    }
}
