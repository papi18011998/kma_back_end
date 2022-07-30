package com.example.spring_jwt_get_arrays.dto;


import com.example.spring_jwt_get_arrays.domain.Genre;

import java.util.Date;
public class ProfesseurDTO extends UtilisateurDTO{
    private Date date_prise_fonction;
    private MatiereDTO matiere;

    public ProfesseurDTO() {
        super();
    }

    public ProfesseurDTO(Long id, String prenom, String nom, String login, String adresse, Boolean is_active, String password, Genre genre, String telephone, Date date_prise_fonction, MatiereDTO matiere) {
        super(id, prenom, nom, login, adresse, is_active, password, genre, telephone);
        this.date_prise_fonction = date_prise_fonction;
        this.matiere = matiere;
    }

    public Date getDate_prise_fonction() {
        return date_prise_fonction;
    }

    public void setDate_prise_fonction(Date date_prise_fonction) {
        this.date_prise_fonction = date_prise_fonction;
    }

    public MatiereDTO getMatiere() {
        return matiere;
    }

    public void setMatiere(MatiereDTO matiere) {
        this.matiere = matiere;
    }
}
