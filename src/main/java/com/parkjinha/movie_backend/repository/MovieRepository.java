package com.parkjinha.movie_backend.repository;

import com.parkjinha.movie_backend.entity.Movies;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends CrudRepository<Movies, Integer> {

}
