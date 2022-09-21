package com.example.spring_jwt_get_arrays.ressources;

import com.example.spring_jwt_get_arrays.dto.UtilisateurDTO;
import com.example.spring_jwt_get_arrays.mappers.KmaMapper;
import com.example.spring_jwt_get_arrays.ressources.formModels.UserForm;
import com.example.spring_jwt_get_arrays.domain.User;
import com.example.spring_jwt_get_arrays.domain.UserPrincipal;
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

import javax.mail.MessagingException;

import java.util.List;

import static com.example.spring_jwt_get_arrays.constant.SecurityConstant.JWT_TOKEN_HEADER;

@RestController
@RequestMapping("users")
@CrossOrigin("*")
public class UserRessource extends ExceptionHandling {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    public final KmaMapper mapper;
    private final JWTTokenProvider jwtTokenProvider;

    public UserRessource(UserService userService, AuthenticationManager authenticationManager, KmaMapper mapper, JWTTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.mapper = mapper;
        this.jwtTokenProvider = jwtTokenProvider;
    }
    @PostMapping("login")
    public ResponseEntity<UtilisateurDTO> login(@RequestBody User user) {
        authenticate(user.getUserName(),user.getPassword());
        User loginUser = userService.findUserByUsername(user.getUserName());
        UtilisateurDTO loginUtilisateurDTO = mapper.utilisateur_to_utilisateurDTO(loginUser);
        UserPrincipal userPrincipal = new UserPrincipal(loginUser);
        HttpHeaders jwtHeaders = getJwtHeaders(userPrincipal);
        return  new ResponseEntity<>(loginUtilisateurDTO,jwtHeaders,HttpStatus.OK);

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
    public ResponseEntity<User> register(@RequestBody UserForm userForm) throws UserNotFoundException, UsernameExistException, MessagingException {
        User newUser = userService.register(userForm.getPrenom(),userForm.getNom(),userForm.getUserName(),userForm.getAdresse(),userForm.getTelephone(),userForm.getGenreId());
        return  new ResponseEntity<>(newUser,HttpStatus.OK);

    }
    @GetMapping()
    public List<User> getUsers(){
        return userService.getUsers();
    }
    @GetMapping(path = "/username/{username}")
    public UtilisateurDTO findByLogin(@PathVariable(name = "username") String username){
        User foundUser = userService.findUserByUsername(username);
        return (foundUser != null)?mapper.utilisateur_to_utilisateurDTO(foundUser):null;
    }
    @GetMapping(path = "/telephone/{telephone}")
    public  UtilisateurDTO findByTelephone(@PathVariable(name = "telephone") String telephone){
        UtilisateurDTO foundUser = userService.findByTelephone(telephone);
        return foundUser;
    }

    @GetMapping("{id}")
    public UtilisateurDTO getUser(@PathVariable Long id) throws UserNotFoundException {
        return  userService.getUser(id);
    }

    @PutMapping("/status/{id}")
    public UtilisateurDTO change_status(@PathVariable Long id) throws UserNotFoundException {
        return userService.change_status(id);
    }
    @PutMapping("/{id}")
    public UtilisateurDTO update_profile(@PathVariable Long id, @RequestBody UtilisateurDTO utilisateurDTO){
        return userService.update_profile(id, utilisateurDTO);
    }
}
