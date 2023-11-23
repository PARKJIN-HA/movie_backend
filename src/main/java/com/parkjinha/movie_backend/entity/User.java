package com.parkjinha.movie_backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "User")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserId")
    private Integer UserId;

    @Column(columnDefinition = "TEXT",name = "UserName")
    private String username;

    @Column(columnDefinition = "TEXT",name = "Password")
    private String password;

    @Column(columnDefinition = "TEXT",name = "Email")
    private String email;

    public User(Integer UserId, String username, String password, String email) {
        this.UserId = UserId;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User() {

    }
}
