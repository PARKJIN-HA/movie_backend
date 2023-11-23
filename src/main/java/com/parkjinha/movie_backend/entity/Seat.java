package com.parkjinha.movie_backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

@Table(name = "Seat")
@Entity
@Data
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SeatID")
    private Integer seatId;
    @Column(name= "RowNum")
    private Integer rowNum;

    @Column(name= "ColumnNum")
    private Integer columnNum;

    @Column(name="Price")
    private Integer price;

    @JoinColumn(name="TheaterId")
    @OneToMany(cascade = CascadeType.ALL)
    private List<Theater> theaterId;

    @Column(name="ReservationCheck")
    private Integer reservationCheck;
}
