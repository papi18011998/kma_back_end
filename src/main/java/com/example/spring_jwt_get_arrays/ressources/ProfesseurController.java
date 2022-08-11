package com.example.spring_jwt_get_arrays.ressources;


import com.example.spring_jwt_get_arrays.domain.Classe;
import com.example.spring_jwt_get_arrays.domain.ClasseProfesseur;
import com.example.spring_jwt_get_arrays.domain.Professeur;
import com.example.spring_jwt_get_arrays.dto.ClasseDTO;
import com.example.spring_jwt_get_arrays.dto.ProfesseurDTO;
import com.example.spring_jwt_get_arrays.exception.domain.ExceptionHandling;
import com.example.spring_jwt_get_arrays.exception.domain.UserNotFoundException;
import com.example.spring_jwt_get_arrays.mappers.KmaMapper;
import com.example.spring_jwt_get_arrays.repository.ClasseProfesseurRepository;
import com.example.spring_jwt_get_arrays.repository.ClasseRepository;
import com.example.spring_jwt_get_arrays.repository.ProfesseurRepository;
import com.example.spring_jwt_get_arrays.ressources.formModels.ProfesseurForm;
import com.example.spring_jwt_get_arrays.service.IProfesseur;
import com.github.javafaker.Faker;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@RestController
@Transactional
@CrossOrigin("*")
public class ProfesseurController extends ExceptionHandling {
    private final IProfesseur iProfesseur;
    private final ClasseRepository classeRepository;
    private final ProfesseurRepository professeurRepository;
    private final KmaMapper mapper;
    private final Faker faker;
    private final ClasseProfesseurRepository classeProfesseurRepository;

    public ProfesseurController(IProfesseur iProfesseur, ClasseRepository classeRepository, ProfesseurRepository professeurRepository, KmaMapper mapper, Faker faker, ClasseProfesseurRepository classeProfesseurRepository) {
        this.iProfesseur = iProfesseur;
        this.classeRepository = classeRepository;
        this.professeurRepository = professeurRepository;
        this.mapper = mapper;
        this.faker = faker;
        this.classeProfesseurRepository = classeProfesseurRepository;
    }
    @GetMapping(path = "/professeurs",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProfesseurDTO> getProfesseur(){
        return iProfesseur.getProfesseurs();
    }
    @PostMapping(path="professeurs")
    public  ProfesseurDTO addProfesseur(@RequestBody ProfesseurForm professeurForm){
        professeurForm.getProfesseurDTO().setPassword(faker.internet().password());
        long idNewProfesseur = iProfesseur.addProfesseur(professeurForm.getProfesseurDTO()).getId();
        int year = Calendar.getInstance().get(Calendar.YEAR);
        professeurForm.getClasses().forEach(classeId->{
            Classe classe = classeRepository.findById(classeId).orElse(null);
            ClasseProfesseur annee = new ClasseProfesseur(null,year-1+"-"+year,classe,professeurRepository.findById(idNewProfesseur).orElse(null));
            classeProfesseurRepository.save(annee);
        });
        return professeurForm.getProfesseurDTO();
    }
    @PutMapping("professeurs/{id}")
    public ProfesseurDTO update(@PathVariable long id, @RequestBody ProfesseurForm professeurForm){
        Professeur professeurToUpdate = professeurRepository.findById(id).orElse(null);
        //Suppression des anciennes classes
        List<ClasseProfesseur> classeProfesseurs = new ArrayList<>();
        professeurToUpdate.getAnnees().forEach(classeProfesseur -> {
            classeProfesseurs.add(classeProfesseur);
        });
        classeProfesseurRepository.deleteAll(classeProfesseurs);
        //Modification
        int year = Calendar.getInstance().get(Calendar.YEAR);
        professeurForm.getClasses().forEach(classeId->{
            Classe classe = classeRepository.findById(classeId).orElse(null);
            ClasseProfesseur classeProfesseur = new ClasseProfesseur(null,year-1+"-"+year,classe,professeurToUpdate);
            classeProfesseurRepository.save(classeProfesseur);
        });
        return iProfesseur.updateProfesseur(id,professeurForm.getProfesseurDTO());
    }
    @GetMapping("professeurs/{id}/classes/{annee_scolaire}")
    public List<ClasseDTO> getClassesOfProfesseurs(@PathVariable long id,
                                                   @PathVariable String annee_scolaire){
        Professeur professeur = professeurRepository.findById(id).orElse(null);
        return iProfesseur.findByProfesseurAndAnneeScolaire(professeur,annee_scolaire);
    }
    @GetMapping("professeurs/{id}")
    public ProfesseurDTO getProfesseur(@PathVariable long id) throws UserNotFoundException {
        return iProfesseur.getProfesseur(id);
    }
}