package com.example.spring_jwt_get_arrays.domain;



import javax.persistence.*;

@Entity
public class ClasseProfesseur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String annee_scolaire;
    @ManyToOne
    private Classe classe;
    @ManyToOne
    private Professeur professeur;

    public ClasseProfesseur() {
    }

    public ClasseProfesseur(Long id, String annee_scolaire, Classe classe, Professeur professeur) {
        this.id = id;
        this.annee_scolaire = annee_scolaire;
        this.classe = classe;
        this.professeur = professeur;
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

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public Professeur getProfesseur() {
        return professeur;
    }

    public void setProfesseur(Professeur professeur) {
        this.professeur = professeur;
    }
}
