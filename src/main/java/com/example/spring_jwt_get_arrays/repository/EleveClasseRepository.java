package com.example.spring_jwt_get_arrays.repository;


import com.example.spring_jwt_get_arrays.domain.Classe;
import com.example.spring_jwt_get_arrays.domain.EleveClasse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Map;

@RepositoryRestResource
@CrossOrigin("*")
public interface EleveClasseRepository extends CrudRepository<EleveClasse, Long> {
    public List<EleveClasse>findByClasseAndAnneeScolaire(Classe classe, String annee_scolaire);
    @Query(value = "SELECT classe.libelle, COUNT(user.id)\n" +
            "FROM classe,eleve_classe,user\n" +
            "WHERE classe.id = eleve_classe.classe_id\n" +
            "AND user.id = eleve_classe.eleve_id\n" +
            "AND eleve_classe.annee_scolaire='2021-2022'\n" +
            "GROUP BY classe.libelle", nativeQuery = true)
    public List<String> getElevesByClasse();

}
