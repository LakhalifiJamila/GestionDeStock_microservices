package com.lakhalifi.GestionDeStock.repository;

import com.lakhalifi.GestionDeStock.model.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FournisseurRepository extends JpaRepository<Fournisseur, Integer> {
}
