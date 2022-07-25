package com.example.spring_jwt_get_arrays.service.impl;

import com.example.spring_jwt_get_arrays.domain.User;
import com.example.spring_jwt_get_arrays.domain.UserPrincipal;
import com.example.spring_jwt_get_arrays.repository.UserRepository;
import com.example.spring_jwt_get_arrays.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.TransactionScoped;
import java.util.Date;

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
}
