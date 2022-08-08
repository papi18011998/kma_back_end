package com.example.spring_jwt_get_arrays.repository;


import com.example.spring_jwt_get_arrays.domain.Classe;
import com.example.spring_jwt_get_arrays.domain.Eleve;
import com.example.spring_jwt_get_arrays.domain.EleveClasse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RepositoryRestResource
@CrossOrigin("*")
public interface EleveClasseRepository extends CrudRepository<EleveClasse, Long> {
    public List<EleveClasse>findByClasseAndAnneeScolaire(Classe classe, String annee_scolaire);
}
