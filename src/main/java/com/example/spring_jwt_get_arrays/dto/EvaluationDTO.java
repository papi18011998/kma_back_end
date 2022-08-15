package com.example.spring_jwt_get_arrays.dto;


import com.example.spring_jwt_get_arrays.domain.Eleve;
import com.example.spring_jwt_get_arrays.domain.Matiere;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class EvaluationDTO {
    private Long id;
    private double note;
    private Date date_evaluation;
    private MatiereDTO matiere;
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private EleveDTO eleve;

    public EvaluationDTO() {
    }

    public EvaluationDTO(Long id, double note, Date date_evaluation, MatiereDTO matiere, EleveDTO eleve) {
        this.id = id;
        this.note = note;
        this.date_evaluation = date_evaluation;
        this.matiere = matiere;
        this.eleve = eleve;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }

    public Date getDate_evaluation() {
        return date_evaluation;
    }

    public void setDate_evaluation(Date date_evaluation) {
        this.date_evaluation = date_evaluation;
    }

    public MatiereDTO getMatiere() {
        return matiere;
    }

    public void setMatiere(MatiereDTO matiere) {
        this.matiere = matiere;
    }

    public EleveDTO getEleve() {
        return eleve;
    }

    public void setEleve(EleveDTO eleve) {
        this.eleve = eleve;
    }
}
