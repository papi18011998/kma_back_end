package com.example.spring_jwt_get_arrays.service.impl;



import com.example.spring_jwt_get_arrays.domain.Administrateur;
import com.example.spring_jwt_get_arrays.dto.AdministrateurDTO;
import com.example.spring_jwt_get_arrays.mappers.KmaMapper;
import com.example.spring_jwt_get_arrays.repository.AdministrateurRepository;
import com.example.spring_jwt_get_arrays.service.IAdministrateur;
import com.example.spring_jwt_get_arrays.utility.SmsSender;
import com.github.javafaker.Faker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

import static com.example.spring_jwt_get_arrays.enumeration.Role.ROLE_ADMIN;

@Service
public class AdministrateurImpl implements IAdministrateur, UserDetailsService {
    private final KmaMapper mapper;
    private final AdministrateurRepository administrateurRepository;
    private final Faker faker;
    private final BCryptPasswordEncoder passwordEncoder;

    public AdministrateurImpl(KmaMapper mapper, AdministrateurRepository administrateurRepository, Faker faker, BCryptPasswordEncoder passwordEncoder) {
        this.mapper = mapper;
        this.administrateurRepository = administrateurRepository;
        this.faker = faker;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<AdministrateurDTO> getAdmins() {
        List<Administrateur> administrateurs =administrateurRepository.findAll();
        List<AdministrateurDTO> list = administrateurs.stream().map(administrateur -> mapper.admin_to_adminDTO(administrateur)).collect(Collectors.toList());
        return list;
    }

    @Override
    public AdministrateurDTO getAdmin(Long id) {
        Administrateur administrateur = administrateurRepository.findById(id).orElse(null);
        return mapper.admin_to_adminDTO(administrateur);
    }

    @Override
    public AdministrateurDTO saveAdmin(AdministrateurDTO administrateurDTO) {
        Administrateur administrateur = mapper.adminDTO_to_admin(administrateurDTO);
        String password = faker.internet().password();
        administrateur.setPassword(passwordEncoder.encode(password));
        administrateur.setRole(ROLE_ADMIN.name());
        administrateur.setAuthorities(ROLE_ADMIN.getAuthorities());
        administrateurRepository.save(administrateur);
//        SmsSender smsSender = new SmsSender();
//        smsSender.sendSms("Votre mot de passe est : "+ password+"\n");
        return mapper.admin_to_adminDTO(administrateur);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
