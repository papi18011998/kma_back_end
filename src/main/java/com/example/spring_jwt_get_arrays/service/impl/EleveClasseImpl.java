package com.example.spring_jwt_get_arrays.service.impl;

import com.example.spring_jwt_get_arrays.domain.Classe;
import com.example.spring_jwt_get_arrays.dto.EleveDTO;
import com.example.spring_jwt_get_arrays.mappers.KmaMapper;
import com.example.spring_jwt_get_arrays.repository.EleveClasseRepository;
import com.example.spring_jwt_get_arrays.service.IEleveClasse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EleveClasseImpl implements IEleveClasse {
    private final EleveClasseRepository eleveClasseRepository;
    private final KmaMapper mapper;

    public EleveClasseImpl(EleveClasseRepository eleveClasseRepository, KmaMapper mapper) {
        this.eleveClasseRepository = eleveClasseRepository;
        this.mapper = mapper;
    }

    @Override
    public List<EleveDTO> findByClasseAndAnnee_scolaire(Classe classe, String annee_scolaire) {
        List<EleveDTO> eleveDTOS = new ArrayList<>();
        eleveClasseRepository.findByClasseAndAnneeScolaire(classe,annee_scolaire).forEach(eleveClasse -> {
            eleveDTOS.add(mapper.eleve_to_eleveDTO(eleveClasse.getEleve()));
        });
        return eleveDTOS;
    }
}
