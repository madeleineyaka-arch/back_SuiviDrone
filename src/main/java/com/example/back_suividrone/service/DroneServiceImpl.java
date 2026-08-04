package com.example.back_suividrone.service;


import com.example.back_suividrone.entity.Drone;
import com.example.back_suividrone.repository.DroneRepository;

import jakarta.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;



@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class DroneServiceImpl implements DroneService {



    private final DroneRepository droneRepository;





    // AJOUTER DRONE

    @Override
    public Drone saveDrone(Drone drone) {


        log.info("Ajout d'un nouveau drone");


        if(droneRepository.existsByNumeroSerie(
                drone.getNumeroSerie())){


            throw new RuntimeException(
                    "Ce numéro de série existe déjà"
            );

        }



        return droneRepository.save(drone);

    }







    // RECHERCHE PAR NUMERO SERIE

    @Override
    public Optional<Drone> findByNumeroSerie(
            String numeroSerie) {


        return droneRepository
                .findByNumeroSerie(numeroSerie);

    }








    // RECHERCHE PAR ID

    @Override
    public Optional<Drone> findById(Long id) {


        return droneRepository.findById(id);

    }








    // LISTE DES DRONES

    @Override
    public List<Drone> getAllDrones() {


        return droneRepository.findAll();

    }









    // SUPPRESSION

    @Override
    public void deleteDrone(Long id) {


        if(!droneRepository.existsById(id)){


            throw new RuntimeException(
                    "Drone introuvable"
            );

        }


        droneRepository.deleteById(id);

    }








    // MODIFICATION

    @Override
    public Drone updateDrone(
            Long id,
            Drone drone) {



        Drone existant =
                droneRepository.findById(id)

                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Drone introuvable"
                                )
                        );





        // Vérification numéro série

        if(!existant.getNumeroSerie()
                .equals(drone.getNumeroSerie())

                &&

                droneRepository.existsByNumeroSerie(
                        drone.getNumeroSerie())){


            throw new RuntimeException(
                    "Ce numéro de série existe déjà"
            );

        }






        existant.setNom(
                drone.getNom()
        );


        existant.setNumeroSerie(
                drone.getNumeroSerie()
        );


        existant.setType(
                drone.getType()
        );


        existant.setEtat(
                drone.getEtat()
        );


        existant.setDateMiseService(
                drone.getDateMiseService()
        );


        existant.setNombreMission(
                drone.getNombreMission()
        );



        return droneRepository.save(existant);

    }


}