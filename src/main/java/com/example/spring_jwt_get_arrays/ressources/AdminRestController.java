package com.example.spring_jwt_get_arrays.ressources;

import com.example.spring_jwt_get_arrays.dto.AdministrateurDTO;
import com.example.spring_jwt_get_arrays.exception.domain.ExceptionHandling;
import com.example.spring_jwt_get_arrays.service.IAdministrateur;
import com.github.javafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class AdminRestController extends ExceptionHandling {
    private Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final IAdministrateur iAdministrateur;
    private final BCryptPasswordEncoder passwordEncoder;
    private final Faker faker;
    public AdminRestController(IAdministrateur iAdministrateur, BCryptPasswordEncoder passwordEncoder, Faker faker) {
        this.iAdministrateur = iAdministrateur;
        this.passwordEncoder = passwordEncoder;
        this.faker = faker;
    }

    @GetMapping(path="/administrateurs", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AdministrateurDTO> getAdmins(){
        return iAdministrateur.getAdmins();
    }

    @GetMapping(value = "/administrateurs/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public AdministrateurDTO getAdmin(@PathVariable Long id){
        return iAdministrateur.getAdmin(id);
    }

    @PostMapping(path = "/administrateurs")
    public AdministrateurDTO saveAdmin(@RequestBody AdministrateurDTO administrateurDTO){
        String password = faker.internet().password();
        LOGGER.info("Le mot de passe de l'utilisateur est"+password);
        administrateurDTO.setPassword(passwordEncoder.encode(password));
        return iAdministrateur.saveAdmin(administrateurDTO);
    }
}
