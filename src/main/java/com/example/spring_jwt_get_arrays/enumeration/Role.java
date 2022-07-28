package com.example.spring_jwt_get_arrays.enumeration;

import com.example.spring_jwt_get_arrays.constant.Authority;

import static com.example.spring_jwt_get_arrays.constant.Authority.*;

public enum Role {
    ROLE_USER(USER_AUTHORITIES),
    ROLE_HR(HR_AUTHORITIES),
    ROLE_MANAGER(MANAGER_AUTHORITIES),
    ROLE_ADMIN(ADMIN_AUTHORITIES),
    ROLE_SUPER_USER(SUPER_ADMIN_AUTHORITIES);

    private String[] authorities;

    Role(String... authorities){
        this.authorities = authorities;
    }
    public String [] getAuthorities(){
        return authorities;
    }
}
