package com.example.graph;

import com.example.graph.entities.Compte;
import com.example.graph.entities.TypeCompte;
import com.example.graph.repositories.CompteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class GraphApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraphApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CompteRepository compteRepository) {
        return args -> {
            // Insérer des comptes dans la base de données
            compteRepository.save(new Compte(null, 1500.50, LocalDate.of(2024, 1, 1), TypeCompte.COURANT));
            compteRepository.save(new Compte(null, 3000.75, LocalDate.of(2024, 2, 1), TypeCompte.EPARGNE));
            compteRepository.save(new Compte(null, 250.00, LocalDate.of(2024, 3, 1), TypeCompte.COURANT));
            compteRepository.save(new Compte(null, 4500.90, LocalDate.of(2024, 4, 1), TypeCompte.EPARGNE));

            // Afficher les comptes pour vérification
            compteRepository.findAll().forEach(System.out::println);
        };
    }
}
