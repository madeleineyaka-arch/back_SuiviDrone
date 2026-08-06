package com.example.back_suividrone.service;


import com.example.back_suividrone.dto.RapportVolRequestDTO;
import com.example.back_suividrone.dto.RapportVolResponseDTO;
import com.example.back_suividrone.entity.Drone;
import com.example.back_suividrone.entity.Mission;
import com.example.back_suividrone.entity.RapportVol;
import com.example.back_suividrone.entity.Utilisateur;
import com.example.back_suividrone.repository.DroneRepository;
import com.example.back_suividrone.repository.MissionRepository;
import com.example.back_suividrone.repository.RapportVolRepository;
import com.example.back_suividrone.repository.UtilisateurRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class RapportVolServiceImpl implements RapportVolService {


    private final RapportVolRepository rapportVolRepository;

    private final DroneRepository droneRepository;

    private final UtilisateurRepository utilisateurRepository;

    private final MissionRepository missionRepository;




    @Override
    public RapportVol creerRapport(RapportVolRequestDTO dto) {


        RapportVol rapportVol = new RapportVol();


        rapportVol.setDateVol(dto.getDateVol());

        rapportVol.setLieuZoneVol(dto.getLieuZoneVol());

        rapportVol.setScenario(dto.getScenario());

        rapportVol.setHeureDecollage(dto.getHeureDecollage());

        rapportVol.setHeureAtterrissage(dto.getHeureAtterrissage());

        rapportVol.setChargeUtile(dto.getChargeUtile());

        rapportVol.setObjectifMission(dto.getObjectifMission());

        rapportVol.setObservations(dto.getObservations());

        rapportVol.setIncidents(dto.getIncidents());

        rapportVol.setVisa(dto.getVisa());



        Drone drone = droneRepository.findById(dto.getDroneId())
                .orElseThrow(() ->
                        new RuntimeException("Drone introuvable")
                );


        Utilisateur pilote = utilisateurRepository.findById(dto.getPiloteId())
                .orElseThrow(() ->
                        new RuntimeException("Pilote introuvable")
                );


        Mission mission = missionRepository.findById(dto.getMissionId())
                .orElseThrow(() ->
                        new RuntimeException("Mission introuvable")
                );



        rapportVol.setDrone(drone);

        rapportVol.setPilote(pilote);

        rapportVol.setMission(mission);



        return rapportVolRepository.save(rapportVol);
    }





    @Override
    public List<RapportVolResponseDTO> getAllRapports() {


        return rapportVolRepository.findAll()
                .stream()
                .map(this::convertirEnDTO)
                .toList();

    }





    @Override
    public RapportVolResponseDTO getRapportById(Long id) {


        RapportVol rapportVol = rapportVolRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Rapport de vol introuvable : " + id
                        )
                );


        return convertirEnDTO(rapportVol);
    }





    @Override
    public RapportVol modifierRapport(Long id, RapportVolRequestDTO dto) {


        RapportVol rapportVol = rapportVolRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Rapport introuvable"
                        )
                );



        rapportVol.setDateVol(dto.getDateVol());

        rapportVol.setLieuZoneVol(dto.getLieuZoneVol());

        rapportVol.setScenario(dto.getScenario());

        rapportVol.setHeureDecollage(dto.getHeureDecollage());

        rapportVol.setHeureAtterrissage(dto.getHeureAtterrissage());

        rapportVol.setChargeUtile(dto.getChargeUtile());

        rapportVol.setObjectifMission(dto.getObjectifMission());

        rapportVol.setObservations(dto.getObservations());

        rapportVol.setIncidents(dto.getIncidents());

        rapportVol.setVisa(dto.getVisa());



        rapportVol.setDrone(
                droneRepository.findById(dto.getDroneId())
                        .orElseThrow(() ->
                                new RuntimeException("Drone introuvable")
                        )
        );


        rapportVol.setPilote(
                utilisateurRepository.findById(dto.getPiloteId())
                        .orElseThrow(() ->
                                new RuntimeException("Pilote introuvable")
                        )
        );


        rapportVol.setMission(
                missionRepository.findById(dto.getMissionId())
                        .orElseThrow(() ->
                                new RuntimeException("Mission introuvable")
                        )
        );



        return rapportVolRepository.save(rapportVol);

    }





    @Override
    public void supprimerRapport(Long id) {


        RapportVol rapportVol = rapportVolRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Rapport introuvable")
                );


        rapportVolRepository.delete(rapportVol);

    }





    private RapportVolResponseDTO convertirEnDTO(RapportVol rapport) {


        return new RapportVolResponseDTO(

                rapport.getId(),

                rapport.getDateVol(),

                rapport.getLieuZoneVol(),

                rapport.getScenario(),

                rapport.getHeureDecollage(),

                rapport.getHeureAtterrissage(),

                rapport.getTempsVol(),

                rapport.getDureeVol(),

                rapport.getChargeUtile(),

                rapport.getObjectifMission(),

                rapport.getObservations(),

                rapport.getIncidents(),

                rapport.getVisa(),


                rapport.getDrone().getId(),

                rapport.getDrone().getNom(),


                rapport.getPilote().getId(),

                rapport.getPilote().getPrenom()
                        + " "
                        + rapport.getPilote().getNom(),


                rapport.getMission().getId(),

                rapport.getMission().getTitre()

        );

    }

}