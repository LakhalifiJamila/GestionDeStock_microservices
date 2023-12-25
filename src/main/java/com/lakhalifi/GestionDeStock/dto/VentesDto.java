package com.lakhalifi.GestionDeStock.dto;

import com.lakhalifi.GestionDeStock.model.Ventes;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import java.time.Instant;
import java.util.List;

@Data
@Builder
public class VentesDto {

    private Integer id;

    private String code;//VALIDE

    private Instant dateVente;//VALIDE

    private String commentaire;

    private Integer idEntreprise;

    private List<LigneVenteDto> ligneVentes;//pas de mapping pour la liste car c'est trop lourd
//AUSSI A VALIDER ligneVentes si nest pas null et aussi article de chacune si nest pas nul et son id
    public static VentesDto fromEntity(Ventes ventes) {

        if(ventes == null){
            return null;
        }

        return VentesDto.builder()
                .id(ventes.getId())
                .code(ventes.getCode())
                .dateVente(ventes.getDateVente())
                .commentaire(ventes.getCommentaire())
                .idEntreprise(ventes.getIdEntreprise())
                .build();

    }

    public static Ventes toEntity(VentesDto ventesDto) {

        if(ventesDto == null){
            return null;
        }

        Ventes ventes= new Ventes();
        ventes.setId(ventesDto.getId());
        ventes.setCode(ventesDto.getCode());
        ventes.setDateVente(ventesDto.getDateVente());
        ventes.setCommentaire(ventesDto.getCommentaire());
        ventes.setIdEntreprise(ventesDto.getIdEntreprise());

        return ventes;

    }
}
