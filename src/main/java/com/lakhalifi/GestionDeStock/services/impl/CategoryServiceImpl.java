package com.lakhalifi.GestionDeStock.services.impl;

import com.lakhalifi.GestionDeStock.dto.CategoryDto;
import com.lakhalifi.GestionDeStock.exception.EntityNotFoundException;
import com.lakhalifi.GestionDeStock.exception.ErrorCodes;
import com.lakhalifi.GestionDeStock.exception.InvalidEntityException;
import com.lakhalifi.GestionDeStock.repository.CategoryRepository;
import com.lakhalifi.GestionDeStock.services.CategoryService;
import com.lakhalifi.GestionDeStock.validator.CategoryValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository= categoryRepository;
    }

    @Override
    public CategoryDto save(CategoryDto dto) {
        List<String> errors= CategoryValidator.validate(dto);
        if(!errors.isEmpty()){
            log.error("Article is not valid {}", dto);
            throw new InvalidEntityException("La category n'est pas valide", ErrorCodes.CATEGORY_NOT_FOUND, errors);
        }
        return CategoryDto.fromEntity(
                categoryRepository.save(CategoryDto.toEntity(dto))
        );
    }

    @Override
    public CategoryDto findById(Integer id) {
        if(id == null){
            log.error("Category ID is null");
            return null;
        }
        return categoryRepository.findById(id)
                .map(CategoryDto::fromEntity)//Mapping: càd on transforme Category en CategoryDto en utilisant la méthode fromEntity
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune category avec l'ID = "+id+" n'a ete trouve dans la BDD",
                        ErrorCodes.CATEGORY_NOT_FOUND)
                );
    }

    @Override
    public CategoryDto findByCode(String code) {
        if(!StringUtils.hasLength(code)){
            log.error("Category CODE is null");
            return null;
        }
        return categoryRepository.findCategoryByCode(code)
                .map(CategoryDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune categorie avec le CODE = "+code+ " n'a ete trouve dans la BDD",
                        ErrorCodes.CATEGORY_NOT_FOUND)
                );
    }

    @Override
    public List<CategoryDto> findAll() {
        return categoryRepository.findAll().stream()// stream() pour faire boucler
                .map(CategoryDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {

        if(id == null){
            log.error("Category ID is null");
            return;
        }
        categoryRepository.deleteById(id);

    }
}
