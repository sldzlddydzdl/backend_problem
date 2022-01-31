package com.example.backend_problem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BackendProblemApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendProblemApplication.class, args);
    }

}
