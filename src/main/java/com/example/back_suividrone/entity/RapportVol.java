package com.example.back_suividrone.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "rapports_vol")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RapportVol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private LocalDate dateVol;


    private String lieuZoneVol;


    private String scenario;


    private LocalTime heureDecollage;


    private LocalTime heureAtterrissage;


    // Temps de vol enregistré en minutes dans la base
    private Integer tempsVol;


    private String chargeUtile;


    @Column(length = 500)
    private String objectifMission;


    @Column(length = 500)
    private String observations;


    @Column(length = 500)
    private String incidents;


    private String visa;



    // Drone utilisé
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "drone_id", nullable = false)
    private Drone drone;



    // Pilote ayant effectué le vol
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pilote_id", nullable = false)
    private Utilisateur pilote;



    // Une mission possède un seul rapport de vol
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id", unique = true)
    private Mission mission;




    // Calcul automatique du temps de vol en minutes
    @PrePersist
    @PreUpdate
    public void calculerTempsVol() {

        if (heureDecollage != null && heureAtterrissage != null) {


            int debut = heureDecollage.getHour() * 60
                    + heureDecollage.getMinute();


            int fin = heureAtterrissage.getHour() * 60
                    + heureAtterrissage.getMinute();


            this.tempsVol = fin - debut;
        }
    }




    // Durée lisible pour l'interface utilisateur
    // Ce champ n'est pas enregistré dans MySQL
    @Transient
    public String getDureeVol() {


        if (tempsVol == null) {

            return "0h 0min 0s";
        }


        int heures = tempsVol / 60;


        int minutes = tempsVol % 60;


        int secondes = 0;


        return heures + "h "
                + minutes + "min "
                + secondes + "s";
    }

}