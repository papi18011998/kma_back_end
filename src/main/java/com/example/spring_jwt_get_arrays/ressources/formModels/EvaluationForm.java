package com.example.spring_jwt_get_arrays.ressources.formModels;

public class EvaluationForm {
    private double note;
    private long idProfesseur;
    private long idEleve;

    public EvaluationForm() {
    }

    public EvaluationForm(double note, long idProfesseur, long idEleve) {
        this.note = note;
        this.idProfesseur = idProfesseur;
        this.idEleve = idEleve;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }

    public long getIdProfesseur() {
        return idProfesseur;
    }

    public void setIdProfesseur(long idProfesseur) {
        this.idProfesseur = idProfesseur;
    }

    public long getIdEleve() {
        return idEleve;
    }

    public void setIdEleve(long idEleve) {
        this.idEleve = idEleve;
    }
}
