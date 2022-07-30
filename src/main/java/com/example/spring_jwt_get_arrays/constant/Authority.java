package com.example.spring_jwt_get_arrays.constant;

public class Authority {
    public static final String[] ELEVE_AUTHORITIES = {"user:read"};
    public static final String[] ADMIN_AUTHORITIES = {"user:read","user:update","user:create","user:delete"};
    public static final String[] PROFESSEUR_AUTHORITIES = {"user:read","user:update","user:create"};
    public static final String[] PARENT_AUTHORITIES = {"user:read"};
}
