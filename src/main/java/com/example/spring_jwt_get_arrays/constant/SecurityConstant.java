package com.example.spring_jwt_get_arrays.constant;

public class SecurityConstant {
    public static final long EXPIRATION_TIME = 432_000_000;//five days in millisecondes
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String GET_ARRAYS_LLC = "Keur Maman Anthiou";
    public static final String GET_ARRAYS_ADMINISTRATION = "User Management Portal";
    public static final String AUTHORITIES = "Authorities";
    public static final String FORBIDDEN_MESSAGE = "Vous devez vous authentifier pour cette à cette ressource !!!";
    public static final String JWT_TOKEN_HEADER = "Jwt-Token";
    public static final String TOKEN_CANNOT_BE_VERIFIED = "Le jeton doit être vérifié";
    public static final String  ACCESS_DENIED_MESSAGE = "Vous n'avez pas les droits pour acceder à cette ressource";
    public static final String OPTIONS_HTTP_METHOD = "OPTIONS";
    public static final String[] PUBLIC_URLS = {"/users/login","/swagger-ui/index.html","/evaluations"};
}
