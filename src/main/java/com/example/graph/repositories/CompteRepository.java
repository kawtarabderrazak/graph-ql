package com.example.graph.repositories;


import com.example.graph.entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface CompteRepository extends JpaRepository<Compte, Long> {
    long count();

    @Query("SELECT SUM(c.solde) FROM Compte c")
    double sumSoldes();
}