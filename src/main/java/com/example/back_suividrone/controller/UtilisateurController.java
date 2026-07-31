package com.example.back_suividrone.controller;

import com.example.back_suividrone.entity.Role;
import com.example.back_suividrone.entity.Utilisateur;
import com.example.back_suividrone.service.UtilisateurService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/utilisateurs")
@RequiredArgsConstructor
public class UtilisateurController {


    private final UtilisateurService utilisateurService;



    // AFFICHER TOUS LES UTILISATEURS
    // ADMIN uniquement

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Utilisateur>> getUtilisateurs() {


        return ResponseEntity.ok(
                utilisateurService.getAllUtilisateurs()
        );
    }




    // CREER UN UTILISATEUR
    // SEUL ADMIN PEUT CREER UN COMPTE

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Utilisateur> saveUtilisateur(
            @RequestBody Utilisateur utilisateur) {


        return ResponseEntity.ok(
                utilisateurService.saveUtilisateur(utilisateur)
        );

    }




    // CHERCHER UN UTILISATEUR PAR ID

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Utilisateur> getUtilisateurById(
            @PathVariable Long id) {


        return utilisateurService.getUtilisateurById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }





    // CHERCHER PAR EMAIL

    @GetMapping("/email/{email}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Utilisateur> getUtilisateurByEmail(
            @PathVariable String email) {


        return utilisateurService.findByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }





    // AFFICHER LES UTILISATEURS PAR ROLE
    // Exemple :
    // GET /api/utilisateurs/role/PILOTE

    @GetMapping("/role/{role}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Utilisateur>> getUtilisateurByRole(
            @PathVariable Role role) {


        return ResponseEntity.ok(
                utilisateurService.getUtilisateurByRole(role)
        );

    }





    // MODIFIER UN UTILISATEUR

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Utilisateur> updateUtilisateur(
            @PathVariable Long id,
            @RequestBody Utilisateur utilisateur) {


        try {

            return ResponseEntity.ok(
                    utilisateurService.updateUtilisateur(id, utilisateur)
            );


        } catch(RuntimeException e) {

            return ResponseEntity.notFound().build();

        }

    }





    // SUPPRIMER UN UTILISATEUR

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteUtilisateur(
            @PathVariable Long id) {


        utilisateurService.deleteUtilisateur(id);


        return ResponseEntity.noContent().build();

    }

}