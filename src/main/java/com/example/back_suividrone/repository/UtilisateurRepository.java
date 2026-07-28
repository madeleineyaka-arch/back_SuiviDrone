package com.example.back_suividrone.repository;

import com.example.back_suividrone.entity.Role;
import com.example.back_suividrone.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
Optional<Utilisateur> findByEmail(String email);
List<Utilisateur> findByRole(Role role);
}
