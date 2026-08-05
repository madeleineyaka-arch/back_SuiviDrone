package com.example.back_suividrone.service;


import com.example.back_suividrone.entity.Mission;
import com.example.back_suividrone.entity.TypeMission;
import com.example.back_suividrone.repository.MissionRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
public class MissionServiceImpl implements MissionService {


    private final MissionRepository missionRepository;



    @Override
    public Mission saveMission(Mission mission) {

        return missionRepository.save(mission);
    }



    @Override
    public List<Mission> findByTypeMission(TypeMission typeMission) {

        return missionRepository.findByTypeMission(typeMission);
    }



    @Override
    public Optional<Mission> findById(Long id) {

        return missionRepository.findById(id);
    }



    @Override
    public List<Mission> getAllMissions() {

        return missionRepository.findAll();
    }



    @Override
    public void deleteMission(Long id) {


        if(!missionRepository.existsById(id)){

            throw new RuntimeException(
                    "Mission introuvable"
            );
        }


        missionRepository.deleteById(id);

    }




    @Override
    public Mission updateMission(Long id, Mission mission) {


        Mission existante =
                missionRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Mission introuvable"
                                )
                        );


        existante.setTitre(mission.getTitre());

        existante.setObjectif(mission.getObjectif());

        existante.setTypeMission(mission.getTypeMission());

        existante.setDateMission(mission.getDateMission());

        existante.setHeureDebut(mission.getHeureDebut());

        existante.setHeureFin(mission.getHeureFin());

        existante.setLieu(mission.getLieu());

        existante.setStatut(mission.getStatut());

        existante.setDrone(mission.getDrone());

        existante.setPilote(mission.getPilote());


        return missionRepository.save(existante);

    }

}