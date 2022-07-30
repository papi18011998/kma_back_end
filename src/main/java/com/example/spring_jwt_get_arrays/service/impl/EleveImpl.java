package com.example.spring_jwt_get_arrays.service.impl;

import com.example.spring_jwt_get_arrays.domain.Eleve;
import com.example.spring_jwt_get_arrays.dto.EleveDTO;
import com.example.spring_jwt_get_arrays.mappers.KmaMapper;
import com.example.spring_jwt_get_arrays.repository.EleveRepository;
import com.example.spring_jwt_get_arrays.service.IEleve;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EleveImpl implements IEleve, UserDetailsService {
    private final EleveRepository eleveRepository;
    private final KmaMapper mapper;

    public EleveImpl(EleveRepository eleveRepository, KmaMapper mapper) {
        this.eleveRepository = eleveRepository;
        this.mapper = mapper;
    }

    @Override
    public Eleve addEleve(Eleve eleve) {
        return eleveRepository.save(eleve);
    }

    @Override
    public List<EleveDTO> getEleves() {
        List<Eleve> eleves = eleveRepository.findAll();
        return eleves.stream().map(mapper::eleve_to_eleveDTO).collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
