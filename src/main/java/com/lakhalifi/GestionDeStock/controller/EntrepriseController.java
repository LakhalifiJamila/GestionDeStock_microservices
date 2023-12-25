package com.lakhalifi.GestionDeStock.controller;

import com.lakhalifi.GestionDeStock.controller.api.EntrepriseApi;
import com.lakhalifi.GestionDeStock.dto.EntrepriseDto;
import com.lakhalifi.GestionDeStock.services.EntrepriseService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.lakhalifi.GestionDeStock.utils.Constants.ENTREPRISE_ENDPOINT;

@RestController
public class EntrepriseController implements EntrepriseApi {

    private EntrepriseService entrepriseService;

    @Autowired
    public EntrepriseController(EntrepriseService entrepriseService){
        this.entrepriseService= entrepriseService;
    }

    @Override
    public EntrepriseDto save(EntrepriseDto dto) {
        return entrepriseService.save(dto);
    }

    @Override
    public EntrepriseDto findById(Integer id) {
        return entrepriseService.findById(id);
    }

    //une entreprise lorsqu'elle se connecte ne doit pas avoir la possibilité de voir la liste de toutes les entreprises
    //mais on a fiat cette méthode juste pour l'administration, pour que l'admin aura l'accès à toutes les entreprises
    @Override
    public List<EntrepriseDto> findAll() {
        return entrepriseService.findAll();
    }

    @Override
    public void delete(Integer id) {
        entrepriseService.delete(id);
    }
}
