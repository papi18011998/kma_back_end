package com.example.spring_jwt_get_arrays.service;


import com.example.spring_jwt_get_arrays.dto.MatiereDTO;

import java.util.List;

public interface IMatiere {
    public List<MatiereDTO> getMatieres();
    public MatiereDTO getMatiere();
    public MatiereDTO save(MatiereDTO matiereDTO);
    public MatiereDTO updateMatiere(long id, MatiereDTO matiereDTO);
}
