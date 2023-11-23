package com.parkjinha.movie_backend.repository;

import com.parkjinha.movie_backend.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
}
