package com.example.back_suividrone.controller;


import com.example.back_suividrone.entity.Mission;
import com.example.back_suividrone.entity.TypeMission;
import com.example.back_suividrone.service.MissionService;


import lombok.RequiredArgsConstructor;


import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;



@RestController
@RequestMapping("/api/mission")
@RequiredArgsConstructor
public class MissionController {



    private final MissionService missionService;



    // Voir toutes les missions

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','SUPERVISEUR')")
    public ResponseEntity<List<Mission>> getAllMissions(){

        return ResponseEntity.ok(
                missionService.getAllMissions()
        );

    }




    // Créer mission

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Mission> saveMission(
            @RequestBody Mission mission){


        return ResponseEntity.ok(
                missionService.saveMission(mission)
        );

    }




    // Voir une mission

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','SUPERVISEUR')")
    public ResponseEntity<Mission> getMissionById(
            @PathVariable Long id){


        return missionService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(
                        ResponseEntity.notFound().build()
                );

    }




    // Recherche par type

    @GetMapping("/type/{typeMission}")
    @PreAuthorize("hasAnyRole('ADMIN','SUPERVISEUR')")
    public ResponseEntity<List<Mission>> getMissionByType(
            @PathVariable TypeMission typeMission){


        return ResponseEntity.ok(
                missionService.findByTypeMission(typeMission)
        );

    }





    // Modifier

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Mission> updateMission(
            @PathVariable Long id,
            @RequestBody Mission mission){


        return ResponseEntity.ok(
                missionService.updateMission(id, mission)
        );

    }





    // Supprimer

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteMission(
            @PathVariable Long id){


        missionService.deleteMission(id);


        return ResponseEntity.noContent().build();

    }


}