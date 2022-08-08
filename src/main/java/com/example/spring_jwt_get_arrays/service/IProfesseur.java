package com.example.spring_jwt_get_arrays.service;


import com.example.spring_jwt_get_arrays.domain.Professeur;
import com.example.spring_jwt_get_arrays.dto.ClasseDTO;
import com.example.spring_jwt_get_arrays.dto.ProfesseurDTO;

import java.util.List;

public interface IProfesseur {
    public List<ProfesseurDTO> getProfesseurs();
    public ProfesseurDTO addProfesseur(ProfesseurDTO professeurDTO);
    public ProfesseurDTO updateProfesseur(long id,ProfesseurDTO professeurDTO);
    public List<ClasseDTO> findByProfesseurAndAnneeScolaire(Professeur professeur, String annee);
}
