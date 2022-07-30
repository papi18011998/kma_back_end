package com.example.spring_jwt_get_arrays.service.impl;

import static com.example.spring_jwt_get_arrays.constant.UserImplConstant.*;

import com.example.spring_jwt_get_arrays.domain.Genre;
import com.example.spring_jwt_get_arrays.domain.User;
import com.example.spring_jwt_get_arrays.domain.UserPrincipal;
import com.example.spring_jwt_get_arrays.exception.domain.UserNotFoundException;
import com.example.spring_jwt_get_arrays.exception.domain.UsernameExistException;
import com.example.spring_jwt_get_arrays.repository.GenreRepository;
import com.example.spring_jwt_get_arrays.repository.UserRepository;
import com.example.spring_jwt_get_arrays.service.LoginAttempsService;
import com.example.spring_jwt_get_arrays.service.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.mail.MessagingException;
import javax.transaction.TransactionScoped;
import java.util.Date;
import java.util.List;

import static com.example.spring_jwt_get_arrays.enumeration.Role.ROLE_ELEVE;

@Service
@TransactionScoped
@Qualifier("UserDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService {
    private Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;
    private LoginAttempsService loginAttempsService;
    private final GenreRepository genreRepository;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, LoginAttempsService loginAttempsService, GenreRepository genreRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.loginAttempsService = loginAttempsService;
        this.genreRepository = genreRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if(user == null){
            LOGGER.error("User not found by username "+ username);
            throw  new UsernameNotFoundException("User not found by username "+ username);
        }else{
            validateLoginAttempt(user);
            user.setJoinDate(new Date());
            userRepository.save(user);
            UserPrincipal userPrincipal = new UserPrincipal(user);
            LOGGER.info("Returning found user by username"+username);
            return  userPrincipal;
        }
    }

    private void validateLoginAttempt(User user) {
        if (user.isNotLocked()){
            if (loginAttempsService.hasExceededMaxAttempts(user.getUserName())){
                user.setNotLocked(false);
            }else {
                user.setNotLocked(true);
            }
        }else{
            loginAttempsService.evictUserFromLoginAttemptCache(user.getUserName());
        }
    }

    @Override
    public User register(String prenom, String nom, String username, String adresse, String telephone, Long genreId) throws UserNotFoundException,UsernameExistException, MessagingException {
        validateNewUsername(StringUtils.EMPTY,username);
        User user = new User();
        Genre genre = genreRepository.findById(genreId).orElse(null);
        String password = generatePassword();
        String encodePassword = encodePassword(password);
        user.setPrenom(prenom);
        user.setNom(nom);
        user.setAdresse(adresse);
        user.setTelephone(telephone);
        user.setUserName(username);
        user.setJoinDate(new Date());
        user.setPassword(encodePassword);
        user.setActive(true);
        user.setNotLocked(true);
        user.setGenre(genre);
        user.setRole(ROLE_ELEVE.name());
        user.setAuthorities(ROLE_ELEVE.getAuthorities());
        userRepository.save(user);
        LOGGER.info("New user password is: "+password);
        return user;
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    private String generatePassword() {
        return RandomStringUtils.randomAlphanumeric(10);
    }

    private String generateUserId() {
        return RandomStringUtils.randomNumeric(10);
    }

    private User validateNewUsername(String currentUsername, String newUsername) throws UserNotFoundException, UsernameExistException{
        User userByNewUsername = findUserByUsername(newUsername);
        if(StringUtils.isNotBlank(currentUsername)) {
            User currentUser = findUserByUsername(currentUsername);
            if(currentUser == null) {
                throw new UserNotFoundException(NO_USER_FOUND_BY_USERNAME + currentUsername);
            }
            if(userByNewUsername != null && !currentUser.getId().equals(userByNewUsername.getId())) {
                throw new UsernameExistException(USERNAME_IS_ALREADY_EXIST);
            }
            return currentUser;
        } else {
            if(userByNewUsername != null) {
                throw new UsernameExistException(USERNAME_IS_ALREADY_EXIST);
            }
            return null;
        }
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUserName(username);
    }
}
