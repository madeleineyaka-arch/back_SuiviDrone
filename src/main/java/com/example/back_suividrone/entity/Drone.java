package com.example.back_suividrone.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Entity
@Table(name = "drones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Drone {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @Column(nullable = false, length = 100)
    private String nom;



    @Column(nullable = false, unique = true, length = 100)
    private String numeroSerie;



    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeDrone type;



    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EtatDrone etat = EtatDrone.DISPONIBLE;



    @Column(nullable = false)
    private LocalDate dateMiseService;



    @Column(nullable = false)
    private Integer nombreMission = 0;


}