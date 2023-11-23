package com.parkjinha.movie_backend.service;

import com.parkjinha.movie_backend.entity.Movie;
import com.parkjinha.movie_backend.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }

    public void saveDataToDB(Movie movie) throws Exception {
        movieRepository.save(movie);
    }

}
