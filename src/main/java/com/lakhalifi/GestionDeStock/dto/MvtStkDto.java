package com.lakhalifi.GestionDeStock.dto;

import com.lakhalifi.GestionDeStock.model.Article;
import com.lakhalifi.GestionDeStock.model.MvtStk;
import com.lakhalifi.GestionDeStock.model.TypeMvtStk;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
public class MvtStkDto {

    private Integer id;

    private Instant dateMvt;

    private BigDecimal quantite;

    private ArticleDto article;

    private TypeMvtStk typeMvt;

    private Integer idEntreprise;

    public static MvtStkDto fromEntity(MvtStk mvtStk){
        if(mvtStk == null){
            return null;
        }

        return MvtStkDto.builder()
                .id(mvtStk.getId())
                .dateMvt(mvtStk.getDateMvt())
                .quantite(mvtStk.getQuantite())
                .article(ArticleDto.fromEntity(mvtStk.getArticle()))
                .typeMvt(mvtStk.getTypeMvt())
                .idEntreprise(mvtStk.getIdEntreprise())
                .build();
    }

    public static MvtStk toEntity(MvtStkDto mvtStkDto){
        if(mvtStkDto == null){
            return null;
        }

        MvtStk mvtStk= new MvtStk();
        mvtStk.setId(mvtStkDto.getId());
        mvtStk.setDateMvt(mvtStkDto.getDateMvt());
        mvtStk.setQuantite(mvtStkDto.getQuantite());
        mvtStk.setArticle(ArticleDto.toEntity(mvtStkDto.getArticle()));
        mvtStk.setTypeMvt(mvtStkDto.getTypeMvt());
        mvtStk.setIdEntreprise(mvtStkDto.getIdEntreprise());

        return mvtStk;
    }

}
