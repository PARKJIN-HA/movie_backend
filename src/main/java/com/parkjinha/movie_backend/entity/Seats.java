package com.parkjinha.movie_backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Table(name = "seats")
@Entity
@Data
public class Seats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name= "rowNum")
    private Integer rowNum;

    @Column(name= "columnNum")
    private Integer columnNum;

    @Column(name="price")
    private Integer price;

    @JoinColumn(name="theater_id")
    @OneToMany(cascade = CascadeType.ALL)
    private List<Theaters> theater_id;

    @Column(name="reservation_check")
    private Integer reservation_check;
}
