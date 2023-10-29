package com.parkjinha.movie_backend.entity;


import jakarta.persistence.*;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Table(name = "movies")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(columnDefinition = "TEXT",name = "movie_name")
    private String movie_name;

    @Column(columnDefinition = "TEXT",name = "director")
    private String director;

    @Column(columnDefinition = "TEXT",name = "poster")
    private String poster;

    @Column(name = "release_date")
    private Date release_date;

    @Column(name = "rate")
    private Integer rate;

    @Column(name = "running_time")
    private Integer running_time;


}
