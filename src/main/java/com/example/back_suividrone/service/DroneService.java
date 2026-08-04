package com.example.back_suividrone.service;


import com.example.back_suividrone.entity.Drone;

import java.util.List;
import java.util.Optional;


public interface DroneService {


    // Ajouter drone
    Drone saveDrone(Drone drone);



    // Recherche numéro série
    Optional<Drone> findByNumeroSerie(String numeroSerie);



    // Recherche par ID
    Optional<Drone> findById(Long id);



    // Liste drones
    List<Drone> getAllDrones();



    // Supprimer
    void deleteDrone(Long id);



    // Modifier
    Drone updateDrone(Long id, Drone drone);

}