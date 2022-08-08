package com.example.spring_jwt_get_arrays.service;

import com.example.spring_jwt_get_arrays.domain.Classe;
import com.example.spring_jwt_get_arrays.dto.EleveDTO;

import java.util.List;

public interface IEleveClasse {
    public List<EleveDTO> findByClasseAndAnnee_scolaire(Classe classe, String annee_scolaire);
}
