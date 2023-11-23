package com.parkjinha.movie_backend;

import com.parkjinha.movie_backend.utils.ApiKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = {"com.parkjinha.movie_backend", "com.parkjinha.movie_backend.repository"})
@EntityScan(basePackages = {"com.parkjinha.movie_backend.entity"})
@EnableConfigurationProperties(ApiKeyProperties.class)
public class MovieBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieBackendApplication.class, args);
    }

}
