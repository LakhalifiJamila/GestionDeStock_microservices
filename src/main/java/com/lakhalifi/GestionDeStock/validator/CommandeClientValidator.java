package com.lakhalifi.GestionDeStock.validator;

import com.lakhalifi.GestionDeStock.dto.CommandeClientDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CommandeClientValidator {

    public static List<String> validate(CommandeClientDto dto){

        List<String> errors= new ArrayList<>();

        if(dto == null){
            errors.add("Veuillez renseigner le code de la commande client");
            errors.add("Veuillez renseigner la date de la commande");
            errors.add("Le client est null");
            errors.add("L'ID du client est null");

            return errors;
        }

        if (!StringUtils.hasLength(dto.getCode())) {
            errors.add("Veuillez renseigner le code de la commande client");
        }

        if(dto.getDateCommande() == null){
            errors.add("Veuillez renseigner la date de la commande");
        }

        if(dto.getClient() == null){
            errors.add("Le client est null");
        }

        if(dto.getClient().getId() == null){
            errors.add("L'ID du client est null");
        }

        return errors;
    }

}
