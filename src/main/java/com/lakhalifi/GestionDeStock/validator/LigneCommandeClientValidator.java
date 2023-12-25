package com.lakhalifi.GestionDeStock.validator;

import com.lakhalifi.GestionDeStock.dto.LigneCommandeClientDto;

import java.util.ArrayList;
import java.util.List;

public class LigneCommandeClientValidator {

    public static List<String> validate(LigneCommandeClientDto dto){

        List<String> errors= new ArrayList<>();

        if(dto == null){
            errors.add("Veuillez renseigner un article");
            errors.add("L'ID de l'article est null");
            errors.add("Veuillez renseigner une quantite");
            errors.add("Veuillez renseigner un prix unitaire");

            return errors;
        }

        if(dto.getArticle() == null){
            errors.add("Veuillez renseigner un article");
        }
        if(dto.getArticle().getId() == null){
            errors.add("ID de l'article est null");
        }
        if(dto.getArticle() == null){
            errors.add("Veuillez renseigner un article");
        }

        if(dto.getQuantite() == null){
            errors.add("Veuillez renseigner une quantite");
        }

        if(dto.getPrixUnitaire() == null){
            errors.add("Veuillez renseigner un prix unitaire");
        }

        return errors;
    }

}
