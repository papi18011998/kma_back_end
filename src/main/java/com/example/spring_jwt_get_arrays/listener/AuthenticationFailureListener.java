package com.example.spring_jwt_get_arrays.listener;

import com.example.spring_jwt_get_arrays.service.LoginAttempsService;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFailureListener {
    private  final LoginAttempsService loginAttempsService;

    public AuthenticationFailureListener(LoginAttempsService loginAttempsService) {
        this.loginAttempsService = loginAttempsService;
    }
    @EventListener
    public void onAuthenticationFailure(AuthenticationFailureBadCredentialsEvent event){
        Object principal = event.getAuthentication().getPrincipal();
        if (principal instanceof String){
            String username = (String) event.getAuthentication().getPrincipal();
            loginAttempsService.adUserToLoginAttemptCache(username);
        }
    }
}

