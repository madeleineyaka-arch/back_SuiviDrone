package com.example.back_suividrone.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
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


    @Column(nullable = false, unique = true, length = 150)
    private String email;


    @Column(nullable = false, length = 255)
    @JsonIgnore
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
     * Gestion des rôles Spring Security
     */
    @Override
    @JsonIgnore
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
     * Email utilisé comme username
     */
    @Override
    public String getUsername() {

        return email;

    }



    @Override
    @JsonIgnore
    public String getPassword() {

        return password;

    }



    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {

        return Boolean.TRUE.equals(actif);

    }



    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {

        return Boolean.TRUE.equals(actif);

    }



    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {

        return Boolean.TRUE.equals(actif);

    }



    @Override
    @JsonIgnore
    public boolean isEnabled() {

        return Boolean.TRUE.equals(actif);

    }


}