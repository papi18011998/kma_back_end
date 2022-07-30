package com.example.spring_jwt_get_arrays.dto;


import com.example.spring_jwt_get_arrays.domain.Genre;

public class AdministrateurDTO extends UtilisateurDTO{
    public AdministrateurDTO() {
        super();
    }

    public AdministrateurDTO(Long id, String prenom, String nom, String login, String adresse, Boolean is_active, String password, Genre genre, String telephone) {
        super(id, prenom, nom, login, adresse, is_active, password, genre, telephone);
    }
}
