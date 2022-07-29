package com.example.spring_jwt_get_arrays.listener;

import com.example.spring_jwt_get_arrays.domain.User;
import com.example.spring_jwt_get_arrays.service.LoginAttempsService;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationSuccessListener {
    private final  LoginAttempsService loginAttempsService;

    public AuthenticationSuccessListener(LoginAttempsService loginAttempsService) {
        this.loginAttempsService = loginAttempsService;
    }

@EventListener
    public void onAuthenticationSuccess(AuthenticationSuccessEvent event){
        Object principal = event.getAuthentication().getPrincipal();
        if(principal instanceof User){
            User user = (User) event.getAuthentication().getPrincipal();
            loginAttempsService.evictUserFromLoginAttemptCache(user.getUserName());
        }
    }
}
