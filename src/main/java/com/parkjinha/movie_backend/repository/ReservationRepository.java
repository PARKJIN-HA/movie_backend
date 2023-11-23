package com.parkjinha.movie_backend.repository;

import com.parkjinha.movie_backend.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
}
