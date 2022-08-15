package com.example.spring_jwt_get_arrays.ressources;

import com.example.spring_jwt_get_arrays.domain.*;
import com.example.spring_jwt_get_arrays.dto.EleveDTO;
import com.example.spring_jwt_get_arrays.exception.domain.ExceptionHandling;
import com.example.spring_jwt_get_arrays.repository.*;
import com.example.spring_jwt_get_arrays.ressources.formModels.EleveForm;
import com.example.spring_jwt_get_arrays.service.IEleve;
import com.github.javafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.example.spring_jwt_get_arrays.enumeration.Role.ROLE_ELEVE;
import static com.example.spring_jwt_get_arrays.enumeration.Role.ROLE_PARENT;

@RestController
@Transactional
@CrossOrigin("*")
public class EleveController extends ExceptionHandling {
    private Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final IEleve ieleve;
    private final ClasseRepository classeRepository;
    private final EleveRepository eleveRepository;
    private final EleveClasseRepository eleveClasseRepository;
    private final ParentRepository parentRepository;
    private final GenreRepository genreRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final Faker faker;

    public EleveController(IEleve ieleve, ClasseRepository classeRepository, EleveRepository eleveRepository, EleveClasseRepository eleveClasseRepository, ParentRepository parentRepository, GenreRepository genreRepository, BCryptPasswordEncoder passwordEncoder, Faker faker) {
        this.ieleve = ieleve;
        this.classeRepository = classeRepository;
        this.eleveRepository = eleveRepository;
        this.eleveClasseRepository = eleveClasseRepository;
        this.parentRepository = parentRepository;
        this.genreRepository = genreRepository;
        this.passwordEncoder = passwordEncoder;
        this.faker = faker;
    }
    @GetMapping(path="eleves", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EleveDTO> getEleves(){
        return  ieleve.getEleves();
    }
    @PostMapping("eleves")
    public Eleve addEleve(@RequestBody EleveForm eleveForm) {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        Eleve eleve = new Eleve();
        Genre genre = genreRepository.findById(eleveForm.getGenreId()).orElse(null);
        String passwordEleve = faker.internet().password();
        LOGGER.info("Le mot de passe de l'eleve est : "+passwordEleve);
        eleve.setPrenom(eleveForm.getPrenom());
        eleve.setNom(eleveForm.getNom());
        eleve.setPassword(passwordEncoder.encode(passwordEleve));
        eleve.setAdresse(eleveForm.getAdresse());
        eleve.setGenre(genre);
        eleve.setNotLocked(true);
        eleve.setActive(true);
        eleve.setRole(ROLE_ELEVE.name());
        eleve.setAuthorities(ROLE_ELEVE.getAuthorities());
        eleve.setJoinDate(new Date());
        eleve.setActive(true);
        eleve.setNotLocked(true);
        eleve.setDate_naissance(eleveForm.getDate_naissance());
        Classe classe = classeRepository.findById(eleveForm.getAnnee()).orElse(null);
        Parent searchParent = parentRepository.findByCni(eleveForm.getCni()).orElse(null);
        if(searchParent == null){
            // Parent and student don't exist
            Genre genreParent = genreRepository.findById(eleveForm.getGenreIdParent()).orElse(null);
            Parent parent = new Parent();
            String passwordParent =  faker.internet().password();
            LOGGER.info("Le mot de passe du parent est: "+ passwordParent);
            parent.setPrenom(eleveForm.getPrenomParent());
            parent.setNom(eleveForm.getNomParent());
            parent.setAdresse(eleveForm.getAdresseParent());
            parent.setTelephone(eleveForm.getTelephone());
            parent.setCni(eleveForm.getCni());
            parent.setUserName(eleveForm.getLogin());
            parent.setPassword(passwordEncoder.encode(passwordParent));
            parent.setGenre(genreParent);
            parent.setRole(ROLE_PARENT.name());
            parent.setAuthorities(ROLE_PARENT.getAuthorities());
            parent.setJoinDate(new Date());
            parent.setActive(true);
            parent.setNotLocked(true);
            long idParent = parentRepository.save(parent).getId();
            assert classe != null;
            String matricule = classe.getLibelle().substring(0, 2).toUpperCase() +
                    faker.number().digits(2) +
                    parent.getPrenom().substring(0, 1).toUpperCase() +
                    parent.getNom().substring(0, 1).toUpperCase();
            eleve.setParent(parentRepository.findById(idParent).orElse(null));
            eleve.setUserName(matricule);
            eleve.setMatricule(matricule);
            long id = ieleve.addEleve(eleve).getId();

            EleveClasse eleveClasse = new EleveClasse(null,year-1+"-"+year,eleveRepository.findById(id).orElse(null),classe);
            eleveClasseRepository.save(eleveClasse);

            return eleveRepository.findById(eleve.getId()).orElse(null);
        }else {
            // Parent exist
            eleve.setParent(searchParent);

            String matricule = classe.getLibelle().substring(0, 2).toUpperCase() +
                    faker.number().digits(2) +
                    searchParent.getPrenom().substring(0, 1).toUpperCase() +
                    searchParent.getNom().substring(0, 1).toUpperCase();
            eleve.setUserName(matricule);
            eleve.setMatricule(matricule);
            long id = ieleve.addEleve(eleve).getId();

            EleveClasse eleveClasse = new EleveClasse(null,year-1+"-"+year,eleveRepository.findById(id).orElse(null),classe);
            eleveClasseRepository.save(eleveClasse);

            return eleveRepository.findById(eleve.getId()).orElse(null);
        }
    }
    @GetMapping("eleves/count")
    public long getCountEleves(){
        return ieleve.countAll();
    }
    @GetMapping("eleves/top")
    public List<String>getTopFive(){
        return ieleve.getTopfiveScore();
    }
}
