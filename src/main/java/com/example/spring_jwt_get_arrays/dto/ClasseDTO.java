package com.example.spring_jwt_get_arrays.dto;

public class ClasseDTO {
    private Long id;
    private String libelle;

    public ClasseDTO() {
    }

    public ClasseDTO(Long id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
