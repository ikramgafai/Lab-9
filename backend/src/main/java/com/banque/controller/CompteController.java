package com.banque.controller;

import com.banque.model.Compte;
import com.banque.service.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/banque/comptes")
@CrossOrigin("*")
public class CompteController {
    @Autowired
    private CompteService compteService;

    @GetMapping
    public ResponseEntity<List<Compte>> getAllComptes() {
        List<Compte> comptes = compteService.getAllComptes();
        return ResponseEntity.ok(comptes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Compte> getCompteById(@PathVariable Long id) {
        Optional<Compte> compte = compteService.getCompteById(id);
        return compte.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Compte> createCompte(@RequestBody Compte compte) {
        Compte createdCompte = compteService.createCompte(compte);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCompte);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Compte> updateCompte(@PathVariable Long id, @RequestBody Compte compte) {
        try {
            Compte updatedCompte = compteService.updateCompte(id, compte);
            return ResponseEntity.ok(updatedCompte);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompte(@PathVariable Long id) {
        compteService.deleteCompte(id);
        return ResponseEntity.noContent().build();
    }
}

