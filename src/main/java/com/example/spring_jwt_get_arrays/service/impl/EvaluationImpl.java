package com.example.spring_jwt_get_arrays.service.impl;

import com.example.spring_jwt_get_arrays.domain.Eleve;
import com.example.spring_jwt_get_arrays.domain.Evaluation;
import com.example.spring_jwt_get_arrays.domain.Matiere;
import com.example.spring_jwt_get_arrays.dto.EvaluationDTO;
import com.example.spring_jwt_get_arrays.exception.domain.InvalidNoteException;
import com.example.spring_jwt_get_arrays.mappers.KmaMapper;
import com.example.spring_jwt_get_arrays.repository.EvaluationRepository;
import com.example.spring_jwt_get_arrays.service.IEvaluation;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class EvaluationImpl implements IEvaluation {
    private final EvaluationRepository evaluationRepository;
    private final KmaMapper mapper;

    public EvaluationImpl(EvaluationRepository evaluationRepository, KmaMapper mapper) {
        this.evaluationRepository = evaluationRepository;
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
}
