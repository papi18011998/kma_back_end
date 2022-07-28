package com.example.spring_jwt_get_arrays.Ressources;

import com.example.spring_jwt_get_arrays.constant.SecurityConstant;
import com.example.spring_jwt_get_arrays.domain.User;
import com.example.spring_jwt_get_arrays.domain.UserPrincipal;
import com.example.spring_jwt_get_arrays.exception.domain.EmailExistException;
import com.example.spring_jwt_get_arrays.exception.domain.ExceptionHandling;
import com.example.spring_jwt_get_arrays.exception.domain.UserNotFoundException;
import com.example.spring_jwt_get_arrays.exception.domain.UsernameExistException;
import com.example.spring_jwt_get_arrays.service.UserService;
import com.example.spring_jwt_get_arrays.utility.JWTTokenProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import static com.example.spring_jwt_get_arrays.constant.SecurityConstant.JWT_TOKEN_HEADER;

@RestController
@RequestMapping("users")
public class UserRessource extends ExceptionHandling {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JWTTokenProvider jwtTokenProvider;

    public UserRessource(UserService userService, AuthenticationManager authenticationManager, JWTTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }
    @PostMapping("login")
    public ResponseEntity<User> login(@RequestBody User user) {
        authenticate(user.getUserName(),user.getPassword());
        User loginUser = userService.findUserByUsername(user.getUserName());
        UserPrincipal userPrincipal = new UserPrincipal(loginUser);
        HttpHeaders jwtHeaders = getJwtHeaders(userPrincipal);
        return  new ResponseEntity<>(loginUser,jwtHeaders,HttpStatus.OK);

    }

    private HttpHeaders getJwtHeaders(UserPrincipal userPrincipal) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(JWT_TOKEN_HEADER,jwtTokenProvider.generateToken(userPrincipal));
        return headers;
    }

    private void authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
    }

    @PostMapping("register")
    public ResponseEntity<User> register(@RequestBody User user) throws UserNotFoundException, EmailExistException, UsernameExistException {
        User newUser = userService.register(user.getFirstName(), user.getLastName(), user.getUserName(), user.getEmail());
        return  new ResponseEntity<>(newUser,HttpStatus.OK);

    }
}
