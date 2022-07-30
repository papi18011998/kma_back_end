package com.example.spring_jwt_get_arrays.domain;


import javax.persistence.*;

@Entity
public class EleveClasse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    private String annee_scolaire;
    @ManyToOne
    private Eleve eleve;
    @ManyToOne
    private Classe classe;

    public EleveClasse() {
    }

    public EleveClasse(Long id, String annee_scolaire, Eleve eleve, Classe classe) {
        this.id = id;
        this.annee_scolaire = annee_scolaire;
        this.eleve = eleve;
        this.classe = classe;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnnee_scolaire() {
        return annee_scolaire;
    }

    public void setAnnee_scolaire(String annee_scolaire) {
        this.annee_scolaire = annee_scolaire;
    }

    public Eleve getEleve() {
        return eleve;
    }

    public void setEleve(Eleve eleve) {
        this.eleve = eleve;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }
}
