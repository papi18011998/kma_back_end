package com.example.spring_jwt_get_arrays.enumeration;

import com.example.spring_jwt_get_arrays.constant.Authority;

import static com.example.spring_jwt_get_arrays.constant.Authority.*;

public enum Role {
    ROLE_PARENT(PARENT_AUTHORITIES),
    ROLE_PROFESSEUR(PROFESSEUR_AUTHORITIES),
    ROLE_ELEVE(ELEVE_AUTHORITIES),
    ROLE_ADMIN(ADMIN_AUTHORITIES);

    private String[] authorities;

    Role(String... authorities){
        this.authorities = authorities;
    }
    public String [] getAuthorities(){
        return authorities;
    }
}
