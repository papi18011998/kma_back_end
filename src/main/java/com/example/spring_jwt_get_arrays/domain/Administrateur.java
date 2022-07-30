package com.example.spring_jwt_get_arrays.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@DiscriminatorValue("administrateur")
public class Administrateur extends User {
    public Administrateur(Long id, String prenom, String nom, String userName, String password, String adresse, Date joinDate, String role, String[] authorities, boolean isActive, boolean isNotLocked, String telephone, Genre genre) {
        super(id, prenom, nom, userName, password, adresse, joinDate, role, authorities, isActive, isNotLocked, telephone, genre);
    }

    public Administrateur() {

    }
}
