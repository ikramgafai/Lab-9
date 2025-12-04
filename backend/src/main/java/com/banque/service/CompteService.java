package com.banque.service;

import com.banque.model.Compte;
import com.banque.repository.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompteService {
    @Autowired
    private CompteRepository compteRepository;

    public List<Compte> getAllComptes() {
        return compteRepository.findAll();
    }

    public Optional<Compte> getCompteById(Long id) {
        return compteRepository.findById(id);
    }

    public Compte createCompte(Compte compte) {
        return compteRepository.save(compte);
    }

    public Compte updateCompte(Long id, Compte compteDetails) {
        Compte compte = compteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compte not found with id: " + id));
        compte.setSolde(compteDetails.getSolde());
        compte.setDateCreation(compteDetails.getDateCreation());
        compte.setType(compteDetails.getType());
        return compteRepository.save(compte);
    }

    public void deleteCompte(Long id) {
        compteRepository.deleteById(id);
    }
}

