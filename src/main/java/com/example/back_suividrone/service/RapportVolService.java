package com.example.back_suividrone.service;


import com.example.back_suividrone.dto.RapportVolRequestDTO;
import com.example.back_suividrone.dto.RapportVolResponseDTO;
import com.example.back_suividrone.entity.RapportVol;

import java.util.List;


public interface RapportVolService {


    RapportVol creerRapport(RapportVolRequestDTO dto);


    List<RapportVolResponseDTO> getAllRapports();


    RapportVolResponseDTO getRapportById(Long id);


    RapportVol modifierRapport(Long id, RapportVolRequestDTO dto);


    void supprimerRapport(Long id);

}