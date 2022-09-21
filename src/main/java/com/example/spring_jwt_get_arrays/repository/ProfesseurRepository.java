package com.example.spring_jwt_get_arrays.repository;

import com.example.spring_jwt_get_arrays.domain.Professeur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin("*")
public interface ProfesseurRepository extends JpaRepository<Professeur,Long> {
    @Query(value = "SELECT COUNT(eleve.id)\n" +
            "FROM `user` professeur, classe_professeur, eleve_classe, `user` eleve\n" +
            "WHERE professeur.id = classe_professeur.professeur_id\n" +
            "AND classe_professeur.classe_id = eleve_classe.classe_id\n" +
            "AND eleve_classe.eleve_id = eleve.id\n" +
            "AND professeur.id = :professeurId",nativeQuery = true)
    public  Long countManagedEleves(@Param("professeurId") long professeurId);
}
