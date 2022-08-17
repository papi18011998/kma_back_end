package com.example.spring_jwt_get_arrays.repository;

import com.example.spring_jwt_get_arrays.domain.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@RepositoryRestResource
@CrossOrigin("*")
public interface ParentRepository extends JpaRepository<Parent,Long> {
    public Optional<Parent> findByCni(String cni);
    @Query(value = "SELECT evaluation.note\n" +
            "FROM evaluation, user as eleve, user as parent \n" +
            "WHERE eleve.id = evaluation.eleve_id\n" +
            "AND eleve.parent_id = parent.id\n" +
            "AND parent.id = :parentId\n" +
            "ORDER BY evaluation.note DESC\n" +
            "LIMIT 1", nativeQuery = true)
    public String getMostFrequentScore(@Param("parentId") long parentId);
}
