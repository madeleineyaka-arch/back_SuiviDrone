package com.example.back_suividrone.repository;

import com.example.back_suividrone.entity.Mission;
import com.example.back_suividrone.entity.TypeMission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MissionRepository extends JpaRepository<Mission, Long> {


    List<Mission> findByTypeMission(TypeMission typeMission);

}