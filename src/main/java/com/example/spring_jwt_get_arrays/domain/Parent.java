package com.example.spring_jwt_get_arrays.domain;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Collection;
import java.util.Date;

@Entity
@DiscriminatorValue("parent")
public class Parent extends User{
    private String cni;
    @OneToMany(mappedBy = "parent")
    private Collection<Eleve> eleves;

    public Parent() {
        super();
    }

    public Parent(Long id, String prenom, String nom, String userName, String password, String adresse, Date joinDate, String role, String[] authorities, boolean isActive, boolean isNotLocked, String telephone, Genre genre, String cni, Collection<Eleve> eleves) {
        super(id, prenom, nom, userName, password, adresse, joinDate, role, authorities, isActive, isNotLocked, telephone, genre);
        this.cni = cni;
        this.eleves = eleves;
    }

    public String getCni() {
        return cni;
    }

    public void setCni(String cni) {
        this.cni = cni;
    }

    public Collection<Eleve> getEleves() {
        return eleves;
    }

    public void setEleves(Collection<Eleve> eleves) {
        this.eleves = eleves;
    }
}
