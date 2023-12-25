package com.lakhalifi.GestionDeStock.controller.api;

import com.lakhalifi.GestionDeStock.dto.ArticleDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.lakhalifi.GestionDeStock.utils.Constants.APP_ROOT;

@Api(APP_ROOT + "/articles")
public interface ArticleApi {

    @PostMapping(value = APP_ROOT + "/articles/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces= MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value= "Enregistrer un article", notes = "Cette méthode permet d'enregistrer ou modifier un article", response = ArticleDto.class) //"operation" en swagger càd méthode et "value" c'est une description de ce qu'elle fait cette méthode. "response" càd le type de retour de cette méthode
    @ApiResponses(value= {
            @ApiResponse(code= 200, message= "L'objet article créé / modifié"),
            @ApiResponse(code= 400, message= "L'objet article n'est pas valide")
    })
    ArticleDto save(@RequestBody ArticleDto dto);

    @GetMapping(value = APP_ROOT + "/articles/{idArticle}", produces= MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value= "Rechercher un article par ID", notes = "Cette méthode permet de rechercher un article selon l'ID", response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code= 200, message= "L'article a ete trouvé dans la BDD"),
            @ApiResponse(code= 404, message= "Aucun article n'existe dans la BDD avec l'ID fourni")
    })
    ArticleDto findById(@PathVariable("idArticle") Integer id);

    @GetMapping(value = APP_ROOT + "/articles/{codeArticle}", produces= MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value= "Rechercher un article par CODE", notes = "Cette méthode permet de rechercher un article selon le CODE", response = ArticleDto.class)
    @ApiResponses(value= {
            @ApiResponse(code= 200, message = "L'article a ete trouve dans la BDD"),
            @ApiResponse(code= 404, message= "Aucun article n'existe dans la BDD avec le CODE fourni")
    })
    ArticleDto findByCodeArticle(@PathVariable("codeArticle") String codeArticle);

    @GetMapping(value = APP_ROOT + "/articles/all", produces= MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoyer la liste de tous les articles", notes= "Cette methode permet de chercher et renvoyer la liste des articles qui existent dans la BDD",
            responseContainer = "List<ArticleDto>")
    @ApiResponses(value= {
            @ApiResponse(code= 200, message= "La liste des articles / une liste vide")
    })
    List<ArticleDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/articles/delete/{idArticle}")
    @ApiOperation(value= "Supprimer un article", notes = "Cette méthode permet de supprimer un article selon l'ID")
    @ApiResponses(value= {
            @ApiResponse(code= 200, message= "L'article a ete bien supprimé")
    })
    void delete(@PathVariable("idArticle") Integer id);
}
