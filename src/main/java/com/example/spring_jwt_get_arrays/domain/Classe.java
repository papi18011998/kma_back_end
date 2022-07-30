package com.example.spring_jwt_get_arrays.domain;


import javax.persistence.*;
import java.util.Collection;

@Entity
public class Classe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String libelle;
    @OneToMany(mappedBy = "classe")
    private Collection<EleveClasse> annees;
    @OneToMany(mappedBy = "classe")
    private Collection<ClasseProfesseur> professeurs_classes;
    public Classe(Long id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    public Classe() {

    }

    public Classe(Long id, String libelle, Collection<EleveClasse> annees, Collection<ClasseProfesseur> professeurs_classes) {
        this.id = id;
        this.libelle = libelle;
        this.annees = annees;
        this.professeurs_classes = professeurs_classes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Collection<EleveClasse> getAnnees() {
        return annees;
    }

    public void setAnnees(Collection<EleveClasse> annees) {
        this.annees = annees;
    }

    public Collection<ClasseProfesseur> getProfesseurs_classes() {
        return professeurs_classes;
    }

    public void setProfesseurs_classes(Collection<ClasseProfesseur> professeurs_classes) {
        this.professeurs_classes = professeurs_classes;
    }
}
