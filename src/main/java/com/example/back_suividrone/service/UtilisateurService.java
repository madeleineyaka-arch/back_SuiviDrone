package com.example.back_suividrone.service;

import com.example.back_suividrone.entity.Utilisateur;

import java.util.List;
import java.util.Optional;

public interface UtilisateurService {


    Utilisateur saveUtilisateur(Utilisateur utilisateur);

    Optional<Utilisateur> findByEmail(String email);

    List<Utilisateur> getAllUtilisateurs();

    Optional<Utilisateur> getUtilisateurById(Long id);

    void deleteUtilisateur(Long id);

    Utilisateur updateUtilisateur(Long id, Utilisateur utilisateur);
}