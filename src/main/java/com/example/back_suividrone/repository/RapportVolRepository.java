package com.example.back_suividrone.repository;

import com.example.back_suividrone.entity.RapportVol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RapportVolRepository extends JpaRepository<RapportVol, Long> {

}