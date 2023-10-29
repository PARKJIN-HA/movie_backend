package com.parkjinha.movie_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = {"com.parkjinha.movie_backend", "com.parkjinha.movie_backend.repository"})
@EntityScan(basePackages = {"com.parkjinha.movie_backend.entity"})
public class MovieBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieBackendApplication.class, args);
    }

}
