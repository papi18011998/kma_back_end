package com.example.spring_jwt_get_arrays.dto;



public class MatiereDTO {
    private Long id;
    private String libelle;
    private int coefficient;

    public MatiereDTO() {
    }

    public MatiereDTO(Long id, String libelle, int coefficient) {
        this.id = id;
        this.libelle = libelle;
        this.coefficient = coefficient;
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

    public int getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }
}
