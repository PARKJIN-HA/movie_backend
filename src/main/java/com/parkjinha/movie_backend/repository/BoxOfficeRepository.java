package com.parkjinha.movie_backend.repository;

import com.parkjinha.movie_backend.entity.BoxOffice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface BoxOfficeRepository extends JpaRepository<BoxOffice, Integer> {
    List<BoxOffice> findByDate(Date targetDate);
}
