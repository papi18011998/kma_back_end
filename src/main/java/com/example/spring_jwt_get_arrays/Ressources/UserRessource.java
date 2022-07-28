package com.example.spring_jwt_get_arrays.Ressources;

import com.example.spring_jwt_get_arrays.domain.User;
import com.example.spring_jwt_get_arrays.exception.domain.EmailExistException;
import com.example.spring_jwt_get_arrays.exception.domain.ExceptionHandling;
import com.example.spring_jwt_get_arrays.exception.domain.UserNotFoundException;
import com.example.spring_jwt_get_arrays.exception.domain.UsernameExistException;
import com.example.spring_jwt_get_arrays.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserRessource extends ExceptionHandling {
    private final UserService userService;

    public UserRessource(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("register")
    public ResponseEntity<User> register(@RequestBody User user) throws UserNotFoundException, EmailExistException, UsernameExistException {
        User newUser = userService.register(user.getFirstName(), user.getLastName(), user.getUserName(), user.getEmail());
        return  new ResponseEntity<>(newUser,HttpStatus.OK);

    }
}
