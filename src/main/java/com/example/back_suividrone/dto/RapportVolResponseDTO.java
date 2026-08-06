package com.example.back_suividrone.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;


@Getter
@Setter
@AllArgsConstructor
public class RapportVolResponseDTO {


    private Long id;


    private LocalDate dateVol;


    private String lieuZoneVol;


    private String scenario;


    private LocalTime heureDecollage;


    private LocalTime heureAtterrissage;


    private Integer tempsVol;


    private String dureeVol;


    private String chargeUtile;


    private String objectifMission;


    private String observations;


    private String incidents;


    private String visa;



    // Informations simplifiées

    private Long droneId;

    private String droneNom;



    private Long piloteId;

    private String piloteNom;



    private Long missionId;

    private String missionTitre;

}