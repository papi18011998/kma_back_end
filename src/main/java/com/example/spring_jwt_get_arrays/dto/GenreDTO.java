package com.example.spring_jwt_get_arrays.dto;

public class GenreDTO {
    private Long id;
    private String libelle;

    public GenreDTO() {
    }

    public GenreDTO(Long id, String libelle) {
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
