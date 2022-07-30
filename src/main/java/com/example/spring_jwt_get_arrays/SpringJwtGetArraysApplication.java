package com.example.spring_jwt_get_arrays;

import com.example.spring_jwt_get_arrays.domain.Genre;
import com.example.spring_jwt_get_arrays.repository.GenreRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.stream.Stream;

@SpringBootApplication
public class SpringJwtGetArraysApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringJwtGetArraysApplication.class, args);
    }
//    @Bean
    public CommandLineRunner runner(GenreRepository genreRepository){
        return args -> {
            Stream.of("Masculin","Féminin").forEach(libelle->{
                Genre genre = new Genre(null,libelle);
                genreRepository.save(genre);
            });
        };
    }


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return  new BCryptPasswordEncoder();
    }
}
