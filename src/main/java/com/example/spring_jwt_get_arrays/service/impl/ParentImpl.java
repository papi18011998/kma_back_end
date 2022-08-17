package com.example.spring_jwt_get_arrays.service.impl;


import com.example.spring_jwt_get_arrays.domain.Eleve;
import com.example.spring_jwt_get_arrays.domain.Evaluation;
import com.example.spring_jwt_get_arrays.domain.Parent;
import com.example.spring_jwt_get_arrays.dto.ParentDTO;
import com.example.spring_jwt_get_arrays.exception.domain.UserNotFoundException;
import com.example.spring_jwt_get_arrays.mappers.KmaMapper;
import com.example.spring_jwt_get_arrays.repository.EleveRepository;
import com.example.spring_jwt_get_arrays.repository.ParentRepository;
import com.example.spring_jwt_get_arrays.service.IParent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParentImpl implements IParent, UserDetailsService {
    private final ParentRepository parentRepository;
    private final EleveRepository eleveRepository;
    private final KmaMapper mapper;

    public ParentImpl(ParentRepository parentRepository, EleveRepository eleveRepository, KmaMapper mapper) {
        this.parentRepository = parentRepository;
        this.eleveRepository = eleveRepository;
        this.mapper = mapper;
    }

    @Override
    public Parent addParent(Parent parent) {
        return parentRepository.save(parent);
    }

    @Override
    public List<ParentDTO> getParents() {
        List<Parent> parents = parentRepository.findAll();
        return  parents.stream().map(mapper::parent_to_parentDTO).collect(Collectors.toList());
    }

    @Override
    public ParentDTO findByCni(String cni) {
        Parent parent = parentRepository.findByCni(cni).orElse(null);
        return (parent == null)?null:mapper.parent_to_parentDTO(parent);
    }

    @Override
    public long countElevesByParent(long parentId) throws UserNotFoundException {
        Parent parent = parentRepository.findById(parentId).orElse(null);
        if (parent == null){
            throw new UserNotFoundException("Parent introuvable");
        }
        return  parent.getEleves().size();
    }

    @Override
    public String getMostFrequentScore(long parentId) {
        return parentRepository.getMostFrequentScore(parentId);
    }

    @Override
    public Collection<Evaluation> getEvaluationsEleves(long eleveId) throws UserNotFoundException {
        Eleve eleve = eleveRepository.findById(eleveId).orElse(null);
        if(eleve == null){
            throw new UserNotFoundException("Elève introuvable !!!");
        }
        return eleve.getEvaluations();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
