package com.lakhalifi.GestionDeStock.repository;

import com.lakhalifi.GestionDeStock.model.MvtStk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MvtStkRepository extends JpaRepository<MvtStk, Integer> {
}
