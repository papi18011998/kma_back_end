package com.example.spring_jwt_get_arrays.ressources;

import com.example.spring_jwt_get_arrays.domain.Eleve;
import com.example.spring_jwt_get_arrays.domain.Matiere;
import com.example.spring_jwt_get_arrays.domain.Professeur;
import com.example.spring_jwt_get_arrays.dto.EvaluationDTO;
import com.example.spring_jwt_get_arrays.exception.domain.ExceptionHandling;
import com.example.spring_jwt_get_arrays.exception.domain.InvalidNoteException;
import com.example.spring_jwt_get_arrays.repository.EleveRepository;
import com.example.spring_jwt_get_arrays.repository.ProfesseurRepository;
import com.example.spring_jwt_get_arrays.ressources.formModels.EvaluationForm;
import com.example.spring_jwt_get_arrays.service.IEvaluation;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin("*")
public class EvaluationController{
    private final IEvaluation  iEvaluation;
    private final ProfesseurRepository professeurRepository;
    private final EleveRepository eleveRepository;

    public EvaluationController(IEvaluation iEvaluation, ProfesseurRepository professeurRepository, EleveRepository eleveRepository) {
        this.iEvaluation = iEvaluation;
        this.professeurRepository = professeurRepository;
        this.eleveRepository = eleveRepository;
    }
    @PostMapping("/evaluations")
    public EvaluationDTO addEvaluation(@RequestBody EvaluationForm evaluationForm) throws InvalidNoteException {
        Professeur professeur = professeurRepository.findById(evaluationForm.getIdProfesseur()).orElse(null);
        Eleve eleve = eleveRepository.findById(evaluationForm.getIdEleve()).orElse(null);
        Matiere matiereOfProfesseur = professeur.getMatiere();
        return  iEvaluation.addEvaluation(evaluationForm.getNote(),matiereOfProfesseur,eleve);
    }
    @GetMapping("evaluations/mostfrequent")
    public long getMostFrequentNote(){
        return iEvaluation.getMostFrequentScore();
    }
}
