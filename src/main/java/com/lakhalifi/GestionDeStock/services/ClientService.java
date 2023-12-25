package com.lakhalifi.GestionDeStock.services;

import com.lakhalifi.GestionDeStock.dto.ClientDto;

import java.util.List;

public interface ClientService {

    ClientDto save(ClientDto dto);

    ClientDto findById(Integer id);

    List<ClientDto> findAll();

    void delete(Integer id);

}
