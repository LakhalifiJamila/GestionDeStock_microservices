package com.lakhalifi.GestionDeStock.controller;

import com.lakhalifi.GestionDeStock.controller.api.VentesApi;
import com.lakhalifi.GestionDeStock.dto.VentesDto;
import com.lakhalifi.GestionDeStock.services.VentesService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VentesController implements VentesApi {

    private VentesService ventesService;

    public VentesController(VentesService ventesService){
        this.ventesService= ventesService;
    }

    @Override
    public VentesDto save(VentesDto dto) {
        return ventesService.save(dto);
    }

    @Override
    public VentesDto findById(Integer id) {
        return ventesService.findById(id);
    }

    @Override
    public VentesDto findByCode(String code) {
        return ventesService.findByCode(code);
    }

    @Override
    public List<VentesDto> findAll() {
        return ventesService.findAll();
    }

    @Override
    public void delete(Integer id) {
        ventesService.delete(id);
    }
}
