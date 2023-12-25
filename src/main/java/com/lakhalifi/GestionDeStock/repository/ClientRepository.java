package com.lakhalifi.GestionDeStock.repository;

import com.lakhalifi.GestionDeStock.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {

    //nothing
}
