package com.example.back_suividrone.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;


@Getter
@Setter
public class RapportVolRequestDTO {


    private LocalDate dateVol;


    private String lieuZoneVol;


    private String scenario;


    private LocalTime heureDecollage;


    private LocalTime heureAtterrissage;


    private String chargeUtile;


    private String objectifMission;


    private String observations;


    private String incidents;


    private String visa;


    private Long droneId;


    private Long piloteId;


    private Long missionId;

}