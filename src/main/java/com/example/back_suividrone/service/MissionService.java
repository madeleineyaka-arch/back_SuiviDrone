package com.example.back_suividrone.service;

import com.example.back_suividrone.entity.Mission;
import com.example.back_suividrone.entity.TypeMission;

import java.util.List;
import java.util.Optional;


public interface MissionService {


    Mission saveMission(Mission mission);


    List<Mission> findByTypeMission(TypeMission typeMission);


    Optional<Mission> findById(Long id);


    List<Mission> getAllMissions();


    void deleteMission(Long id);


    Mission updateMission(Long id, Mission mission);

}