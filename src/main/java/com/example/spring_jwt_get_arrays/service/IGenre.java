package com.example.spring_jwt_get_arrays.service;


import com.example.spring_jwt_get_arrays.dto.GenreDTO;

import java.util.List;

public interface IGenre {
    public List<GenreDTO> getGenres();
}
