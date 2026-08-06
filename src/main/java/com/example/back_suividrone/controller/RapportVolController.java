package com.example.back_suividrone.controller;


import com.example.back_suividrone.dto.RapportVolRequestDTO;
import com.example.back_suividrone.dto.RapportVolResponseDTO;
import com.example.back_suividrone.entity.RapportVol;
import com.example.back_suividrone.service.RapportVolService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api/rapports-vol")
@RequiredArgsConstructor
@CrossOrigin("*")
public class RapportVolController {



    private final RapportVolService rapportVolService;





    @PostMapping
    public ResponseEntity<RapportVol> creerRapport(
            @RequestBody RapportVolRequestDTO dto
    ){

        return ResponseEntity.ok(
                rapportVolService.creerRapport(dto)
        );
    }





    @GetMapping
    public ResponseEntity<List<RapportVolResponseDTO>> getAllRapports(){

        return ResponseEntity.ok(
                rapportVolService.getAllRapports()
        );
    }





    @GetMapping("/{id}")
    public ResponseEntity<RapportVolResponseDTO> getRapportById(
            @PathVariable Long id
    ){

        return ResponseEntity.ok(
                rapportVolService.getRapportById(id)
        );
    }





    @PutMapping("/{id}")
    public ResponseEntity<RapportVol> modifierRapport(
            @PathVariable Long id,
            @RequestBody RapportVolRequestDTO dto
    ){

        return ResponseEntity.ok(
                rapportVolService.modifierRapport(id, dto)
        );
    }





    @DeleteMapping("/{id}")
    public ResponseEntity<String> supprimerRapport(
            @PathVariable Long id
    ){

        rapportVolService.supprimerRapport(id);


        return ResponseEntity.ok(
                "Rapport de vol supprimé avec succès"
        );
    }

}