package com.example.spring_jwt_get_arrays.domain;



import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;
@Entity
public class Evaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double note;
    @Temporal(TemporalType.DATE)
    private Date date_evaluation;
    @ManyToOne
    private Matiere matiere;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    private Eleve eleve;

    public Evaluation() {
    }

    public Evaluation(Long id, double note, Date date_evaluation, Matiere matiere, Eleve eleve) {
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

    public Matiere getMatiere() {
        return matiere;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }

    public Eleve getEleve() {
        return eleve;
    }

    public void setEleve(Eleve eleve) {
        this.eleve = eleve;
    }
}
