package com.example.back_suividrone.controller;


import com.example.back_suividrone.entity.Drone;
import com.example.back_suividrone.service.DroneService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api/drones")
@RequiredArgsConstructor
public class DroneController {



    private final DroneService droneService;




    // ===============================
    // CONSULTER TOUS LES DRONES
    // ADMIN + SUPERVISEUR + TECHNICIEN
    // ===============================

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','SUPERVISEUR','TECHNICIEN')")
    public ResponseEntity<List<Drone>> getAllDrones(){


        return ResponseEntity.ok(
                droneService.getAllDrones()
        );

    }






    // ===============================
    // AJOUTER UN DRONE
    // ADMIN UNIQUEMENT
    // ===============================

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Drone> saveDrone(
            @RequestBody Drone drone){


        return ResponseEntity.ok(
                droneService.saveDrone(drone)
        );

    }







    // ===============================
    // CONSULTER UN DRONE PAR ID
    // ADMIN + SUPERVISEUR + TECHNICIEN
    // ===============================

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','SUPERVISEUR','TECHNICIEN')")
    public ResponseEntity<Drone> getDroneById(
            @PathVariable Long id){



        return droneService.findById(id)

                .map(ResponseEntity::ok)

                .orElse(
                        ResponseEntity.notFound().build()
                );

    }







    // ===============================
    // RECHERCHER PAR NUMERO DE SERIE
    // ADMIN + SUPERVISEUR + TECHNICIEN
    // ===============================

    @GetMapping("/numero/{numeroSerie}")
    @PreAuthorize("hasAnyRole('ADMIN','SUPERVISEUR','TECHNICIEN')")
    public ResponseEntity<Drone> getDroneByNumeroSerie(
            @PathVariable String numeroSerie){



        return droneService.findByNumeroSerie(numeroSerie)

                .map(ResponseEntity::ok)

                .orElse(
                        ResponseEntity.notFound().build()
                );

    }








    // ===============================
    // MODIFIER UN DRONE
    // ADMIN UNIQUEMENT
    // ===============================

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Drone> updateDrone(
            @PathVariable Long id,
            @RequestBody Drone drone){


        try {

            return ResponseEntity.ok(
                    droneService.updateDrone(id, drone)
            );


        } catch(RuntimeException e){

            return ResponseEntity.notFound().build();

        }

    }







    // ===============================
    // SUPPRIMER UN DRONE
    // ADMIN UNIQUEMENT
    // ===============================

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteDrone(
            @PathVariable Long id){



        droneService.deleteDrone(id);


        return ResponseEntity.noContent().build();

    }



}