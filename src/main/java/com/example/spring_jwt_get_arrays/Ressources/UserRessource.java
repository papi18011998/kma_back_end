package com.example.spring_jwt_get_arrays.Ressources;

import com.example.spring_jwt_get_arrays.domain.User;
import com.example.spring_jwt_get_arrays.exception.domain.EmailExistException;
import com.example.spring_jwt_get_arrays.exception.domain.ExceptionHandling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = {"/","/users"})
public class UserRessource extends ExceptionHandling {
    @GetMapping("home")
    public String showUser() throws EmailExistException {
        throw  new EmailExistException("This email is already taken");
    }
}
