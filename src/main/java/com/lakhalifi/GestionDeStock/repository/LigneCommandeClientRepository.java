package com.lakhalifi.GestionDeStock.repository;

import com.lakhalifi.GestionDeStock.model.LigneCommandeClient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LigneCommandeClientRepository extends JpaRepository<LigneCommandeClient, Integer> {
}
