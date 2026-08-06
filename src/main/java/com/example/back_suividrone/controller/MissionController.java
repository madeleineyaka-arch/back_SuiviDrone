package com.example.back_suividrone.controller;


import com.example.back_suividrone.dto.MissionResponseDTO;
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





    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','SUPERVISEUR')")
    public ResponseEntity<List<MissionResponseDTO>> getAll(){


        return ResponseEntity.ok(
                missionService.getAllMissions()
        );

    }






    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MissionResponseDTO> save(
            @RequestBody Mission mission){


        return ResponseEntity.ok(
                missionService.saveMission(mission)
        );

    }







    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','SUPERVISEUR')")
    public ResponseEntity<MissionResponseDTO> findById(
            @PathVariable Long id){


        return ResponseEntity.ok(
                missionService.findById(id)
        );

    }







    @GetMapping("/type/{typeMission}")
    @PreAuthorize("hasAnyRole('ADMIN','SUPERVISEUR')")
    public ResponseEntity<List<MissionResponseDTO>> findByType(
            @PathVariable TypeMission typeMission){


        return ResponseEntity.ok(
                missionService.findByTypeMission(typeMission)
        );

    }







    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MissionResponseDTO> update(
            @PathVariable Long id,
            @RequestBody Mission mission){


        return ResponseEntity.ok(
                missionService.updateMission(id,mission)
        );

    }







    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(
            @PathVariable Long id){


        missionService.deleteMission(id);


        return ResponseEntity.noContent().build();

    }


}