package com.example.spring_jwt_get_arrays.exception.domain;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.spring_jwt_get_arrays.domain.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.persistence.NoResultException;
import java.io.IOException;
import java.rmi.AccessException;

import java.util.Objects;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class ExceptionHandling  implements ErrorController {
    private final  Logger LOGGER = LoggerFactory.getLogger(getClass());
    private static final String ACCOUNT_LOCKED = "Votre compte est blocqué, contatez l'administrateur !!!";
    private static final String METHOD_IS_NOT_ALLOWED = "Ce point d'entrée n'existe pas. Envoyez une '%s' request";
    private static final String INCORRECT_CREDENTIALS = "login ou mot de passe incorrect. SVP Réessayez !!!";
    private static final String ACCOUNT_DISABLED = "Votre compte est desactivé, contatez l'administrateur !!!";
    private static final String ERROR_PROCESSING_FILE = "Error occurred while processing file";
    private static final String NOT_ENOUGH_PERMISSION = "Vous n'avez les droits pour atteidre la ressource souhaité";
    public static final String ERROR_PATH= "/error";

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<HttpResponse> accountDisabledException(){
        return  createHttpResponse(BAD_REQUEST, ACCOUNT_DISABLED);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<HttpResponse> badCredentialsException(){
        return createHttpResponse(BAD_REQUEST, INCORRECT_CREDENTIALS);
    }
    @ExceptionHandler(AccessException.class)
    public ResponseEntity<HttpResponse> accessDeniedException(){
        return  createHttpResponse(FORBIDDEN,NOT_ENOUGH_PERMISSION);
    }
    @ExceptionHandler(LockedException.class)
    public ResponseEntity<HttpResponse> lockedException(){
        return  createHttpResponse(UNAUTHORIZED,ACCOUNT_LOCKED);
    }
    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<HttpResponse> tokenExpireException(TokenExpiredException exception){
        return createHttpResponse(UNAUTHORIZED, exception.getMessage().toUpperCase());
    }
    @ExceptionHandler(EmailExistException.class)
    public ResponseEntity<HttpResponse> emailExistException(EmailExistException exception) {
        return createHttpResponse(BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(UsernameExistException.class)
    public ResponseEntity<HttpResponse> usernameExistException(UsernameExistException exception) {
        return createHttpResponse(BAD_REQUEST, exception.getMessage());
    }
    @ExceptionHandler(EmailNotFoundException.class)
    public ResponseEntity<HttpResponse> emailNotFoundException(EmailNotFoundException exception) {
        return createHttpResponse(BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<HttpResponse> userNotFoundException(UserNotFoundException exception) {
        return createHttpResponse(BAD_REQUEST, exception.getMessage());
    }
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<HttpResponse> urlNotfoundException(NoHandlerFoundException e) {
        return createHttpResponse(BAD_REQUEST, "Cette page est introuvable !!!");
    }
    @ExceptionHandler(ClasseAlreadyExistException.class)
    public ResponseEntity<HttpResponse> classeAlreadyExistException(ClasseAlreadyExistException invalidNoteException) {
        return createHttpResponse(BAD_REQUEST, invalidNoteException.getMessage());
    }
    @ExceptionHandler(InvalidNoteException.class)
    public ResponseEntity<HttpResponse> invalidNoteException(InvalidNoteException invalidNoteException) {
        return createHttpResponse(BAD_REQUEST, invalidNoteException.getMessage());
    }
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<HttpResponse> methodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
        HttpMethod supportedMethod = Objects.requireNonNull(exception.getSupportedHttpMethods()).iterator().next();
        return createHttpResponse(METHOD_NOT_ALLOWED, String.format(METHOD_IS_NOT_ALLOWED, supportedMethod));
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<HttpResponse> internalServerErrorException(Exception exception) {
//        LOGGER.error(exception.getMessage());
//        return createHttpResponse(INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_MSG);
//    }

//    @ExceptionHandler(NotAnImageFileException.class)
//    public ResponseEntity<HttpResponse> notAnImageFileException(NotAnImageFileException exception) {
//        LOGGER.error(exception.getMessage());
//        return createHttpResponse(BAD_REQUEST, exception.getMessage());
//    }

    @ExceptionHandler(NoResultException.class)
    public ResponseEntity<HttpResponse> notFoundException(NoResultException exception) {
        LOGGER.error(exception.getMessage());
        return createHttpResponse(NOT_FOUND, exception.getMessage());
    }

//    @RequestMapping(ERROR_PATH)
//    public ResponseEntity<HttpResponse> notFounf404() {
//        return createHttpResponse(NOT_FOUND,"There is no mapping for this URL");
//    }
    @ExceptionHandler(IOException.class)
    public ResponseEntity<HttpResponse> iOException(IOException exception) {
        LOGGER.error(exception.getMessage());
        return createHttpResponse(INTERNAL_SERVER_ERROR, exception.getMessage());
    }


    private ResponseEntity<HttpResponse> createHttpResponse(HttpStatus httpStatus, String message){
        HttpResponse httpResponse = new HttpResponse(httpStatus.value(),httpStatus, httpStatus.getReasonPhrase().toUpperCase(),message);
        return  new ResponseEntity<>(httpResponse,httpStatus);
    }

}
