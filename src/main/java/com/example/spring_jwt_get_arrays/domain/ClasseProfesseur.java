package com.example.spring_jwt_get_arrays.domain;



import javax.persistence.*;

@Entity
public class ClasseProfesseur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String anneeScolaire;
    @ManyToOne
    private Classe classe;
    @ManyToOne
    private Professeur professeur;

    public ClasseProfesseur() {
    }

    public ClasseProfesseur(Long id, String anneeScolaire, Classe classe, Professeur professeur) {
        this.id = id;
        this.anneeScolaire = anneeScolaire;
        this.classe = classe;
        this.professeur = professeur;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnneeScolaire() {
        return anneeScolaire;
    }

    public void setAnneeScolaire(String anneeScolaire) {
        this.anneeScolaire = anneeScolaire;
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
