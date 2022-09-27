package com.example.spring_jwt_get_arrays.repository;

import com.example.spring_jwt_get_arrays.domain.Eleve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RepositoryRestResource
@CrossOrigin("*")
public interface EleveRepository extends JpaRepository<Eleve,Long> {
    @Query(value = "SELECT concat(user.prenom,' ', user.nom),evaluation.note\n" +
            "FROM user,evaluation,eleve_classe\n" +
            "WHERE user.id = evaluation.eleve_id\n" +
            "AND user.id = eleve_classe.eleve_id\n" +
            "AND eleve_classe.annee_scolaire ='2021-2022'\n" +
            "ORDER BY evaluation.note DESC\n" +
            "LIMIT 5", nativeQuery = true)
    public List<String> getTopfiveScore();

    @Query(value = "SELECT e.note \n" +
            "FROM `user` eleve, evaluation e, eleve_classe ec \n" +
            "WHERE eleve .id  = e.eleve_id \n" +
            "AND eleve.id = ec.eleve_id\n" +
            "AND eleve.id = :idEleve\n" +
            "ORDER BY e.note DESC\n" +
            "LIMIT 1",nativeQuery = true)
    public String bestScore(@Param("idEleve") long idEleve);

    @Query(value="SELECT AVG(e.note) \n" +
            "FROM `user` eleve, evaluation e, eleve_classe ec \n" +
            "WHERE eleve .id  = e.eleve_id \n" +
            "AND eleve.id = ec.eleve_id\n" +
            "AND eleve.id = :idEleve\n" +
            "GROUP  BY eleve.id ", nativeQuery = true)
    public String averageScore(@Param("idEleve") long idEleve);
    @Query(value = "SELECT note \n" +
            "FROM user eleve, evaluation, eleve_classe\n" +
            "WHERE eleve.id = evaluation.eleve_id\n" +
            "AND eleve.id = eleve_classe.eleve_id\n" +
            "AND eleve.id = :idEleve\n" +
            "GROUP BY note ORDER BY COUNT(note) DESC LIMIT 1",nativeQuery = true)
    public String frequentScore(@Param("idEleve") long idEleve);
}
