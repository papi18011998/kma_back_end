package com.example.spring_jwt_get_arrays.ressources.formModels;

public class UserForm {
    private String prenom;
    private String nom;
    private String userName;
    private String adresse;
    private String telephone;
    private Long genreId;

    public UserForm(String prenom, String nom, String userName, String adresse, String telephone, Long genreId) {
        this.prenom = prenom;
        this.nom = nom;
        this.userName = userName;
        this.adresse = adresse;
        this.telephone = telephone;
        this.genreId = genreId;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public Long getGenreId() {
        return genreId;
    }

    public void setGenreId(Long genreId) {
        this.genreId = genreId;
    }
}
