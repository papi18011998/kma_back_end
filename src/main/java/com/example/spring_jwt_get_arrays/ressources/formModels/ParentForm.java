package com.example.spring_jwt_get_arrays.ressources.formModels;

import java.util.Date;

public class ParentForm {
    //Parent Attributes
    private String prenom;
    private String nom;
    private String adresse;
    private String telephone;
    private String cni;
    private String userName;
    private long genre_id;
    //Eleve Attributes
    private String prenomEleve;
    private String nomEleve;
    private String adresseEleve;
    private long genre_idEleve;
    private Date date_naissance;
    private long annee;

    public ParentForm() {
    }

    public ParentForm(String prenom, String nom, String adresse, String telephone, String cni, String userName, long genre_id, String prenomEleve, String nomEleve, String adresseEleve, long genre_idEleve, Date date_naissance, long annee) {
        this.prenom = prenom;
        this.nom = nom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.cni = cni;
        this.userName = userName;
        this.genre_id = genre_id;
        this.prenomEleve = prenomEleve;
        this.nomEleve = nomEleve;
        this.adresseEleve = adresseEleve;
        this.genre_idEleve = genre_idEleve;
        this.date_naissance = date_naissance;
        this.annee = annee;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCni() {
        return cni;
    }

    public void setCni(String cni) {
        this.cni = cni;
    }

    public long getGenre_id() {
        return genre_id;
    }

    public void setGenre_id(long genre_id) {
        this.genre_id = genre_id;
    }

    public String getPrenomEleve() {
        return prenomEleve;
    }

    public void setPrenomEleve(String prenomEleve) {
        this.prenomEleve = prenomEleve;
    }

    public String getNomEleve() {
        return nomEleve;
    }

    public void setNomEleve(String nomEleve) {
        this.nomEleve = nomEleve;
    }

    public String getAdresseEleve() {
        return adresseEleve;
    }

    public void setAdresseEleve(String adresseEleve) {
        this.adresseEleve = adresseEleve;
    }

    public long getGenre_idEleve() {
        return genre_idEleve;
    }

    public void setGenre_idEleve(long genre_idEleve) {
        this.genre_idEleve = genre_idEleve;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }

    public long getAnnee() {
        return annee;
    }

    public void setAnnee(long annee) {
        this.annee = annee;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
