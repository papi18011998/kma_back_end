package com.example.spring_jwt_get_arrays.service;


import com.example.spring_jwt_get_arrays.domain.Evaluation;
import com.example.spring_jwt_get_arrays.domain.Parent;
import com.example.spring_jwt_get_arrays.dto.EvaluationDTO;
import com.example.spring_jwt_get_arrays.dto.ParentDTO;
import com.example.spring_jwt_get_arrays.exception.domain.UserNotFoundException;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface IParent {
    public Parent addParent(Parent parent);
    public List<ParentDTO> getParents();
    public ParentDTO findByCni(String cni);
    public long countElevesByParent(long parentId) throws UserNotFoundException;
    public String getMostFrequentScore(long parentId);
    public Collection<Evaluation> getEvaluationsEleves(long eleveId) throws UserNotFoundException;
}
