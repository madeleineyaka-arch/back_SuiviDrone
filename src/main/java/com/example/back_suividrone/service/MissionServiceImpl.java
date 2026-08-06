package com.example.back_suividrone.service;


import com.example.back_suividrone.dto.MissionResponseDTO;
import com.example.back_suividrone.entity.Drone;
import com.example.back_suividrone.entity.Mission;
import com.example.back_suividrone.entity.TypeMission;
import com.example.back_suividrone.entity.Utilisateur;
import com.example.back_suividrone.repository.DroneRepository;
import com.example.back_suividrone.repository.MissionRepository;
import com.example.back_suividrone.repository.UtilisateurRepository;


import jakarta.transaction.Transactional;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;


import java.util.List;



@Service
@RequiredArgsConstructor
@Transactional
public class MissionServiceImpl implements MissionService {


    private final MissionRepository missionRepository;

    private final MissionMapper missionMapper;

    private final DroneRepository droneRepository;

    private final UtilisateurRepository utilisateurRepository;



    @Override
    public MissionResponseDTO saveMission(Mission mission){


        // Récupérer le vrai drone depuis la base
        if(mission.getDrone() != null){

            Drone drone = droneRepository.findById(
                            mission.getDrone().getId()
                    )
                    .orElseThrow(
                            () -> new RuntimeException(
                                    "Drone introuvable"
                            )
                    );

            mission.setDrone(drone);
        }



        // Récupérer le vrai pilote depuis la base
        if(mission.getPilote() != null){

            Utilisateur pilote = utilisateurRepository.findById(
                            mission.getPilote().getId()
                    )
                    .orElseThrow(
                            () -> new RuntimeException(
                                    "Pilote introuvable"
                            )
                    );

            mission.setPilote(pilote);
        }



        Mission saved = missionRepository.save(mission);


        return missionMapper.toDTO(saved);

    }





    @Override
    public List<MissionResponseDTO> getAllMissions(){


        return missionRepository.findAll()
                .stream()
                .map(missionMapper::toDTO)
                .toList();

    }





    @Override
    public MissionResponseDTO findById(Long id){


        Mission mission =
                missionRepository.findById(id)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Mission introuvable"
                                )
                        );


        return missionMapper.toDTO(mission);

    }





    @Override
    public List<MissionResponseDTO> findByTypeMission(
            TypeMission typeMission){


        return missionRepository
                .findByTypeMission(typeMission)
                .stream()
                .map(missionMapper::toDTO)
                .toList();

    }





    @Override
    public MissionResponseDTO updateMission(
            Long id,
            Mission mission){


        Mission existante =
                missionRepository.findById(id)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Mission introuvable"
                                )
                        );



        existante.setTitre(
                mission.getTitre()
        );


        existante.setObjectif(
                mission.getObjectif()
        );


        existante.setTypeMission(
                mission.getTypeMission()
        );


        existante.setDateMission(
                mission.getDateMission()
        );


        existante.setHeureDebut(
                mission.getHeureDebut()
        );


        existante.setHeureFin(
                mission.getHeureFin()
        );


        existante.setLieu(
                mission.getLieu()
        );


        existante.setStatut(
                mission.getStatut()
        );



        // Mise à jour du drone
        if(mission.getDrone() != null){

            Drone drone = droneRepository.findById(
                            mission.getDrone().getId()
                    )
                    .orElseThrow(
                            () -> new RuntimeException(
                                    "Drone introuvable"
                            )
                    );

            existante.setDrone(drone);
        }



        // Mise à jour du pilote
        if(mission.getPilote() != null){

            Utilisateur pilote = utilisateurRepository.findById(
                            mission.getPilote().getId()
                    )
                    .orElseThrow(
                            () -> new RuntimeException(
                                    "Pilote introuvable"
                            )
                    );

            existante.setPilote(pilote);
        }



        Mission updated =
                missionRepository.save(existante);



        return missionMapper.toDTO(updated);

    }





    @Override
    public void deleteMission(Long id){


        if(!missionRepository.existsById(id)){

            throw new RuntimeException(
                    "Mission introuvable"
            );

        }


        missionRepository.deleteById(id);

    }

}