package com.example.spring_jwt_get_arrays.service;


import com.example.spring_jwt_get_arrays.dto.MatiereDTO;
import com.example.spring_jwt_get_arrays.exception.domain.MatiereAlreadyExistException;

import java.util.List;

public interface IMatiere {
    public List<MatiereDTO> getMatieres();
    public MatiereDTO getMatiere();
    public MatiereDTO save(MatiereDTO matiereDTO) throws MatiereAlreadyExistException;
    public MatiereDTO updateMatiere(long id, MatiereDTO matiereDTO);
}
