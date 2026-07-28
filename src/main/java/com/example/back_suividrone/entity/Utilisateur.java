package com.example.back_suividrone.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "utilisateurs")
@Data
@NoArgsConstructor
@AllArgsConstructor


public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 100)
    private String name;

    @Column(nullable = false,unique = true,length = 100)
    private String username;

    @Column(nullable = false,unique = true, length = 150)
    private String email;

    @Column(nullable = false,length = 255)
    private String password;

    @Column(nullable = false,unique = true, length = 100)
    private String matricule;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,length = 100)
    private Grade grade;

    @Column(nullable = false)
    private Boolean actif = true;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,length = 50)
    private Role  role;



}
