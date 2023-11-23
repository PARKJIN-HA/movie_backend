package com.parkjinha.movie_backend.repository;

import com.parkjinha.movie_backend.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    Movie findByMovieName(String movieName);
}
