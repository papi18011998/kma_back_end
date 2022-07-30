package com.example.spring_jwt_get_arrays.dto;


import com.example.spring_jwt_get_arrays.domain.Genre;

import java.util.Date;

public class EleveDTO extends  UtilisateurDTO{
    private String matricule;
    private Date date_naissance;

    public EleveDTO() {
        super();
    }

    public EleveDTO(Long id, String prenom, String nom, String login, String adresse, Boolean is_active, String password, Genre genre, String telephone, String matricule, Date date_naissance) {
        super(id, prenom, nom, login, adresse, is_active, password, genre, telephone);
        this.matricule = matricule;
        this.date_naissance = date_naissance;
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
}
