package com.example.spring_jwt_get_arrays.service;


import com.example.spring_jwt_get_arrays.dto.ClasseDTO;

import java.util.List;
public interface IClasse {
    public List<ClasseDTO> getClasses();
    public ClasseDTO addClasse(ClasseDTO classeDTO);
}
