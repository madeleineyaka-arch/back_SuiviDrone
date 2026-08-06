package com.example.back_suividrone.dto;

import lombok.Data;

import java.sql.Time;
import java.time.LocalDate;


@Data
public class MissionResponseDTO {


    private Long id;


    private String titre;


    private String objectif;


    private String typeMission;


    private LocalDate dateMission;


    private Time heureDebut;


    private Time heureFin;


    private String lieu;


    private String statut;



    private Long droneId;

    private String droneNom;



    private Long piloteId;

    private String piloteNom;

    private String pilotePrenom;

    private String piloteEmail;

}