package com.example.back_suividrone.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Collection;
import java.util.Collections;


@Entity
@Table(name = "utilisateurs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Utilisateur implements UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @Column(nullable = false, length = 100)
    private String nom;



    @Column(nullable = false, length = 100)
    private String prenom;



    @Column(nullable = false, unique = true, length = 150)
    private String email;



    /*
     * Le mot de passe peut être reçu en entrée JSON
     * mais ne sera jamais renvoyé dans les réponses API
     */
    @Column(nullable = false, length = 255)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;



    @Column(nullable = false, unique = true, length = 100)
    private String matricule;



    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Grade grade;



    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;



    @Column(nullable = false)
    private Boolean actif = true;



    /*
     * Gestion des autorisations Spring Security
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {


        if(role == null){

            return Collections.emptyList();

        }


        return Collections.singletonList(
                new SimpleGrantedAuthority(
                        "ROLE_" + role.name()
                )
        );

    }



    /*
     * L'email sert d'identifiant de connexion
     */
    @Override
    public String getUsername() {

        return email;

    }



    /*
     * Obligatoire pour UserDetails
     * Ne pas mettre @JsonIgnore ici
     */
    @Override
    public String getPassword() {

        return password;

    }



    @Override
    public boolean isAccountNonExpired() {

        return Boolean.TRUE.equals(actif);

    }



    @Override
    public boolean isAccountNonLocked() {

        return Boolean.TRUE.equals(actif);

    }



    @Override
    public boolean isCredentialsNonExpired() {

        return Boolean.TRUE.equals(actif);

    }



    @Override
    public boolean isEnabled() {

        return Boolean.TRUE.equals(actif);

    }

}