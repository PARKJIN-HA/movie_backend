package com.parkjinha.movie_backend.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Table(name = "RunSchedule")
@Entity
@Data
public class RunSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RunId")
    private Integer runId;

    @ManyToOne
    @JoinColumn(name = "MovieId")
    Movie movieId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TheaterId")
    private Theater theaterId;

    @Column(name = "StartTime")
    private String startTime;
}
