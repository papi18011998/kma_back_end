package com.example.spring_jwt_get_arrays.service;


import com.example.spring_jwt_get_arrays.domain.Eleve;
import com.example.spring_jwt_get_arrays.dto.EleveDTO;

import java.util.List;


public interface IEleve {
    public Eleve addEleve(Eleve eleve);
    public List<EleveDTO> getEleves();
    public long countAll();
    public List<String> getTopfiveScore();
}
