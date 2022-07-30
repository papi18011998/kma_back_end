package com.example.spring_jwt_get_arrays.service;


import com.example.spring_jwt_get_arrays.domain.Parent;
import com.example.spring_jwt_get_arrays.dto.ParentDTO;

import java.util.List;

public interface IParent {
    public Parent addParent(Parent parent);
    public List<ParentDTO> getParents();
    public ParentDTO findByCni(String cni);
}
