package com.example.gym;

import com.example.gym.config.AppConfig;
import com.example.gym.facade.GymFacade;
import com.example.gym.model.Trainee;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;

public class App {
    public static void main(String[] args) {
        try (var ctx = new AnnotationConfigApplicationContext(AppConfig.class)) {
            GymFacade f = ctx.getBean(GymFacade.class);

            Trainee t = new Trainee();
            t.setFirstName("John");
            t.setLastName("Doe");
            t.setDateOfBirth(LocalDate.of(2000,1,1));
            t.setAddress("Some Street 1");

            f.trainees().create(t); // username John.Doe or John.Doe2 if duplicates present
            System.out.println(f.trainees().list());
        }
    }
}
