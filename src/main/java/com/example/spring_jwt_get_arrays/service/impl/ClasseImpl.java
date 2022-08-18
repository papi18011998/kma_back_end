package com.example.spring_jwt_get_arrays.service.impl;

import com.example.spring_jwt_get_arrays.domain.Classe;
import com.example.spring_jwt_get_arrays.dto.ClasseDTO;
import com.example.spring_jwt_get_arrays.exception.domain.ClasseAlreadyExistException;
import com.example.spring_jwt_get_arrays.mappers.KmaMapper;
import com.example.spring_jwt_get_arrays.repository.ClasseRepository;
import com.example.spring_jwt_get_arrays.service.IClasse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class ClasseImpl implements IClasse {
    private final KmaMapper mapper;
    private ClasseRepository classeRepository;
    private Logger LOGGER = LoggerFactory.getLogger(getClass());

    public ClasseImpl(KmaMapper mapper, ClasseRepository classeRepository) {
        this.mapper = mapper;
        this.classeRepository = classeRepository;
    }

    @Override
    public List<ClasseDTO> getClasses() {
        List<Classe> classeList = classeRepository.findAll();
        List<ClasseDTO> classes =classeList.stream().map(classe -> mapper.classe_to_classeDTO(classe)).collect(Collectors.toList());
        return classes;
    }

    @Override
    public ClasseDTO addClasse(ClasseDTO classeDTO) throws ClasseAlreadyExistException {
        classeDTO.setLibelle(StringUtils.deleteWhitespace(classeDTO.getLibelle().toUpperCase()));
        Classe classe = classeRepository.findByLibelle(classeDTO.getLibelle());
        if (classe != null){
            throw new ClasseAlreadyExistException("Cette classe existe déjà !!!");
        }
//        classeRepository.save(mapper.classeDTO_to_classe(classeDTO));
        return null;
    }
}
