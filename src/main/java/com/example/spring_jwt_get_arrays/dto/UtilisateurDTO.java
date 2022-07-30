package com.example.spring_jwt_get_arrays.dto;

import com.example.spring_jwt_get_arrays.domain.Genre;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UtilisateurDTO {
    private Long id;
    private String prenom;
    private String nom;
    private String login;
    private String adresse;
    private Boolean is_active;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private Genre genre;
    private String telephone;

    public UtilisateurDTO() {
    }

    public UtilisateurDTO(Long id, String prenom, String nom, String login, String adresse, Boolean is_active, String password, Genre genre, String telephone) {
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
        this.login = login;
        this.adresse = adresse;
        this.is_active = is_active;
        this.password = password;
        this.genre = genre;
        this.telephone = telephone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Boolean getIs_active() {
        return is_active;
    }

    public void setIs_active(Boolean is_active) {
        this.is_active = is_active;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
