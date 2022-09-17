package com.example.spring_jwt_get_arrays.ressources;

import com.example.spring_jwt_get_arrays.dto.MatiereDTO;
import com.example.spring_jwt_get_arrays.exception.domain.ExceptionHandling;
import com.example.spring_jwt_get_arrays.exception.domain.MatiereAlreadyExistException;
import com.example.spring_jwt_get_arrays.service.IMatiere;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class MatiereController extends ExceptionHandling {
    private final IMatiere matiere;

    public MatiereController(IMatiere matiere) {
        this.matiere = matiere;
    }

    @GetMapping(path = "/matieres",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MatiereDTO> getMatieres(){
        return matiere.getMatieres();
    }

    @PostMapping("/matieres")
    public MatiereDTO addMatiere(@RequestBody MatiereDTO matiereDTO) throws MatiereAlreadyExistException {
        return matiere.save(matiereDTO);
    }
}
