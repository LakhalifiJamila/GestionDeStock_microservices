package com.lakhalifi.GestionDeStock.repository;

import com.lakhalifi.GestionDeStock.model.Article;
import com.lakhalifi.GestionDeStock.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Optional<Category> findCategoryByCode(String code);
}
