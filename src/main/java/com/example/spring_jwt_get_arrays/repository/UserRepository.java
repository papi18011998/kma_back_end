package com.example.spring_jwt_get_arrays.repository;

import com.example.spring_jwt_get_arrays.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@RepositoryRestResource
@CrossOrigin("*")
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUserName(String username);

    Optional<User> findByTelephone(String telephone);
}
