package com.example.spring_jwt_get_arrays.service.impl;

import com.example.spring_jwt_get_arrays.domain.Matiere;
import com.example.spring_jwt_get_arrays.dto.MatiereDTO;
import com.example.spring_jwt_get_arrays.exception.domain.MatiereAlreadyExistException;
import com.example.spring_jwt_get_arrays.mappers.KmaMapper;
import com.example.spring_jwt_get_arrays.repository.MatiereRepository;
import com.example.spring_jwt_get_arrays.service.IMatiere;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatiereImpl implements IMatiere {
    private final MatiereRepository matiereRepository;
    private final KmaMapper mapper;

    public MatiereImpl(MatiereRepository matiereRepository, KmaMapper mapper) {
        this.matiereRepository = matiereRepository;
        this.mapper = mapper;
    }

    @Override
    public List<MatiereDTO> getMatieres() {
        List<Matiere> matieres = matiereRepository.findAll();
        return  matieres.stream().map(mapper::matiere_to_matiereDTO).collect(Collectors.toList());
    }

    @Override
    public MatiereDTO getMatiere() {
        return null;
    }

    @Override
    public MatiereDTO save(MatiereDTO matiereDTO) throws MatiereAlreadyExistException {
        Matiere foundMatiere = matiereRepository.findByLibelle(matiereDTO.getLibelle());
        if(foundMatiere != null){
            throw new MatiereAlreadyExistException("La matière "+matiereDTO.getLibelle()+" existe déjà !!!");
        }
        Matiere matiere = matiereRepository.save(mapper.matiereDTO_to_matiere(matiereDTO));
        return mapper.matiere_to_matiereDTO(matiere);
    }

    @Override
    public MatiereDTO updateMatiere(long id, MatiereDTO matiereDTO) {
        return null;
    }
}
