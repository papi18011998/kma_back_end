package com.example.spring_jwt_get_arrays.service.impl;

import com.example.spring_jwt_get_arrays.domain.Genre;
import com.example.spring_jwt_get_arrays.dto.GenreDTO;
import com.example.spring_jwt_get_arrays.mappers.KmaMapper;
import com.example.spring_jwt_get_arrays.repository.GenreRepository;
import com.example.spring_jwt_get_arrays.service.IGenre;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenreImpl implements IGenre {
    private final KmaMapper mapper;
    private final GenreRepository genreRepository;

    public GenreImpl(KmaMapper mapper, GenreRepository genreRepository) {
        this.mapper = mapper;
        this.genreRepository = genreRepository;
    }

    @Override
    public List<GenreDTO> getGenres() {
        List <Genre> genres = genreRepository.findAll();
        return genres.stream().map(genre -> mapper.genre_to_genreDTO(genre)).collect(Collectors.toList());
    }
}
