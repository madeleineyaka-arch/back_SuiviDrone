package com.example.back_suividrone.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {


    private final JwtAuthFilter jwtAuthFilter;



    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


        http
                .csrf(csrf -> csrf.disable())


                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )


                .authorizeHttpRequests(auth -> auth


                        // Connexion sans token
                        .requestMatchers("/api/auth/**")
                        .permitAll()



                        // Gestion des utilisateurs ADMIN uniquement
                        .requestMatchers("/api/utilisateurs/**")
                        .hasRole("ADMIN")



                        // Gestion des drones
                        // ADMIN + SUPERVISEUR + TECHNICIEN peuvent accéder
                        .requestMatchers("/api/drones/**")
                        .hasAnyRole(
                                "ADMIN",
                                "SUPERVISEUR",
                                "TECHNICIEN"
                        )


                        // Gestion des missions
                        // ADMIN + SUPERVISEUR  peuvent accéder
                        .requestMatchers("/api/mission/**")
                        .hasAnyRole(
                                "ADMIN",
                                "SUPERVISEUR"
                        )


                        // autres API protégées
                        .anyRequest()
                        .authenticated()

                )


                .addFilterBefore(
                        jwtAuthFilter,
                        UsernamePasswordAuthenticationFilter.class
                );


        return http.build();

    }

}