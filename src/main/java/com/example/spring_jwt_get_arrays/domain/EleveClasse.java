package com.example.spring_jwt_get_arrays.domain;


import javax.persistence.*;

@Entity
public class EleveClasse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    private String anneeScolaire;
    @ManyToOne
    private Eleve eleve;
    @ManyToOne
    private Classe classe;

    public EleveClasse() {
    }

    public EleveClasse(Long id, String anneeScolaire, Eleve eleve, Classe classe) {
        this.id = id;
        this.anneeScolaire = anneeScolaire;
        this.eleve = eleve;
        this.classe = classe;
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
