package com.example.spring_jwt_get_arrays.ressources;

import com.example.spring_jwt_get_arrays.domain.Eleve;
import com.example.spring_jwt_get_arrays.domain.Matiere;
import com.example.spring_jwt_get_arrays.domain.Professeur;
import com.example.spring_jwt_get_arrays.dto.EvaluationDTO;
import static com.example.spring_jwt_get_arrays.enumeration.Genre.*;
import com.example.spring_jwt_get_arrays.exception.domain.InvalidNoteException;
import com.example.spring_jwt_get_arrays.repository.EleveRepository;
import com.example.spring_jwt_get_arrays.repository.ProfesseurRepository;
import com.example.spring_jwt_get_arrays.ressources.formModels.EvaluationForm;
import com.example.spring_jwt_get_arrays.service.IEvaluation;
import com.example.spring_jwt_get_arrays.utility.SmsSender;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("*")
public class EvaluationController{
    private final IEvaluation  iEvaluation;
    private final SmsSender smsSender;
    private final ProfesseurRepository professeurRepository;
    private final EleveRepository eleveRepository;

    public EvaluationController(IEvaluation iEvaluation, SmsSender smsSender, ProfesseurRepository professeurRepository, EleveRepository eleveRepository) {
        this.iEvaluation = iEvaluation;
        this.smsSender = smsSender;
        this.professeurRepository = professeurRepository;
        this.eleveRepository = eleveRepository;
    }
    @PostMapping("/evaluations")
    public EvaluationDTO addEvaluation(@RequestBody EvaluationForm evaluationForm) throws InvalidNoteException {
        Professeur professeur = professeurRepository.findById(evaluationForm.getIdProfesseur()).orElse(null);
        Eleve eleve = eleveRepository.findById(evaluationForm.getIdEleve()).orElse(null);
        Matiere matiereOfProfesseur = professeur.getMatiere();
        String civiliteParent;
        String civiliteProfesseur;
        if (eleve.getParent().getGenre().getLibelle() == Masculin.toString()){
            civiliteParent = "Mr";
        }else {
            civiliteParent = "Mme";
        }

        if (professeur.getGenre().getLibelle() == Masculin.toString()){
            civiliteProfesseur = "Mr";
        }else {
            civiliteProfesseur = "Mme";
        }
//                smsSender.sendSms("Bonjour,\n" +
//                    civiliteParent+" "+eleve.getParent().getNom()+", le professeur "+ civiliteProfesseur+" "+professeur.getNom()+
//                    "professeur de "+professeur.getMatiere().getLibelle()+" a ajoute une note de "+ evaluationForm.getNote() +" à votre eleve "+eleve.getPrenom() +" "+ eleve.getNom()+"\n");
        return  iEvaluation.addEvaluation(evaluationForm.getNote(),matiereOfProfesseur,eleve);
    }
    @GetMapping("evaluations/mostfrequent")
    public long getMostFrequentNote(){
        return iEvaluation.getMostFrequentScore();
    }
    @GetMapping("evaluations/avg")
    public long getAverageByClasse(){
        return iEvaluation.getAverageByClasse();
    }
}
