package com.example.back_suividrone.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.time.LocalDate;


@Entity
@Table(name = "mission")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Mission {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false, length = 100)
    private String titre;


    @Column(nullable = false, length = 255)
    private String objectif;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeMission typeMission;


    @Column(nullable = false)
    private LocalDate dateMission;


    @Column(nullable = false)
    private Time heureDebut;


    @Column(nullable = false)
    private Time heureFin;


    @Column(nullable = false, length = 100)
    private String lieu;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatutMission statut = StatutMission.PLANIFIEE;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "drone_id", nullable = false)
    private Drone drone;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pilote_id", nullable = false)
    private Utilisateur pilote;

}