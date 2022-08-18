package com.example.spring_jwt_get_arrays.ressources;


import com.example.spring_jwt_get_arrays.domain.Classe;
import com.example.spring_jwt_get_arrays.dto.ClasseDTO;
import com.example.spring_jwt_get_arrays.dto.EleveDTO;
import com.example.spring_jwt_get_arrays.exception.domain.ClasseAlreadyExistException;
import com.example.spring_jwt_get_arrays.exception.domain.ExceptionHandling;
import com.example.spring_jwt_get_arrays.repository.ClasseRepository;
import com.example.spring_jwt_get_arrays.repository.EleveClasseRepository;
import com.example.spring_jwt_get_arrays.service.IClasse;
import com.example.spring_jwt_get_arrays.service.impl.EleveClasseImpl;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class ClasseController extends ExceptionHandling {
    private final IClasse classe;
    private final ClasseRepository classeRepository;
    private final EleveClasseRepository eleveClasseRepository;
    private final EleveClasseImpl eleveClasse;

    public ClasseController(IClasse classe, ClasseRepository classeRepository, EleveClasseRepository eleveClasseRepository, EleveClasseImpl eleveClasse) {
        this.classe = classe;
        this.classeRepository = classeRepository;
        this.eleveClasseRepository = eleveClasseRepository;
        this.eleveClasse = eleveClasse;
    }

    @GetMapping(path = "/classes",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ClasseDTO> getClasses(){
        return classe.getClasses();
    }
    @PostMapping(path ="/classes")
    public ClasseDTO addClasse(@RequestBody ClasseDTO classeDTO) throws ClasseAlreadyExistException {
        return classe.addClasse(classeDTO);
    }
    @GetMapping("classes/{id}/{annee_scolaire}")
    public List<EleveDTO> findByClasseAndAnnee_scolaire(@PathVariable long id, @PathVariable String annee_scolaire){
        Classe classe = classeRepository.findById(id).orElse(null);
        return eleveClasse.findByClasseAndAnnee_scolaire(classe,annee_scolaire);
    }
    @GetMapping("classes/counteleves")
    public List<String> getElevesByClasse(){
        return eleveClasseRepository.getElevesByClasse();
    }
}
