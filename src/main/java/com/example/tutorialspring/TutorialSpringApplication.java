package com.example.tutorialspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.example.tutorialspring.persistence.repo")
@EntityScan("com.example.tutorialspring.persistence.model")
@SpringBootApplication
public class TutorialSpringApplication {

    public static void main(String[] args) {

        SpringApplication.run(TutorialSpringApplication.class, args);
    }

}

