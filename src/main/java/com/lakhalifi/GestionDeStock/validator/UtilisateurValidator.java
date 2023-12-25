package com.lakhalifi.GestionDeStock.validator;

import com.lakhalifi.GestionDeStock.dto.UtilisateurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/*
En Java, le terme "utilité" (ou "utility" en anglais) ne fait pas
 référence à un concept spécifique du langage, mais il est souvent
 associé à des classes ou des méthodes utilitaires. Ces classes ou
 méthodes utilitaires sont conçues pour fournir un ensemble de
 fonctionnalités génériques ou des services qui peuvent être utilisés
 dans divers contextes de programmation. Elles ne sont pas liées à un
 objet particulier et sont souvent regroupées dans des classes
 statiques.
    ==========================
 StringUtils est une classe ou une utilité courante dans de nombreux
  langages de programmation, en particulier dans le langage Java,
  mais on peut également la trouver dans d'autres langages. Cette
  classe propose généralement un ensemble de méthodes statiques pour
  effectuer diverses opérations sur les chaînes de caractères, ce
  qui rend la manipulation et le traitement des chaînes plus pratiques
   et efficaces. Ces méthodes peuvent inclure des tâches telles que la
    concaténation de chaînes, la suppression des espaces vides, la
     recherche, le remplacement, la comparaison, etc.
 */

public class UtilisateurValidator {

    public static List<String> validate(UtilisateurDto utilisateurDto){
        List<String> errors= new ArrayList<>();

        if(utilisateurDto == null){
            errors.add("Veuillez renseigner le nom d'utilisateur");
            errors.add("Veuillez renseigner le prenom d'utilisateur");
            errors.add("Veuillez renseigner l'email d'utilisateur");
            errors.add("Veuillez renseigner le mot de passe d'utilisateur");
            errors.add("Veuillez renseigner l'adresse d'utilisateur");

            return errors;
        }

        if(!StringUtils.hasLength(utilisateurDto.getNom())){
            errors.add("Veuillez renseigner le nom d'utilisateur");
        }

        if(!StringUtils.hasLength(utilisateurDto.getPrenom())){
            errors.add("Veuillez renseigner le prenom d'utilisateur");
        }

        if(!StringUtils.hasLength(utilisateurDto.getEmail())){
            errors.add("Veuillez renseigner l'email d'utilisateur");
        }

        if(!StringUtils.hasLength(utilisateurDto.getMotDePasse())){
            errors.add("Veuillez renseigner le mot de passe d'utilisateur");
        }

        if(utilisateurDto.getDateDeNaissance() == null){
            errors.add("Veuillez renseigner la date de naissance d'utilisateur");
        }

        if(utilisateurDto.getAdresse() == null){
            errors.add("Veuillez renseigner l'adresse d'utilisateur");
        } else{

            if(!StringUtils.hasLength(utilisateurDto.getAdresse().getAdresse1())){
                errors.add("Le champs 'Adresse 1' est obligatoire");
            }
            if(!StringUtils.hasLength(utilisateurDto.getMotDePasse())){
                errors.add("Le champs 'Ville' est obligatoire");
            }
            if(!StringUtils.hasLength(utilisateurDto.getMotDePasse())){
                errors.add("Le champs 'Code postale' est obligatoire");
            }
            if(!StringUtils.hasLength(utilisateurDto.getMotDePasse())){
                errors.add("Le champs 'Pays' est obligatoire");
            }

        }

        return errors;
    }

}
