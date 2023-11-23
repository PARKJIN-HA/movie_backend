package com.parkjinha.movie_backend.repository;

import com.parkjinha.movie_backend.entity.RunSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RunScheduleRepository extends JpaRepository<RunSchedule, Integer> {
}
