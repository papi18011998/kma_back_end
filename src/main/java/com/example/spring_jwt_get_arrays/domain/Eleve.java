package com.example.spring_jwt_get_arrays.domain;



import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@DiscriminatorValue("eleve")
public class Eleve extends User{
    private String matricule;
    @Temporal(TemporalType.DATE)
    private Date date_naissance;
    @ManyToOne
    private  Parent parent;

    @OneToMany(mappedBy = "eleve")
    private List<EleveClasse> annees;

    @OneToMany(mappedBy = "eleve")
    private Collection<Evaluation> evaluations;

    public Eleve() {
        super();
    }

    public Eleve(Long id, String prenom, String nom, String userName, String password, String adresse, Date joinDate, String role, String[] authorities, boolean isActive, boolean isNotLocked, String telephone, Genre genre, String matricule, Date date_naissance, Parent parent, List<EleveClasse> annees, Collection<Evaluation> evaluations) {
        super(id, prenom, nom, userName, password, adresse, joinDate, role, authorities, isActive, isNotLocked, telephone, genre);
        this.matricule = matricule;
        this.date_naissance = date_naissance;
        this.parent = parent;
        this.annees = annees;
        this.evaluations = evaluations;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    public List<EleveClasse> getAnnees() {
        return annees;
    }

    public void setAnnees(List<EleveClasse> annees) {
        this.annees = annees;
    }

    public Collection<Evaluation> getEvaluations() {
        return evaluations;
    }

    public void setEvaluations(Collection<Evaluation> evaluations) {
        this.evaluations = evaluations;
    }
}
