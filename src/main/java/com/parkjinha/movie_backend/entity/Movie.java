package com.parkjinha.movie_backend.entity;


import jakarta.persistence.*;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Table(name = "Movie")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MovieId")
    private Integer movieId;

    @Column(columnDefinition = "TEXT",name = "MovieName")
    private String movieName;

    @Column(columnDefinition = "TEXT",name = "Director")
    private String director;

    @Column(columnDefinition = "TEXT",name = "Poster")
    private String poster;

    @Column(name = "ReleaseDate")
    private Date releaseDate;

    @Column(name = "Rate")
    private String rate;

    @Column(name = "RunningTime")
    private Integer runningTime;

}
