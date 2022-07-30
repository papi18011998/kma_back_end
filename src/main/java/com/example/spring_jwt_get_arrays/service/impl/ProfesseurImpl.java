package com.example.spring_jwt_get_arrays.service.impl;


import com.example.spring_jwt_get_arrays.domain.Professeur;
import com.example.spring_jwt_get_arrays.dto.ProfesseurDTO;
import com.example.spring_jwt_get_arrays.mappers.KmaMapper;
import com.example.spring_jwt_get_arrays.repository.ProfesseurRepository;
import com.example.spring_jwt_get_arrays.service.IProfesseur;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfesseurImpl implements IProfesseur, UserDetailsService {
    private final ProfesseurRepository professeurRepository;
    private final KmaMapper mapper;

    public ProfesseurImpl(ProfesseurRepository professeurRepository, KmaMapper mapper) {
        this.professeurRepository = professeurRepository;
        this.mapper = mapper;
    }

    @Override
    public List<ProfesseurDTO> getProfesseurs() {
        List<Professeur> professeurs = professeurRepository.findAll();
        return professeurs.stream().map(mapper::professeur_to_professeurDTO).collect(Collectors.toList());
    }

    @Override
    public ProfesseurDTO addProfesseur(ProfesseurDTO professeurDTO) {
        Professeur professeur = mapper.professeurDTO_to_professeur(professeurDTO);
        professeurRepository.save(professeur);
        return  mapper.professeur_to_professeurDTO(professeur);
    }

    @Override
    public ProfesseurDTO updateProfesseur(long id, ProfesseurDTO professeurDTO) {
        Professeur professeur = professeurRepository.findById(id).orElse(null);
        if(professeur != null){
            professeur.setPrenom(professeurDTO.getPrenom());
            professeur.setNom(professeurDTO.getNom());
            professeur.setAdresse(professeurDTO.getAdresse());
            professeur.setGenre(professeurDTO.getGenre());
            professeur.setDate_prise_fonction(professeurDTO.getDate_prise_fonction());
            professeur.setMatiere(mapper.matiereDTO_to_matiere(professeurDTO.getMatiere()));
            professeurRepository.save(professeur);
        }
        return (professeur==null)?null:mapper.professeur_to_professeurDTO(professeur);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
