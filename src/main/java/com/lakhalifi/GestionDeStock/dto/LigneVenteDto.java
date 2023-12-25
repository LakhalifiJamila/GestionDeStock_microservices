package com.lakhalifi.GestionDeStock.dto;

import com.lakhalifi.GestionDeStock.model.Article;
import com.lakhalifi.GestionDeStock.model.LigneVente;
import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Builder
public class LigneVenteDto {

    private Integer id;

    private VentesDto vente;

    private BigDecimal quantite;

    private BigDecimal prixUnitaire;

    private Article article;

    private Integer idEntreprise;

    public static LigneVenteDto fromEntity(LigneVente ligneVente){
        if(ligneVente == null){
            return null;
        }

        return LigneVenteDto.builder()
                .id(ligneVente.getId())
                .quantite(ligneVente.getQuantite())
                .prixUnitaire(ligneVente.getPrixUnitaire())
                .idEntreprise(ligneVente.getIdEntreprise())
                .build();
    }

    public static LigneVente toEntity(LigneVenteDto ligneVenteDto){
        if(ligneVenteDto == null){
            return null;
        }

        LigneVente ligneVente= new LigneVente();
        ligneVente.setId(ligneVenteDto.getId());
        //ligneVente.setVente(VentesDto.toEntity(ligneVenteDto.getVente()));
        ligneVente.setPrixUnitaire(ligneVenteDto.getPrixUnitaire());
        ligneVente.setIdEntreprise(ligneVenteDto.getIdEntreprise());

        return ligneVente;
    }


}
