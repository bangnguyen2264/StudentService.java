package com.example.demo.Config;

import com.example.demo.Entity.Classes;
import com.example.demo.Repository.ClassesRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ClassConfig {
@Bean
   CommandLineRunner commandLineRunner(
           ClassesRepository classesRepository
){
    return args -> {
        Classes cn22A = new Classes(
                "CN22A"
        );
        Classes cn22B = new Classes(
                "CN22B"
        );
        classesRepository.saveAll(List.of(cn22B,cn22A));

    };
}
}
