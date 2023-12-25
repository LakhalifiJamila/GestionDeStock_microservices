package com.lakhalifi.GestionDeStock.controller.api;

import com.lakhalifi.GestionDeStock.dto.FournisseurDto;
import com.lakhalifi.GestionDeStock.model.Fournisseur;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.lakhalifi.GestionDeStock.utils.Constants.FOURNISSEUR_ENDPOINT;

@Api(FOURNISSEUR_ENDPOINT)
public interface FournisseurApi {

    @PostMapping(FOURNISSEUR_ENDPOINT + "/create")
    FournisseurDto save(@RequestBody FournisseurDto dto);

    @GetMapping(FOURNISSEUR_ENDPOINT + "/{idFournisseur}")
    FournisseurDto findById(@PathVariable("idFournisseur") Integer id);

    @GetMapping(FOURNISSEUR_ENDPOINT + "/all")
    List<FournisseurDto> findAll();

    @DeleteMapping(FOURNISSEUR_ENDPOINT + "/delete/{idFournisseur}")
    void delete(@PathVariable("idFournisseur") Integer id);
}
