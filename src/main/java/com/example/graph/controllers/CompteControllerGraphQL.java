package com.example.graph.controllers;


import com.example.graph.entities.Compte;
import com.example.graph.repositories.CompteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class CompteControllerGraphQL {

    private CompteRepository compteRepository;

    @QueryMapping
    public List<Compte> allComptes() {
        return compteRepository.findAll();
    }

    @QueryMapping
    public Compte compteById(@Argument Long id) {
        Compte compte = compteRepository.findById(id).orElse(null);
        if (compte == null)
            throw new RuntimeException(String.format("Compte %s not found", id));
        else
            return compte;
    }

    @MutationMapping
    public Compte saveCompte(@Argument Compte compte) {
        LocalDate dateCreation = LocalDate.parse(compte.getDateCreation().toString());  // Convertir la date en LocalDate
        compte.setDateCreation(dateCreation);  // Affecter la date convertie à l'objet

        return compteRepository.save(compte);
    }

    @QueryMapping
    public Map<String, Object> totalSolde() {
        long count = compteRepository.count();
        double sum = compteRepository.sumSoldes();
        double average = count > 0 ? sum / count : 0;

        return Map.of(
                "count", count,
                "sum", sum,
                "average", average
        );
    }
}