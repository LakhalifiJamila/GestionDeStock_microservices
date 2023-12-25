package com.lakhalifi.GestionDeStock.controller;

import com.lakhalifi.GestionDeStock.controller.api.CommandeClientApi;
import com.lakhalifi.GestionDeStock.dto.CommandeClientDto;
import com.lakhalifi.GestionDeStock.services.CommandeClientService;
import com.lakhalifi.GestionDeStock.services.impl.CommandeClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommandeClientController implements CommandeClientApi {

    private CommandeClientService commandeClientService;

    @Autowired
    public CommandeClientController(CommandeClientServiceImpl commandeClientService){
        this.commandeClientService= commandeClientService;
    }

    //"ResponseEntity" encapsule le type de retour
    @Override
    public ResponseEntity<CommandeClientDto> save(CommandeClientDto dto) {
        return ResponseEntity.ok(commandeClientService.save(dto));
        //OU BIEN
        //return ResponseEntity.status(HttpStatus.OK).body(commandeClientService.save(dto));
    }

    @Override
    public ResponseEntity<CommandeClientDto> findById(Integer id) {
        return ResponseEntity.ok(commandeClientService.findById(id));
    }

    @Override
    public ResponseEntity<CommandeClientDto> findByCode(String code) {
        return ResponseEntity.ok(commandeClientService.findByCode(code));
    }

    @Override
    public ResponseEntity<List<CommandeClientDto>> findAll() {
        return ResponseEntity.ok(commandeClientService.findAll());
    }

    @Override
    public ResponseEntity delete(Integer id) {

        commandeClientService.delete(id);
        return ResponseEntity.ok().build();
    }
}
