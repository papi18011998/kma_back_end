package com.example.spring_jwt_get_arrays.ressources.formModels;

import com.example.spring_jwt_get_arrays.dto.ProfesseurDTO;

import java.util.List;

public class ProfesseurForm {
    private ProfesseurDTO professeurDTO;
    private List<Long> classes;

    public ProfesseurForm(ProfesseurDTO professeurDTO, List<Long> classes) {
        this.professeurDTO = professeurDTO;
        this.classes = classes;
    }

    public ProfesseurForm() {
    }

    public ProfesseurDTO getProfesseurDTO() {
        return professeurDTO;
    }

    public void setProfesseurDTO(ProfesseurDTO professeurDTO) {
        this.professeurDTO = professeurDTO;
    }

    public List<Long> getClasses() {
        return classes;
    }

    public void setClasses(List<Long> classes) {
        this.classes = classes;
    }
}
