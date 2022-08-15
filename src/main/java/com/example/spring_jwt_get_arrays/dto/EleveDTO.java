package com.example.spring_jwt_get_arrays.dto;


import com.example.spring_jwt_get_arrays.domain.Genre;

import java.util.Collection;
import java.util.Date;

public class EleveDTO extends  UtilisateurDTO{
    private String matricule;
    private Date date_naissance;
    private ParentDTO parent;

    public Collection<EvaluationDTO> getEvaluations() {
        return evaluations;
    }

    public void setEvaluations(Collection<EvaluationDTO> evaluations) {
        this.evaluations = evaluations;
    }

    private Collection<EvaluationDTO> evaluations;

    public EleveDTO() {
        super();
    }

    public EleveDTO(Long id, String prenom, String nom, String userName, String password, String adresse, Date joinDate, String role, String[] authorities, boolean isActive, boolean isNotLocked, String telephone, Genre genre, String matricule, Date date_naissance, ParentDTO parent, Collection<EvaluationDTO> evaluations) {
        super(id, prenom, nom, userName, password, adresse, joinDate, role, authorities, isActive, isNotLocked, telephone, genre);
        this.matricule = matricule;
        this.date_naissance = date_naissance;
        this.parent = parent;
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

    public ParentDTO getParent() {
        return parent;
    }

    public void setParent(ParentDTO parent) {
        this.parent = parent;
    }
}
