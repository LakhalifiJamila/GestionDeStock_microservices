package com.lakhalifi.GestionDeStock.services;

import com.lakhalifi.GestionDeStock.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    public CategoryDto save(CategoryDto dto);

    public CategoryDto findById(Integer id);

    public CategoryDto findByCode(String code);

    public List<CategoryDto> findAll();

    public void delete(Integer id);
}
