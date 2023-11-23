package com.parkjinha.movie_backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@Table(name = "Reservation")
@ToString
@Entity
@Data
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ReservationId")
    private Integer reservationId;

    @JoinColumn(name = "UserId")
    @OneToMany
    private List<User> userId;

    @Column(name = "PeopleNumber")
    private Integer peopleNumber;
}
