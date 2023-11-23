package com.parkjinha.movie_backend.repository;

import com.parkjinha.movie_backend.entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheaterRepository extends JpaRepository<Theater, Integer> {
}
