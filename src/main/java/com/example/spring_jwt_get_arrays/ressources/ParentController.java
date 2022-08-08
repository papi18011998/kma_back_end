package com.example.spring_jwt_get_arrays.ressources;

import com.example.spring_jwt_get_arrays.domain.Classe;
import com.example.spring_jwt_get_arrays.domain.Eleve;
import com.example.spring_jwt_get_arrays.domain.EleveClasse;
import com.example.spring_jwt_get_arrays.domain.Parent;
import com.example.spring_jwt_get_arrays.dto.ParentDTO;
import com.example.spring_jwt_get_arrays.exception.domain.ExceptionHandling;
import com.example.spring_jwt_get_arrays.repository.*;
import com.example.spring_jwt_get_arrays.ressources.formModels.ParentForm;
import com.example.spring_jwt_get_arrays.service.IParent;
import com.github.javafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.example.spring_jwt_get_arrays.enumeration.Role.ROLE_ELEVE;
import static com.example.spring_jwt_get_arrays.enumeration.Role.ROLE_PARENT;

@RestController
@CrossOrigin("*")
public class ParentController extends ExceptionHandling {
    private final IParent iParent;
    private final GenreRepository genreRepository;
    private final ParentRepository parentRepository;
    private final ClasseRepository classeRepository;
    private  final EleveClasseRepository eleveClasseRepository;
    private final EleveRepository eleveRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final Faker faker;
    public ParentController(IParent iParent, GenreRepository genreRepository, ParentRepository parentRepository, ClasseRepository classeRepository, EleveClasseRepository eleveClasseRepository, EleveRepository eleveRepository, BCryptPasswordEncoder passwordEncoder, Faker faker) {
        this.iParent = iParent;
        this.genreRepository = genreRepository;
        this.parentRepository = parentRepository;
        this.classeRepository = classeRepository;
        this.eleveClasseRepository = eleveClasseRepository;
        this.eleveRepository = eleveRepository;
        this.passwordEncoder = passwordEncoder;
        this.faker = faker;
    }

    @GetMapping(path = "parents", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ParentDTO> getParents(){
        return iParent.getParents();
    }
    @GetMapping(path = "parents/cni/{cni}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ParentDTO findByCni(@PathVariable String cni){
        return iParent.findByCni(cni);
    }
    @PostMapping(path = "parents")
    public Parent addParent(@RequestBody ParentForm parentForm){
        String passwordParent = faker.internet().password();
        String passworEleve = faker.internet().password();
        LOGGER.info("Le mot de passe de l'eleve est "+ passworEleve);
        // set parent values
        Parent parent = new Parent();
        parent.setPrenom(parentForm.getPrenom());
        parent.setNom(parentForm.getNom());
        parent.setAdresse(parentForm.getAdresse());
        parent.setTelephone(parentForm.getTelephone());
        parent.setCni(parentForm.getCni());
        parent.setActive(true);
        parent.setNotLocked(true);
        parent.setUserName(parentForm.getLogin());
        parent.setRole(ROLE_PARENT.name());
        parent.setAuthorities(ROLE_PARENT.getAuthorities());
        parent.setJoinDate(new Date());
        parent.setPassword(passwordEncoder.encode(passwordParent));
        parent.setGenre(genreRepository.findById(parentForm.getGenre_id()).orElse(null));
        long id = iParent.addParent(parent).getId();
        //set eleve values
        Eleve eleve = new Eleve();
        eleve.setPrenom(parentForm.getPrenomEleve());
        eleve.setNom(parentForm.getNomEleve());
        eleve.setAdresse(parentForm.getAdresseEleve());
        eleve.setGenre(genreRepository.findById(parentForm.getGenre_idEleve()).orElse(null));
        eleve.setActive(true);
        eleve.setNotLocked(true);
        eleve.setJoinDate(new Date());
        eleve.setRole(ROLE_ELEVE.name());
        eleve.setAuthorities(ROLE_ELEVE.getAuthorities());
        eleve.setPassword(passwordEncoder.encode(passworEleve));
        Classe classe = classeRepository.findById(parentForm.getAnnee()).orElse(null);
        String matricule = classe.getLibelle().substring(0, 2).toUpperCase() +
                faker.number().digits(2) +
                parent.getPrenom().substring(0, 1).toUpperCase() +
                parent.getNom().substring(0, 1).toUpperCase();
        eleve.setUserName(matricule);
        eleve.setMatricule(matricule);
        eleve.setParent(parentRepository.findById(id).orElse(null));
        int year = Calendar.getInstance().get(Calendar.YEAR);
        EleveClasse eleveClasse = new EleveClasse(null,year-1+"-"+year,eleve,classe);
        // flush data
        eleveRepository.save(eleve);
        eleveClasseRepository.save(eleveClasse);
        return parentRepository.findById(id).orElse(null);
    }

}
