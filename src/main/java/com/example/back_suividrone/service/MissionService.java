package com.example.back_suividrone.service;


import com.example.back_suividrone.dto.MissionResponseDTO;
import com.example.back_suividrone.entity.Mission;
import com.example.back_suividrone.entity.TypeMission;


import java.util.List;



public interface MissionService {


    MissionResponseDTO saveMission(Mission mission);


    List<MissionResponseDTO> getAllMissions();


    MissionResponseDTO findById(Long id);


    List<MissionResponseDTO> findByTypeMission(TypeMission typeMission);


    MissionResponseDTO updateMission(
            Long id,
            Mission mission
    );


    void deleteMission(Long id);


}