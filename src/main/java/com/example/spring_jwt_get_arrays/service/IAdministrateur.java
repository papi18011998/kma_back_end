package com.example.spring_jwt_get_arrays.service;


import com.example.spring_jwt_get_arrays.dto.AdministrateurDTO;

import java.util.List;

public interface IAdministrateur {
    public List<AdministrateurDTO> getAdmins();
    public AdministrateurDTO getAdmin(Long id);
    public AdministrateurDTO saveAdmin(AdministrateurDTO administrateurDTO);
}
