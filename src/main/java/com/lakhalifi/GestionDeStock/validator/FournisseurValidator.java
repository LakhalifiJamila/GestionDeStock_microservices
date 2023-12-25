package com.lakhalifi.GestionDeStock.validator;

import com.lakhalifi.GestionDeStock.dto.ClientDto;
import com.lakhalifi.GestionDeStock.dto.FournisseurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class FournisseurValidator {
    public static List<String> validate(FournisseurDto dto){
        List<String> errors= new ArrayList<>();

        if(dto == null){
            errors.add("Veuillez renseigner le nom du fournisseur");
            errors.add("Veuillez renseigner le prenom du fournisseur");
            errors.add("Veuillez renseigner le Mail du fournisseur");
            errors.add("Veuillez renseigner le numero de telephone du fournisseur");

            return errors;

        }

        if(!StringUtils.hasLength(dto.getNom())){
            errors.add("Veuillez renseigner le nom du fournisseur");
        }

        if(!StringUtils.hasLength(dto.getPrenom())){
            errors.add("Veuillez renseigner le prenom du fournisseur");
        }

        if(!StringUtils.hasLength(dto.getMail())){
            errors.add("Veuillez renseigner le Mail du fournisseur");
        }

        if(!StringUtils.hasLength(dto.getNumTel())){
            errors.add("Veuillez renseigner le numero de telephone du fournisseur");
        }

        return errors;
    }
}
