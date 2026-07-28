package com.example.back_suividrone.controller;

import com.example.back_suividrone.entity.Utilisateur;
import com.example.back_suividrone.service.UtilisateurService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utilisateurs")
@RequiredArgsConstructor
public class UtilisateurController {

    private final UtilisateurService utilisateurService;



    // AFFICHER TOUS LES UTILISATEURS
    // GET http://localhost:8080/api/utilisateurs

    @GetMapping
    public ResponseEntity<List<Utilisateur>> getUtilisateurs() {

        return ResponseEntity.ok(
                utilisateurService.getAllUtilisateurs()
        );
    }




    // CREER UN UTILISATEUR
    // L'ADMIN CREE LE COMPTE
    // POST http://localhost:8080/api/utilisateurs

    @PostMapping
    public ResponseEntity<Utilisateur> saveUtilisateur(
            @RequestBody Utilisateur utilisateur) {


        Utilisateur nouvelUtilisateur =
                utilisateurService.saveUtilisateur(utilisateur);


        return ResponseEntity.ok(nouvelUtilisateur);
    }




    // CHERCHER UN UTILISATEUR PAR ID
    // GET /api/utilisateurs/1

    @GetMapping("/{id}")
    public ResponseEntity<Utilisateur> getUtilisateurById(
            @PathVariable Long id) {


        return utilisateurService.getUtilisateurById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }



    // CHERCHER PAR EMAIL
    // UTILISE POUR LOGIN PLUS TARD
    // GET /api/utilisateurs/email/test@gmail.com

    @GetMapping("/email/{email}")
    public ResponseEntity<Utilisateur> getUtilisateurByEmail(
            @PathVariable String email) {


        return utilisateurService.findByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }




    // MODIFIER UN UTILISATEUR
    // PUT /api/utilisateurs/1

    @PutMapping("/{id}")
    public ResponseEntity<Utilisateur> updateUtilisateur(
            @PathVariable Long id,
            @RequestBody Utilisateur utilisateur) {


        try {

            Utilisateur utilisateurModifie =
                    utilisateurService.updateUtilisateur(id, utilisateur);


            return ResponseEntity.ok(utilisateurModifie);


        } catch (RuntimeException e) {

            return ResponseEntity.notFound().build();

        }
    }




    // SUPPRIMER UN UTILISATEUR
    // DELETE /api/utilisateurs/1

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUtilisateur(
            @PathVariable Long id) {


        utilisateurService.deleteUtilisateur(id);


        return ResponseEntity.noContent().build();

    }

}