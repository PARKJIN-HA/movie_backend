package com.parkjinha.movie_backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Table(name = "Theater")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Theater {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TheaterId")
    private Integer theaterId;

    @Column(columnDefinition = "TEXT",name = "TheaterName")
    private String theaterName;

    @Column(name = "SeatAvailable")
    private Integer seatAvailable;

    @Column(name = "SeatTotal")
    private Integer seatTotal;
}
