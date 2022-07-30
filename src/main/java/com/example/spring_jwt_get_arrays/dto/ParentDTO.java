package com.example.spring_jwt_get_arrays.dto;


import com.example.spring_jwt_get_arrays.domain.Genre;

public class ParentDTO extends  UtilisateurDTO{
    private String cni;
    private String telephone;

    public ParentDTO() {
        super();
    }

    public ParentDTO(Long id, String prenom, String nom, String login, String adresse, Boolean is_active, String password, Genre genre, String telephone, String cni, String telephone1) {
        super(id, prenom, nom, login, adresse, is_active, password, genre, telephone);
        this.cni = cni;
        this.telephone = telephone1;
    }

    public String getCni() {
        return cni;
    }

    public void setCni(String cni) {
        this.cni = cni;
    }

    @Override
    public String getTelephone() {
        return telephone;
    }

    @Override
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
