package com.example.spring_jwt_get_arrays.service.impl;

import com.example.spring_jwt_get_arrays.domain.*;
import com.example.spring_jwt_get_arrays.dto.EvaluationDTO;
import com.example.spring_jwt_get_arrays.exception.domain.CannotUpdateEvaluationException;
import com.example.spring_jwt_get_arrays.exception.domain.InvalidNoteException;
import com.example.spring_jwt_get_arrays.mappers.KmaMapper;
import com.example.spring_jwt_get_arrays.repository.EvaluationRepository;
import com.example.spring_jwt_get_arrays.repository.ProfesseurRepository;
import com.example.spring_jwt_get_arrays.service.IEvaluation;
import com.example.spring_jwt_get_arrays.utility.SmsSender;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

import static com.example.spring_jwt_get_arrays.enumeration.Genre.Masculin;

@Service
@Transactional
public class EvaluationImpl implements IEvaluation {
    private final EvaluationRepository evaluationRepository;
    private  final ProfesseurRepository professeurRepository;
    private final SmsSender smsSender;
    private final KmaMapper mapper;

    public EvaluationImpl(EvaluationRepository evaluationRepository, ProfesseurRepository professeurRepository, SmsSender smsSender, KmaMapper mapper) {
        this.evaluationRepository = evaluationRepository;
        this.professeurRepository = professeurRepository;
        this.smsSender = smsSender;
        this.mapper = mapper;
    }

    @Override
    public EvaluationDTO addEvaluation(double note, Matiere matiere, Eleve eleve) throws InvalidNoteException {
        if(note<0 || note>20){
            throw new InvalidNoteException("La note entrée doit être entre 0 et 20 !!!\n La note entée est : "+note);
        }
        Evaluation evaluation = new Evaluation(null,note,new Date(),matiere,eleve);
        evaluationRepository.save(evaluation);
        return mapper.evaluation_to_evaluationDTO(evaluation);
    }

    @Override
    public long getMostFrequentScore() {
        return evaluationRepository.getMostFrequentScore();
    }

    @Override
    public long getAverageByClasse() {
        return evaluationRepository.getAverageByClasse();
    }

    @Override
    public EvaluationDTO updateEvaluation(long idEvaluation,long idProfesseur, double note) throws CannotUpdateEvaluationException, InvalidNoteException {
        // Test de note valide
        if(note<0 || note>20){
            throw new InvalidNoteException("La note entrée doit être entre 0 et 20 !!!\n La note entée est : "+note);
        }
        // recherche du professeur et de sa matiere enseignee
        Professeur professeur = professeurRepository.findById(idProfesseur).orElse(null);
        assert professeur != null;
        // recherche de l'evaluation
        Evaluation evaluationToUpdate = evaluationRepository.findById(idEvaluation).orElse(null);
        assert evaluationToUpdate != null;

        Matiere matiereProfesseur = professeur.getMatiere();

        // Test si la note que le prof veut modifier est de sa matiere
        if(evaluationToUpdate.getMatiere().equals(matiereProfesseur)){
            evaluationToUpdate.setNote(note);
            double oldEvaluation = evaluationToUpdate.getNote();
            String civiliteParent;
            String civiliteProfesseur;
            Eleve eleve = evaluationToUpdate.getEleve();
            Parent parent = evaluationToUpdate.getEleve().getParent();
            if (evaluationToUpdate.getEleve().getParent().getGenre().getLibelle() == Masculin.toString()){
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
//                    civiliteParent+" "+parent.getNom()+", le professeur "+ civiliteProfesseur+" "+professeur.getNom()+
//                    " professeur de "+professeur.getMatiere().getLibelle()+" a modifié la note qui était de "+ oldEvaluation +" à "+ evaluationToUpdate.getNote()+" de votre eleve "+eleve.getPrenom() +" "+ eleve.getNom()+"\n");

            return mapper.evaluation_to_evaluationDTO(evaluationToUpdate);
        }else {
            throw new CannotUpdateEvaluationException("Vous n'avez pas le droit de modifier cette note.Seul le prof de "+ evaluationToUpdate.getMatiere().getLibelle()+" peut faire cette opération" );
        }

    }
}
