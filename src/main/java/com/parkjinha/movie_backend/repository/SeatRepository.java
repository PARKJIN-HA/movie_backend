package com.parkjinha.movie_backend.repository;

import com.parkjinha.movie_backend.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Integer> {
}
