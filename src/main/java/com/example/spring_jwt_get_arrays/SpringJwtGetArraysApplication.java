package com.example.spring_jwt_get_arrays;

import com.example.spring_jwt_get_arrays.domain.*;
import com.example.spring_jwt_get_arrays.repository.*;
import com.example.spring_jwt_get_arrays.utility.SmsSender;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;

import static com.example.spring_jwt_get_arrays.enumeration.Role.*;

@SpringBootApplication
public class SpringJwtGetArraysApplication implements  CommandLineRunner{
    private final RepositoryRestConfiguration restConfiguration;

    public SpringJwtGetArraysApplication(RepositoryRestConfiguration restConfiguration) {
        this.restConfiguration = restConfiguration;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringJwtGetArraysApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        restConfiguration.exposeIdsFor(Eleve.class);
    }
//    @Bean
//    public CommandLineRunner sendSms(){
//        return args -> {
//            SmsSender sms = new SmsSender();
//            sms.sendSms("Bonjour zero");
//        };
//    }
//    @Bean
    public CommandLineRunner runner(GenreRepository genreRepository,
                                    ClasseRepository classeRepository,
                                    MatiereRepository matiereRepository,
                                    AdministrateurRepository administrateurRepository,
                                    ProfesseurRepository professeurRepository,
                                    ClasseProfesseurRepository classeProfesseurRepository,
                                    ParentRepository parentRepository,
                                    EleveRepository eleveRepository,
                                    EleveClasseRepository eleveClasseRepository,
                                    EvaluationRepository evaluationRepository){
        return args -> {
            //Instanciation du faker
            Faker faker = new Faker(new Locale("fr_FR"));
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            //faker de genres
            Stream.of("Masculin","Féminin").forEach(libelle->{
                Genre genre = new Genre(null,libelle);
                genreRepository.save(genre);
            });

            //faker de classes
            Stream.of("CIA","CIB","CIC","CPA","CPB","CPC","CE1","CE2","CM1","CM2","6emeA","5eme","4eme","2eme S2B","1ere","TS2D").forEach(libelle->{
                Classe classe = new Classe();
                classe.setLibelle(libelle);
                classeRepository.save(classe);
            });

            // faker de matieres
            Stream.of(new String[]{"Mathematiques", "2"}, new String[]{"SVT", "3"},new String[]{"Anglais","7"},new String[]{"Histo-Geo","4"},new String[]{"Espagnol","4"},new String[]{"Français","2"},new String[]{"Allemand","2"}).forEach(tableauMatiere->{
                Matiere matiere = new Matiere();
                matiere.setLibelle(tableauMatiere[0]);
                matiere.setCoefficient(Integer.parseInt(tableauMatiere[1]));
                matiereRepository.save(matiere);
            });

            // faker pour les administrateurs
            for (int i = 0; i<=10;i++){
                Administrateur administrateur = new Administrateur();
                administrateur.setPrenom(faker.name().firstName());
                administrateur.setNom(faker.name().lastName());
                administrateur.setUserName(administrateur.getPrenom()+faker.number().digits(3));
                administrateur.setPassword(passwordEncoder.encode("Ad1234"));
                administrateur.setAdresse(faker.address().streetName());
                administrateur.setJoinDate(faker.date().between(df.parse("01-01-2000"),new Date()));
                administrateur.setRole(ROLE_ADMIN.name());
                administrateur.setAuthorities(ROLE_ADMIN.getAuthorities());
                administrateur.setActive(true);
                administrateur.setNotLocked(true);
                administrateur.setTelephone("77"+faker.number().digits(7));
                administrateur.setGenre((Math.random()>0.5)?genreRepository.findById(1L).orElse(null):genreRepository.findById(2L).orElse(null));
                administrateurRepository.save(administrateur);
            }
            // faker professeurs
            for(int i = 0; i<=20;i++){
                Professeur professeur = new Professeur();
                professeur.setPrenom(faker.name().firstName());
                professeur.setNom(faker.name().lastName());
                professeur.setUserName("prof"+professeur.getPrenom()+faker.number().digits(3));
                professeur.setPassword(passwordEncoder.encode("Prof1234"));
                professeur.setAdresse(faker.address().streetName());
                professeur.setJoinDate(faker.date().between(df.parse("01-01-2000"),new Date()));
                professeur.setRole(ROLE_PROFESSEUR.name());
                professeur.setAuthorities(ROLE_PROFESSEUR.getAuthorities());
                professeur.setActive(true);
                professeur.setNotLocked(true);
                professeur.setTelephone("77"+faker.number().digits(7));
                professeur.setGenre((Math.random()>0.5)?genreRepository.findById(1L).orElse(null):genreRepository.findById(2L).orElse(null));
                professeur.setDate_prise_fonction(faker.date().between(df.parse("01-01-2000"),df.parse("01-01-2021")));
                professeur.setMatiere(matiereRepository.findAll().get(faker.number().numberBetween(1,matiereRepository.findAll().size())));
                professeurRepository.save(professeur);
            }
            //faker ProfesseurClasse
            for (int i=0;i<=30;i++){
                List<String> annee_scolaire = new ArrayList<>();
                annee_scolaire.add("2019-2020");
                annee_scolaire.add("2020-2021");
                annee_scolaire.add("2021-2022");
                List<ClasseProfesseur> annee_enseignes = new ArrayList<>();
                Professeur rand_professeur = professeurRepository.findAll().get(faker.number().numberBetween(1,professeurRepository.findAll().size()));
                Classe rand_classe = classeRepository.findAll().get(faker.number().numberBetween(1,classeRepository.findAll().size()));
                ClasseProfesseur classeProfesseur = new ClasseProfesseur(null,
                        annee_scolaire.get(faker.number().numberBetween(1,annee_scolaire.size())),
                        rand_classe,
                        rand_professeur);
                annee_enseignes.add(classeProfesseur);
                rand_professeur.setAnnees(annee_enseignes);
                rand_classe.setProfesseurs_classes(annee_enseignes);
                classeProfesseurRepository.save(classeProfesseur);
                professeurRepository.save(rand_professeur);
                classeRepository.save(rand_classe);
            }
            //faker Parents
            for(int i =0; i<20;i++){
                Parent parent = new Parent();
                parent.setPrenom(faker.name().firstName());
                parent.setNom(faker.name().lastName());
                parent.setUserName("par"+parent.getPrenom()+faker.number().digits(3));
                parent.setPassword(passwordEncoder.encode("Pa1234"));
                parent.setAdresse(faker.address().streetName());
                parent.setJoinDate(faker.date().between(df.parse("01-01-2000"),new Date()));
                parent.setRole(ROLE_PARENT.name());
                parent.setAuthorities(ROLE_PARENT.getAuthorities());
                parent.setActive(true);
                parent.setNotLocked(true);
                parent.setTelephone("77"+faker.number().digits(7));
                parent.setGenre((Math.random()>0.5)?genreRepository.findById(1L).orElse(null):genreRepository.findById(2L).orElse(null));
                parent.setCni((parent.getGenre().getId()==1)?"1"+faker.number().digits(12):"2"+faker.number().digits(12));
                parentRepository.save(parent);
            }
            // faker Eleve
            parentRepository.findAll().forEach(parent -> {
                for (int i = 0; i<faker.number().numberBetween(1,4);i++){
                    try {
                        Eleve eleve = new Eleve();
                        eleve.setParent(parent);
                        eleve.setPrenom(faker.name().firstName());
                        eleve.setNom(faker.name().lastName());
                        eleve.setPassword(passwordEncoder.encode("Ele1234"));
                        eleve.setAdresse(faker.address().streetName());
                        eleve.setJoinDate(faker.date().between(df.parse("01-01-2000"),new Date()));
                        eleve.setRole(ROLE_ELEVE.name());
                        eleve.setAuthorities(ROLE_ELEVE.getAuthorities());
                        eleve.setActive(true);
                        eleve.setNotLocked(true);
                        eleve.setGenre((Math.random()>0.5)?genreRepository.findById(1L).orElse(null):genreRepository.findById(2L).orElse(null));
                        eleve.setDate_naissance(faker.date().between(df.parse("01-01-1998"),df.parse("01-01-2017")));
                        eleveRepository.save(eleve);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            //faker EleveClasse
            eleveRepository.findAll().forEach(eleve -> {
                List<String> annee_scolaire = new ArrayList<>();
                annee_scolaire.add("2019-2020");
                annee_scolaire.add("2020-2021");
                annee_scolaire.add("2021-2022");
                annee_scolaire.add("2013-2014");
                Classe rand_classe = classeRepository.findAll().get(faker.number().numberBetween(1,classeRepository.findAll().size()));
                eleve.setMatricule(rand_classe.getLibelle().substring(0,2).toUpperCase()+
                        '-' +'0'+faker.number().digits(4)+
                        eleve.getParent().getPrenom().substring(0,1).toUpperCase()+
                        eleve.getParent().getNom().substring(0,1).toUpperCase());
                eleve.setUserName(eleve.getMatricule());
                EleveClasse eleveClasse = new EleveClasse();
                eleveClasse.setAnneeScolaire(annee_scolaire.get(faker.number().numberBetween(1,annee_scolaire.size())));
                eleveClasse.setClasse(rand_classe);
                eleveClasse.setEleve(eleve);
                eleveRepository.save(eleve);
                eleveClasseRepository.save(eleveClasse);
            });
            //faker Note
            for(int i=0;i<200;i++){
                Matiere matiere = matiereRepository.findAll().get(faker.number().numberBetween(1,matiereRepository.findAll().size()));
                Eleve eleve = eleveRepository.findAll().get(faker.number().numberBetween(1,eleveRepository.findAll().size()));
                Evaluation evaluation = new Evaluation(null,faker.number().numberBetween(0,20),faker.date().between(df.parse("01-10-2019"),df.parse("30-06-2022")), matiere, eleve);
                evaluationRepository.save(evaluation);
            }
        };
    }
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();

        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
        corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
                "Accept", "Jwt-Token", "Authorization", "Origin", "X-Requested-With", "Access-Control-Request-Method",
                "Access-Control-Request-Headers"));
        corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Jwt-Token", "Authorization",
                "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));

        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }
    @Bean
    public Faker faker(){
        return new Faker();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return  new BCryptPasswordEncoder();
    }
}
