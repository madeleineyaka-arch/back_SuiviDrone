package com.example.back_suividrone.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Collection;
import java.util.Collections;



@Entity
@Table(name = "utilisateurs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Utilisateur implements UserDetails {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;




    @Column(nullable = false, length = 100)
    private String nom;




    @Column(nullable = false, length = 100)
    private String prenom;




    // Utilisé pour la connexion
    @Column(nullable = false, unique = true, length = 150)
    private String email;




    // Mot de passe crypté avec BCrypt
    @Column(nullable = false, length = 255)
    private String password;




    // Matricule militaire ou identifiant utilisateur
    @Column(nullable = false, unique = true, length = 100)
    private String matricule;




    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Grade grade;




    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;




    // Compte actif ou désactivé par ADMIN
    @Column(nullable = false)
    private Boolean actif = Boolean.TRUE;





    /*
     * Gestion des permissions Spring Security
     * Exemple :
     * ADMIN devient ROLE_ADMIN
     * PILOTE devient ROLE_PILOTE
     */

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {


        return Collections.singletonList(

                new SimpleGrantedAuthority(
                        "ROLE_" + role.name()
                )

        );

    }





    // Email utilisé comme username pour Spring Security

    @Override
    public String getUsername() {

        return email;

    }





    @Override
    public String getPassword() {

        return password;

    }





    @Override
    public boolean isAccountNonExpired() {

        return actif;

    }





    @Override
    public boolean isAccountNonLocked() {

        return actif;

    }





    @Override
    public boolean isCredentialsNonExpired() {

        return actif;

    }





    @Override
    public boolean isEnabled() {

        return actif;

    }


}