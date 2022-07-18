package com.example.spring_jwt_get_arrays.Ressources;

import com.example.spring_jwt_get_arrays.domain.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserRessource {
    @GetMapping("home")
    public String showUser(){
        return "Application works";
    }
}
