package com.lakhalifi.GestionDeStock.controller.api;

import com.lakhalifi.GestionDeStock.dto.CategoryDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

import static com.lakhalifi.GestionDeStock.utils.Constants.APP_ROOT;

@Api(APP_ROOT + "/categories")
public interface CategoryApi {

    @PostMapping(value = APP_ROOT + "/categories/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value= "Enregistrer une categorie", notes = "Cette méthode permet d'enregistrer ou modifier une categorie", response = CategoryDto.class)
    @ApiResponses(value= {
            @ApiResponse(code= 200, message= "L'objet categorie cree / modifie"),
            @ApiResponse(code= 400, message = "L'objet categorie n'est pas valide")
    })
    CategoryDto save(@RequestBody CategoryDto dto);

    @GetMapping(value = APP_ROOT + "/categories/{idCategory}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value= "Rechercher une categorie selon ID", notes = "Cette methode permet de chercher une categorie selon son ID", response = CategoryDto.class)
    @ApiResponses(value= {
            @ApiResponse(code= 200, message= "La categorie a ete trouve dans la BDD"),
            @ApiResponse(code= 404, message = "Aucune categorie n'existe dans la BDD avec l'ID fourni")
    })
    CategoryDto findById(@PathVariable("idCategory") Integer id);

    @GetMapping(value = APP_ROOT + "/categories/{codeCategory}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value= "Rechercher une categorie selon CODE", notes = "Cette methode permet de chercher une categorie selon son CODE", response = CategoryDto.class)
    @ApiResponses(value= {
            @ApiResponse(code= 200, message= "La categorie a ete trouve dans la BDD"),
            @ApiResponse(code= 404, message = "Aucune categorie n'existe dans la BDD avec l'CODE fourni")
    })
    CategoryDto findByCode(@PathVariable("codeCategory") String codeCategory);

    @GetMapping(value = APP_ROOT + "/categories/all", produces= MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoyer la liste de tous les categories", notes= "Cette methode permet de chercher et renvoyer la liste des categories qui existent dans la BDD",
            responseContainer = "List<CategoryDto>")
    @ApiResponses(value= {
            @ApiResponse(code= 200, message= "La liste des categories / une liste vide")
    })
    List<CategoryDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/categories/delete/{idCategory}")
    @ApiOperation(value= "Supprimer une categorie", notes = "Cette méthode permet de supprimer une categorie selon l'ID")
    @ApiResponses(value= {
            @ApiResponse(code= 200, message= "La categorie a ete bien supprimé")
    })
    void delete(@PathVariable("idCategory") Integer id);
}
