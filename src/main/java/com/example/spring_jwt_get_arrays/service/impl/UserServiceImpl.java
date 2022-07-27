package com.example.spring_jwt_get_arrays.service.impl;

import com.example.spring_jwt_get_arrays.domain.User;
import com.example.spring_jwt_get_arrays.domain.UserPrincipal;
import com.example.spring_jwt_get_arrays.exception.domain.EmailExistException;
import com.example.spring_jwt_get_arrays.exception.domain.UserNotFoundException;
import com.example.spring_jwt_get_arrays.exception.domain.UsernameExistException;
import com.example.spring_jwt_get_arrays.repository.UserRepository;
import com.example.spring_jwt_get_arrays.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.TransactionScoped;
import java.util.Date;
import java.util.List;

@Service
@TransactionScoped
@Qualifier("UserDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService {
    private Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if(user == null){
            LOGGER.error("User not found by username "+ username);
            throw  new UsernameNotFoundException("User not found by username "+ username);
        }else{
            user.setLastLoginDateDisplay(user.getLastLoginDate());
            user.setLastLoginDate(new Date());
            userRepository.save(user);
            UserPrincipal userPrincipal = new UserPrincipal(user);
            LOGGER.info("Returning found user by username"+username);
            return  userPrincipal;
        }
    }

    @Override
    public User register(String firstName, String lastName, String username, String email) throws UserNotFoundException, EmailExistException, UsernameExistException {
        validateNewUsernameAnEmail(StringUtils.EMPTY,username,email);
        return null;
    }

    private User validateNewUsernameAnEmail(String currentUsername,String newUsername, String newEmail) throws EmailExistException, UsernameExistException, UserNotFoundException {
        if (StringUtils.isNotBlank(currentUsername)){
            User currentUser = findUserByUsername(currentUsername);
            if(currentUser == null){
                throw  new UserNotFoundException("No user found by username"+currentUsername);
            }
            User userByUserName = findUserByUsername(newUsername);
            if (userByUserName != null && currentUser.getId().equals(userByUserName.getId())){
                throw  new UsernameExistException("Username is already exist");
            }
            User userByEmail = findUserByUsername(newEmail);
            if (userByEmail != null && currentUser.getId().equals(userByEmail.getId())){
                throw  new EmailExistException("Email is already exist");
            }
            return currentUser;
        }else{
            User userByUserName = findUserByUsername(newUsername);
            if (userByUserName != null){
                throw  new UsernameExistException("Username is already exist");
            }
            User userByEmail = findUserByUsername(newEmail);
            if (userByEmail != null){
                throw  new EmailExistException("Email is already exist");
            }
            return null;
        }
    }

    @Override
    public List<User> getUsers() {
        return null;
    }

    @Override
    public User findUserByUsername(String username) {
        return null;
    }

    @Override
    public User findUserByEmail(String email) {
        return null;
    }
}
