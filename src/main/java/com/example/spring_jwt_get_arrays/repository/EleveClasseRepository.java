package com.example.spring_jwt_get_arrays.repository;


import com.example.spring_jwt_get_arrays.domain.EleveClasse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin("*")
public interface EleveClasseRepository extends CrudRepository<EleveClasse, Long> {

}
