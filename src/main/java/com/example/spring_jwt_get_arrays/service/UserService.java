package com.example.spring_jwt_get_arrays.service;

import com.example.spring_jwt_get_arrays.domain.User;
import com.example.spring_jwt_get_arrays.exception.domain.EmailExistException;
import com.example.spring_jwt_get_arrays.exception.domain.UserNotFoundException;
import com.example.spring_jwt_get_arrays.exception.domain.UsernameExistException;

import java.util.List;

public interface UserService {
    User register(String firstName,String lastName,String username,String email) throws UserNotFoundException, EmailExistException, UsernameExistException;
    List<User> getUsers();
    User findUserByUsername(String username);
    User findUserByEmail(String email);
}
