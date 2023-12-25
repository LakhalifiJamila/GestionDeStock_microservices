package com.lakhalifi.GestionDeStock.repository;

import com.lakhalifi.GestionDeStock.model.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntrepriseRepository extends JpaRepository<Entreprise, Integer> {
}
