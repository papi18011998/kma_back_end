package com.example.spring_jwt_get_arrays.mappers;

import com.example.spring_jwt_get_arrays.domain.*;
import com.example.spring_jwt_get_arrays.dto.*;
import com.example.spring_jwt_get_arrays.repository.EleveRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class KmaMapper {

    public Administrateur adminDTO_to_admin(AdministrateurDTO administrateurDTO){
        Administrateur administrateur = new Administrateur();
        BeanUtils.copyProperties(administrateurDTO,administrateur);
        return administrateur;
    }

    public AdministrateurDTO admin_to_adminDTO(Administrateur administrateur){
        AdministrateurDTO administrateurDTO = new AdministrateurDTO();
        BeanUtils.copyProperties(administrateur,administrateurDTO);
        return administrateurDTO;
    }

    public Eleve eleveDTO_to_eleve(EleveDTO eleveDTO){
        Eleve eleve = new Eleve();
        BeanUtils.copyProperties(eleveDTO,eleve);
        eleve.setParent(parentDTO_to_parent(eleveDTO.getParent()));
        Collection<Evaluation> evaluations = new ArrayList<>();
        eleveDTO.getEvaluations().forEach(evaluationDTO -> {
            evaluations.add(evaluationDTO_to_evaluation(evaluationDTO));
        });
        eleve.setEvaluations(evaluations);
        return eleve;
    }

    public EleveDTO eleve_to_eleveDTO(Eleve eleve){
        EleveDTO eleveDTO = new EleveDTO();
        BeanUtils.copyProperties(eleve,eleveDTO);
        eleveDTO.setParent(parent_to_parentDTO(eleve.getParent()));
        Collection<EvaluationDTO> evaluationDTOS = new ArrayList<>();
        eleve.getEvaluations().forEach(evaluation -> {
            evaluationDTOS.add(evaluation_to_evaluationDTO(evaluation));
        });
        eleveDTO.setEvaluations(evaluationDTOS);
        return eleveDTO;
    }

    public Professeur professeurDTO_to_professeur(ProfesseurDTO professeurDTO){
        Professeur professeur = new Professeur();
        BeanUtils.copyProperties(professeurDTO,professeur);
        professeur.setMatiere(matiereDTO_to_matiere(professeurDTO.getMatiere()));
        return  professeur;
    }

    public  ProfesseurDTO professeur_to_professeurDTO(Professeur professeur){
        ProfesseurDTO professeurDTO = new ProfesseurDTO();
        BeanUtils.copyProperties(professeur,professeurDTO);
        professeurDTO.setMatiere(matiere_to_matiereDTO(professeur.getMatiere()));
        return professeurDTO;
    }

    public Parent parentDTO_to_parent(ParentDTO parentDTO){
        Parent parent = new Parent();
        BeanUtils.copyProperties(parentDTO,parent);
        return parent;
    }

    public ParentDTO parent_to_parentDTO(Parent parent){
        ParentDTO parentDTO = new ParentDTO();
        BeanUtils.copyProperties(parent,parentDTO);
        return parentDTO;
    }

    public UtilisateurDTO utilisateur_to_utilisateurDTO(User utilisateur){
        UtilisateurDTO utilisateurDTO = new UtilisateurDTO();
        BeanUtils.copyProperties(utilisateur,utilisateurDTO);
        return utilisateurDTO;
    }

    public User utilsateurDTO_to_utilisateur(UtilisateurDTO utilisateurDTO){
        User utilisateur = new User();
        BeanUtils.copyProperties(utilisateurDTO,utilisateur);
        return utilisateur;
    }

    public Matiere matiereDTO_to_matiere(MatiereDTO matiereDTO){
        Matiere matiere = new Matiere();
        BeanUtils.copyProperties(matiereDTO,matiere);
        return  matiere;
    }

    public MatiereDTO matiere_to_matiereDTO(Matiere matiere){
        MatiereDTO matiereDTO = new MatiereDTO();
        BeanUtils.copyProperties(matiere,matiereDTO);
        return matiereDTO;
    }

    public Classe classeDTO_to_classe(ClasseDTO classeDTO){
        Classe classe = new Classe();
        BeanUtils.copyProperties(classeDTO,classe);
        return  classe;
    }

    public ClasseDTO classe_to_classeDTO(Classe classe){
        ClasseDTO classeDTO = new ClasseDTO();
        BeanUtils.copyProperties(classe,classeDTO);
        return  classeDTO;
    }

     public Evaluation evaluationDTO_to_evaluation(EvaluationDTO evaluationDTO){
        Evaluation evaluation = new Evaluation();
        BeanUtils.copyProperties(evaluationDTO,evaluation);
        evaluation.setMatiere(matiereDTO_to_matiere(evaluationDTO.getMatiere()));
//        evaluation.setEleve(eleveDTO_to_eleve(evaluationDTO.getEleve()));
        return evaluation;
     }

     public EvaluationDTO evaluation_to_evaluationDTO(Evaluation evaluation){
        EvaluationDTO evaluationDTO = new EvaluationDTO();
        BeanUtils.copyProperties(evaluation,evaluationDTO);
        evaluationDTO.setMatiere(matiere_to_matiereDTO(evaluation.getMatiere()));
//        evaluationDTO.setEleve(eleve_to_eleveDTO(evaluation.getEleve()));
        return evaluationDTO;
     }

     public GenreDTO genre_to_genreDTO(Genre genre){
        GenreDTO genreDTO = new GenreDTO();
        BeanUtils.copyProperties(genre,genreDTO);
        return genreDTO;
     }

     public Genre genreDTO_to_genre(GenreDTO genreDTO){
        Genre genre = new Genre();
        BeanUtils.copyProperties(genreDTO,genre);
        return genre;
     }
}


