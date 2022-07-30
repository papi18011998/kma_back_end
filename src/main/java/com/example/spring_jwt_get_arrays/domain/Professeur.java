package com.example.spring_jwt_get_arrays.domain;



import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@DiscriminatorValue("professeur")
public class Professeur extends  User{
    @Temporal(TemporalType.DATE)
    private Date date_prise_fonction;
    @ManyToOne
    private Matiere matiere;

    @OneToMany(mappedBy = "professeur")
    private List<ClasseProfesseur> annees;

    public Professeur() {
        super();
    }

    public Professeur(Long id, String prenom, String nom, String userName, String password, String adresse, Date joinDate, String role, String[] authorities, boolean isActive, boolean isNotLocked, String telephone, Genre genre, Date date_prise_fonction, Matiere matiere, List<ClasseProfesseur> annees) {
        super(id, prenom, nom, userName, password, adresse, joinDate, role, authorities, isActive, isNotLocked, telephone, genre);
        this.date_prise_fonction = date_prise_fonction;
        this.matiere = matiere;
        this.annees = annees;
    }

    public Date getDate_prise_fonction() {
        return date_prise_fonction;
    }

    public void setDate_prise_fonction(Date date_prise_fonction) {
        this.date_prise_fonction = date_prise_fonction;
    }

    public Matiere getMatiere() {
        return matiere;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }

    public List<ClasseProfesseur> getAnnees() {
        return annees;
    }

    public void setAnnees(List<ClasseProfesseur> annees) {
        this.annees = annees;
    }
}
