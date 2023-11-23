package com.parkjinha.movie_backend.repository;

import com.parkjinha.movie_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
