package com.banque.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "comptes")
public class Compte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double solde;

    @Column(nullable = false)
    private LocalDate dateCreation;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeCompte type;

    public Compte() {
    }

    public Compte(Double solde, LocalDate dateCreation, TypeCompte type) {
        this.solde = solde;
        this.dateCreation = dateCreation;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getSolde() {
        return solde;
    }

    public void setSolde(Double solde) {
        this.solde = solde;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public TypeCompte getType() {
        return type;
    }

    public void setType(TypeCompte type) {
        this.type = type;
    }
}

