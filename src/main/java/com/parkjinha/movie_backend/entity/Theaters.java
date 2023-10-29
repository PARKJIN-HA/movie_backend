package com.parkjinha.movie_backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Table(name = "theaters")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Theaters {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(columnDefinition = "TEXT",name = "theater_name")
    private String theater_name;

    @Column(name = "seat_available")
    private Integer seat_available;

    @Column(name = "seat_total")
    private Integer seat_total;
}
