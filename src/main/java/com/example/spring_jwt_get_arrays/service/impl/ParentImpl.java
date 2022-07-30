package com.example.spring_jwt_get_arrays.service.impl;


import com.example.spring_jwt_get_arrays.domain.Parent;
import com.example.spring_jwt_get_arrays.dto.ParentDTO;
import com.example.spring_jwt_get_arrays.mappers.KmaMapper;
import com.example.spring_jwt_get_arrays.repository.ParentRepository;
import com.example.spring_jwt_get_arrays.service.IParent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParentImpl implements IParent, UserDetailsService {
    private final ParentRepository parentRepository;
    private final KmaMapper mapper;

    public ParentImpl(ParentRepository parentRepository, KmaMapper mapper) {
        this.parentRepository = parentRepository;
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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
