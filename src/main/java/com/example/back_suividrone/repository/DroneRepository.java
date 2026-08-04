package com.example.back_suividrone.repository;


import com.example.back_suividrone.entity.Drone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface DroneRepository extends JpaRepository<Drone, Long> {


    // Recherche par numéro de série
    Optional<Drone> findByNumeroSerie(String numeroSerie);



    // Recherche par nom
    List<Drone> findByNom(String nom);



    // Vérification avant création
    boolean existsByNumeroSerie(String numeroSerie);

}