package com.example.back_suividrone.repository;

import com.example.back_suividrone.entity.Role;
import com.example.back_suividrone.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UtilisateurRepository
        extends JpaRepository<Utilisateur, Long> {


    // Pour la connexion JWT
    Optional<Utilisateur> findByEmail(String email);


    // Recherche militaire par matricule
    Optional<Utilisateur> findByMatricule(String matricule);


    // Afficher les utilisateurs par rôle
    List<Utilisateur> findByRole(Role role);


    // Vérification avant création
    boolean existsByEmail(String email);


    boolean existsByMatricule(String matricule);

}