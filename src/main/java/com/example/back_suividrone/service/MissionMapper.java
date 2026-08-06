package com.example.back_suividrone.service;


import com.example.back_suividrone.dto.MissionResponseDTO;
import com.example.back_suividrone.entity.Mission;

import org.springframework.stereotype.Component;



@Component
public class MissionMapper {



    public MissionResponseDTO toDTO(Mission mission){


        MissionResponseDTO dto = new MissionResponseDTO();



        dto.setId(mission.getId());


        dto.setTitre(
                mission.getTitre()
        );


        dto.setObjectif(
                mission.getObjectif()
        );



        dto.setTypeMission(
                mission.getTypeMission().name()
        );



        dto.setDateMission(
                mission.getDateMission()
        );



        dto.setHeureDebut(
                mission.getHeureDebut()
        );


        dto.setHeureFin(
                mission.getHeureFin()
        );


        dto.setLieu(
                mission.getLieu()
        );



        dto.setStatut(
                mission.getStatut().name()
        );



        if(mission.getDrone()!=null){

            dto.setDroneId(
                    mission.getDrone().getId()
            );


            dto.setDroneNom(
                    mission.getDrone().getNom()
            );

        }



        if(mission.getPilote()!=null){


            dto.setPiloteId(
                    mission.getPilote().getId()
            );


            dto.setPiloteNom(
                    mission.getPilote().getNom()
            );


            dto.setPilotePrenom(
                    mission.getPilote().getPrenom()
            );


            dto.setPiloteEmail(
                    mission.getPilote().getEmail()
            );

        }



        return dto;

    }


}