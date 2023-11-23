package com.parkjinha.movie_backend.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@Table(name="BoxOffice")
public class BoxOffice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="BoxOfficeId")
    private Integer boxOfficeId;

    @Column(name="Date")
    private Date date;

    @Column(name="Rank")
    private Integer rank;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MovieId")
    private Movie movie;

}
