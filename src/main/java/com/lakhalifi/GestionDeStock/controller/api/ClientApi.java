package com.lakhalifi.GestionDeStock.controller.api;

import com.lakhalifi.GestionDeStock.dto.ClientDto;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.lakhalifi.GestionDeStock.utils.Constants.APP_ROOT;

@Api(APP_ROOT + "/clients") //cette annotation toute seule est suffisante pour creer une documentation
public interface ClientApi {

    //Nomination: (exemple: books)
    // POST - books/
    // GET - books/ (get all)
    // GET - books/{id}
    // GET - books/{code}
    // PATCH - books/
    // DELETE - books/{id}

    @PostMapping(value = APP_ROOT + "/clients/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ClientDto save(@RequestBody ClientDto dto);

    @GetMapping(value = APP_ROOT + "/clients/{idClient}", produces = MediaType.APPLICATION_JSON_VALUE)
    ClientDto findById(@PathVariable("idClient") Integer id);

    @GetMapping(value = APP_ROOT + "/clients/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<ClientDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/clients/delete/{idClient}")
    void delete(@PathVariable("idClient") Integer id);

}
