package com.parkjinha.movie_backend.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Table(name = "runSchedules")
@Entity
@Data
public class RunSchedules {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "movie_id")
    private List<Movies> movieId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "theater_id")
    private Theaters theaterId;

    @Column(name = "start_time")
    private String startTime;
}
