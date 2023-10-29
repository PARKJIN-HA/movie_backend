package com.parkjinha.movie_backend.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Table(name = "tickets")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tickets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @JoinColumn(name = "seat_id")
    @OneToOne
    private Seats seatId;

    @JoinColumn(name = "schedule_id")
    @OneToMany
    private List<RunSchedules> scheduleId;

    @Column(name = "reservation_id")
    private Integer reservationId;

    @Column(name = "reservation_time")
    private String reservationTime;
}
