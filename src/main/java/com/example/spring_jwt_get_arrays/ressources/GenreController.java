package com.example.spring_jwt_get_arrays.ressources;


import com.example.spring_jwt_get_arrays.dto.GenreDTO;
import com.example.spring_jwt_get_arrays.service.IGenre;
import org.springframework.hateoas.MediaTypes;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
public class GenreController {
    private final IGenre genre;

    public GenreController(IGenre genre) {
        this.genre = genre;
    }

    @GetMapping(path="/genres", produces = MediaTypes.ALPS_JSON_VALUE)
    public List<GenreDTO> getGenre(){
        return genre.getGenres();
    }
}
