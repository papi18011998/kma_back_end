package com.example.spring_jwt_get_arrays.repository;

import com.example.spring_jwt_get_arrays.domain.Evaluation;
import com.example.spring_jwt_get_arrays.dto.EvaluationDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Repository
@CrossOrigin("*")
public interface EvaluationRepository extends JpaRepository<Evaluation,Long> {
    @Query(value = "SELECT note,COUNT(note) AS 'occurrence' FROM evaluation GROUP BY note ORDER BY 'occurrence' DESC LIMIT 1",nativeQuery = true)
    public long getMostFrequentScore();
}
