package com.example.spring_jwt_get_arrays.repository;

import com.example.spring_jwt_get_arrays.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUserName(String username);
    User findByEmail(String email);
}
