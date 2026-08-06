package com.example.back_suividrone.service;

import com.example.back_suividrone.entity.Role;
import com.example.back_suividrone.entity.Utilisateur;
import com.example.back_suividrone.repository.UtilisateurRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UtilisateurServiceImpl implements UtilisateurService {


    private final UtilisateurRepository utilisateurRepository;

    private final PasswordEncoder passwordEncoder;



    @Override
    public Utilisateur saveUtilisateur(Utilisateur utilisateur) {


        log.info("Création utilisateur");


        // Vérification du mot de passe
        if(utilisateur.getPassword() == null || utilisateur.getPassword().isBlank()) {

            throw new RuntimeException("Le mot de passe est obligatoire");

        }



        // Vérification email
        if(utilisateurRepository.existsByEmail(utilisateur.getEmail())){

            throw new RuntimeException("Email déjà utilisé");

        }



        // Vérification matricule
        if(utilisateurRepository.existsByMatricule(utilisateur.getMatricule())){

            throw new RuntimeException("Matricule déjà utilisé");

        }



        // Cryptage du mot de passe
        utilisateur.setPassword(
                passwordEncoder.encode(utilisateur.getPassword())
        );



        return utilisateurRepository.save(utilisateur);
    }





    @Override
    public Optional<Utilisateur> findByEmail(String email){

        return utilisateurRepository.findByEmail(email);

    }





    @Override
    public List<Utilisateur> getAllUtilisateurs(){

        return utilisateurRepository.findAll();

    }





    @Override
    public Optional<Utilisateur> getUtilisateurById(Long id){

        return utilisateurRepository.findById(id);

    }





    @Override
    public List<Utilisateur> getUtilisateurByRole(Role role){

        return utilisateurRepository.findByRole(role);

    }





    @Override
    public void deleteUtilisateur(Long id){


        if(!utilisateurRepository.existsById(id)){

            throw new RuntimeException("Utilisateur introuvable");

        }


        utilisateurRepository.deleteById(id);

    }





    @Override
    public Utilisateur updateUtilisateur(
            Long id,
            Utilisateur utilisateur
    ){


        Utilisateur existant =
                utilisateurRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Utilisateur introuvable"
                                )
                        );



        // Vérification email
        if(!existant.getEmail().equals(utilisateur.getEmail())
                &&
                utilisateurRepository.existsByEmail(utilisateur.getEmail())){

            throw new RuntimeException("Email déjà utilisé");

        }




        // Vérification matricule
        if(!existant.getMatricule().equals(utilisateur.getMatricule())
                &&
                utilisateurRepository.existsByMatricule(utilisateur.getMatricule())){

            throw new RuntimeException("Matricule déjà utilisé");

        }





        existant.setNom(utilisateur.getNom());

        existant.setPrenom(utilisateur.getPrenom());

        existant.setEmail(utilisateur.getEmail());

        existant.setMatricule(utilisateur.getMatricule());

        existant.setGrade(utilisateur.getGrade());

        existant.setRole(utilisateur.getRole());

        existant.setActif(utilisateur.getActif());




        // Modification du mot de passe uniquement s'il est fourni
        if(utilisateur.getPassword() != null
                && !utilisateur.getPassword().isBlank()){


            existant.setPassword(
                    passwordEncoder.encode(
                            utilisateur.getPassword()
                    )
            );

        }




        return utilisateurRepository.save(existant);

    }

}