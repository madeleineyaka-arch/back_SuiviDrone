package com.example.back_suividrone.service;

import com.example.back_suividrone.entity.Utilisateur;
import com.example.back_suividrone.repository.UtilisateurRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UtilisateurServiceImpl implements UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;


    @Override
    public Utilisateur saveUtilisateur(Utilisateur utilisateur) {
        log.info("Enregistrer un nouvel utilisateur dans la base de données");
        return utilisateurRepository.save(utilisateur);
    }


    @Override
    public Optional<Utilisateur> findByEmail(String email) {
        return utilisateurRepository.findByEmail(email);
    }


    @Override
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }


    @Override
    public Optional<Utilisateur> getUtilisateurById(Long id) {
        return utilisateurRepository.findById(id);
    }


    @Override
    public void deleteUtilisateur(Long id) {
        utilisateurRepository.deleteById(id);
    }


    @Override
    public Utilisateur updateUtilisateur(Long id, Utilisateur utilisateur) {

        Utilisateur utilisateurExistant =
                utilisateurRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Utilisateur introuvable"));


        utilisateurExistant.setName(utilisateur.getName());
        utilisateurExistant.setUsername(utilisateur.getUsername());
        utilisateurExistant.setEmail(utilisateur.getEmail());
        utilisateurExistant.setMatricule(utilisateur.getMatricule());
        utilisateurExistant.setGrade(utilisateur.getGrade());
        utilisateurExistant.setRole(utilisateur.getRole());
        utilisateurExistant.setActif(utilisateur.getActif());


        return utilisateurRepository.save(utilisateurExistant);
    }
}