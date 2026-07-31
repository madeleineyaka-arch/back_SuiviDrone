package com.example.back_suividrone.controller;


import com.example.back_suividrone.dto.AuthRequest;
import com.example.back_suividrone.dto.AuthResponse;
import com.example.back_suividrone.entity.Utilisateur;
import com.example.back_suividrone.repository.UtilisateurRepository;
import com.example.back_suividrone.security.JwtService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {



    private final UtilisateurRepository utilisateurRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;




    // LOGIN
    // POST http://localhost:8080/auth/login

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody AuthRequest request
    ) {


        Utilisateur utilisateur =
                utilisateurRepository.findByEmail(request.getEmail())
                        .orElse(null);



        if(utilisateur == null){

            return ResponseEntity
                    .badRequest()
                    .body("Email incorrect");

        }



        if(!passwordEncoder.matches(
                request.getPassword(),
                utilisateur.getPassword()
        )){


            return ResponseEntity
                    .badRequest()
                    .body("Mot de passe incorrect");

        }




        String token = jwtService.generateToken(
                utilisateur.getEmail(),
                utilisateur.getRole().name()
        );



        return ResponseEntity.ok(
                new AuthResponse(
                        token
                )
        );


    }

}