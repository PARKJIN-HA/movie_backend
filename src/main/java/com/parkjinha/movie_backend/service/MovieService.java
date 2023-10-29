package com.parkjinha.movie_backend.service;

import com.parkjinha.movie_backend.entity.Movies;
import com.parkjinha.movie_backend.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }

    public void saveDataToDB(Movies movies) throws Exception {
        movieRepository.save(movies);
    }
}
