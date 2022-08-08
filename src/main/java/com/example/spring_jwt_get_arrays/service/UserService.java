package com.example.spring_jwt_get_arrays.service;

import com.example.spring_jwt_get_arrays.domain.User;
import com.example.spring_jwt_get_arrays.dto.UtilisateurDTO;
import com.example.spring_jwt_get_arrays.exception.domain.UserNotFoundException;
import com.example.spring_jwt_get_arrays.exception.domain.UsernameExistException;

import javax.mail.MessagingException;
import java.util.List;

public interface UserService {
    User register(String prenom, String nom, String username,String adresse,String telephone, Long genreId) throws UserNotFoundException,UsernameExistException, MessagingException;
    List<User> getUsers();
    public UtilisateurDTO getUser(Long id) throws UserNotFoundException;
    public UtilisateurDTO change_status(Long id) throws UserNotFoundException;
    public UtilisateurDTO update_profile(Long id,UtilisateurDTO utilisateurDTO);
    public UtilisateurDTO findByTelephone(String telephone);
    User findUserByUsername(String username);
}
