package com.example.spring_jwt_get_arrays.repository;

import com.example.spring_jwt_get_arrays.domain.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre,Long> {
}
