package com.example.spring_jwt_get_arrays.repository;

import com.example.spring_jwt_get_arrays.domain.ClasseProfesseur;
import com.example.spring_jwt_get_arrays.domain.Professeur;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RepositoryRestResource
@CrossOrigin("*")
public interface ClasseProfesseurRepository extends CrudRepository<ClasseProfesseur, Long> {
    public List<ClasseProfesseur> findByProfesseurAndAnneeScolaire(Professeur professeur,String annee);
}

