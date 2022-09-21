package com.example.spring_jwt_get_arrays.ressources.formModels;


import java.util.Date;

public class EleveForm {
//    Eleve Informations
    private String prenom;
    private String nom;
    private String adresse;
    private long genreId;
    private Date date_naissance;
    private long annee;
//    ParentInformations
    private String prenomParent;
    private String nomParent;
    private String adresseParent;
    private String telephone;
    private String cni;
    private String userName;
    private long genreIdParent;

    public EleveForm() {
    }

    public EleveForm(String prenom, String nom, String adresse, long genreId, Date date_naissance, long annee, String prenomParent, String nomParent, String adresseParent, String telephone, String cni, String userName, long genreIdParent) {
        this.prenom = prenom;
        this.nom = nom;
        this.adresse = adresse;
        this.genreId = genreId;
        this.date_naissance = date_naissance;
        this.annee = annee;
        this.prenomParent = prenomParent;
        this.nomParent = nomParent;
        this.adresseParent = adresseParent;
        this.telephone = telephone;
        this.cni = cni;
        this.userName = userName;
        this.genreIdParent = genreIdParent;
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

    public long getGenreId() {
        return genreId;
    }

    public void setGenreId(long genreId) {
        this.genreId = genreId;
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

    public String getPrenomParent() {
        return prenomParent;
    }

    public void setPrenomParent(String prenomParent) {
        this.prenomParent = prenomParent;
    }

    public String getNomParent() {
        return nomParent;
    }

    public void setNomParent(String nomParent) {
        this.nomParent = nomParent;
    }

    public String getAdresseParent() {
        return adresseParent;
    }

    public void setAdresseParent(String adresseParent) {
        this.adresseParent = adresseParent;
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

    public long getGenreIdParent() {
        return genreIdParent;
    }

    public void setGenreIdParent(long genreIdParent) {
        this.genreIdParent = genreIdParent;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
