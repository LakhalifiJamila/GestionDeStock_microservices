package com.lakhalifi.GestionDeStock.dto;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lakhalifi.GestionDeStock.model.Article;
import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;

@Builder
@Data
public class ArticleDto {

    private Integer id;

    private String codeArticle;

    private String designation;

    private BigDecimal prixUnitaireHt;

    private BigDecimal tauxTva;

    private BigDecimal prixUnitaireTtc;

    private String photo;

    private CategoryDto category;

    private Integer idEntreprise;

    @JsonCreator
    public ArticleDto(@JsonProperty("id") Integer id,
                      @JsonProperty("codeArticle") String codeArticle,
                      @JsonProperty("designation") String designation,
                      @JsonProperty("prixUnitaireHt") BigDecimal prixUnitaireHt,
                      @JsonProperty("tauxTva") BigDecimal tauxTva,
                      @JsonProperty("prixUnitaireTtc") BigDecimal prixUnitaireTtc,
                      @JsonProperty("photo") String photo,
                      @JsonProperty("category") CategoryDto category,
                      @JsonProperty("idEntreprise") Integer idEntreprise) {

        this.id = id;
        this.codeArticle = codeArticle;
        this.designation = designation;
        this.prixUnitaireHt = prixUnitaireHt;
        this.tauxTva = tauxTva;
        this.prixUnitaireTtc = prixUnitaireTtc;
        this.photo = photo;
        this.category = category;
        this.idEntreprise = idEntreprise;
    }



    public static ArticleDto fromEntity(Article article){
        if(article == null){
            return null;
        }

        return ArticleDto.builder()
                .id(article.getId())
                .codeArticle(article.getCodeArticle())
                .designation(article.getDesignation())
                .prixUnitaireHt(article.getPrixUnitaireHt())
                .tauxTva(article.getTauxTva())
                .prixUnitaireTtc(article.getPrixUnitaireTtc())
                .photo(article.getPhoto())
                .category(CategoryDto.fromEntity(article.getCategory()))
                .idEntreprise(article.getIdEntreprise())
                .build();
    }

    public static Article toEntity(ArticleDto articleDto){

        if(articleDto == null){
            return null;
        }

        Article article= new Article();
        article.setId(articleDto.getId());
        article.setCodeArticle(articleDto.getCodeArticle());
        article.setDesignation(articleDto.getDesignation());
        article.setPrixUnitaireHt(articleDto.getPrixUnitaireHt());
        article.setTauxTva(articleDto.getTauxTva());
        article.setPrixUnitaireTtc(articleDto.getPrixUnitaireTtc());
        article.setPhoto(articleDto.getPhoto());
        article.setIdEntreprise(articleDto.getIdEntreprise());
        article.setCategory(CategoryDto.toEntity(articleDto.getCategory()));

        return article;
    }

}
