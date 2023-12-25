package com.lakhalifi.GestionDeStock.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lakhalifi.GestionDeStock.model.Category;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CategoryDto {

    private Integer id;

    private String code;


    private String designation;//càd description

    private Integer idEntreprise;

    @JsonIgnore
    private List<ArticleDto> articles;

    @JsonCreator
    public CategoryDto(@JsonProperty("id") Integer id,
                      @JsonProperty("code") String code,
                      @JsonProperty("designation") String designation,
                      @JsonProperty("idEntreprise") Integer idEntreprise,
                      @JsonProperty("articles") List<ArticleDto> articles)

    {

        this.id = id;
        this.code = code;
        this.designation = designation;
        this.idEntreprise = idEntreprise;
        this.articles= articles;
    }


    public static CategoryDto fromEntity(Category category){
        if(category == null){
            return null;
            //TODO throw an exception
        }

        return CategoryDto.builder()
                .id(category.getId())
                .code(category.getCode())
                .designation(category.getDesignation())
                .idEntreprise(category.getIdEntreprise())
                .build();
    }

    public static Category toEntity(CategoryDto categoryDto){
        if (categoryDto == null){
            return null;
            //TODO throw an exception
        }

        Category category= new Category();
        category.setId(categoryDto.getId());
        category.setCode(categoryDto.getCode());
        category.setIdEntreprise(categoryDto.getIdEntreprise());
        category.setDesignation(categoryDto.getDesignation());

        return category;
    }

}
