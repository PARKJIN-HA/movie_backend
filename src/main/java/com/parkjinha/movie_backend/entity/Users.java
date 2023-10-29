package com.parkjinha.movie_backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Users")
@Data
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(columnDefinition = "TEXT",name = "username")
    private String username;

    @Column(columnDefinition = "TEXT",name = "password")
    private String password;

    @Column(columnDefinition = "TEXT",name = "email")
    private String email;

    public Users(Integer id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Users() {

    }
}
