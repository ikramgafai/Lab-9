package com.banque.config;

import com.banque.model.Compte;
import com.banque.model.TypeCompte;
import com.banque.repository.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private CompteRepository compteRepository;

    @Override
    public void run(String... args) {
        if (compteRepository.count() == 0) {
            Compte compte1 = new Compte(5000.0, LocalDate.of(2024, 1, 15), TypeCompte.COURANT);
            Compte compte2 = new Compte(10000.0, LocalDate.of(2024, 2, 20), TypeCompte.EPARGNE);
            Compte compte3 = new Compte(7500.0, LocalDate.of(2024, 3, 10), TypeCompte.COURANT);

            compteRepository.save(compte1);
            compteRepository.save(compte2);
            compteRepository.save(compte3);

            System.out.println("Initial data loaded: 3 comptes created");
        }
    }
}

