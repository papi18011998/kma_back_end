package com.example.spring_jwt_get_arrays.service;

import com.example.spring_jwt_get_arrays.domain.Eleve;
import com.example.spring_jwt_get_arrays.domain.Matiere;
import com.example.spring_jwt_get_arrays.dto.EvaluationDTO;
import com.example.spring_jwt_get_arrays.exception.domain.InvalidNoteException;

import java.util.List;

public interface IEvaluation {
public EvaluationDTO addEvaluation(double note, Matiere matiere, Eleve eleve) throws InvalidNoteException;
public long getMostFrequentScore();
public long getAverageByClasse();

}
