package com.parkjinha.movie_backend.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Table(name = "Ticket")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TicketId")
    private Integer ticketId;

    @JoinColumn(name = "SeatId")
    @OneToOne
    private Seat seatId;

    @JoinColumn(name = "ScheduleId")
    @OneToMany
    private List<RunSchedule> scheduleId;

    @Column(name = "ReservationId")
    private Integer reservationId;

    @Column(name = "ReservationTime")
    private String reservationTime;
}
