package com.example.spring_jwt_get_arrays.service;

import com.example.spring_jwt_get_arrays.domain.User;

import java.util.List;

public interface UserService {
    User register(String firstName,String lastName,String username,String email);
    List<User> getUsers();
    User findUserByUsername(String username);
    User findUserByEmail(String email);
}
