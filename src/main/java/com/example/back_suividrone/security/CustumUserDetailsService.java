package com.example.back_suividrone.security;


import com.example.back_suividrone.entity.Utilisateur;
import com.example.back_suividrone.repository.UtilisateurRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class CustumUserDetailsService implements UserDetailsService {



    private final UtilisateurRepository utilisateurRepository;




    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {



        Utilisateur utilisateur =

                utilisateurRepository.findByEmail(email)

                        .orElseThrow(() ->

                                new UsernameNotFoundException(
                                        "Utilisateur non trouvé avec email : "
                                                + email
                                )

                        );



        return utilisateur;

    }

}