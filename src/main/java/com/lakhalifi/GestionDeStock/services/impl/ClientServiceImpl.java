package com.lakhalifi.GestionDeStock.services.impl;

import com.lakhalifi.GestionDeStock.dto.ClientDto;
import com.lakhalifi.GestionDeStock.exception.EntityNotFoundException;
import com.lakhalifi.GestionDeStock.exception.ErrorCodes;
import com.lakhalifi.GestionDeStock.exception.InvalidEntityException;
import com.lakhalifi.GestionDeStock.model.Client;
import com.lakhalifi.GestionDeStock.repository.ClientRepository;
import com.lakhalifi.GestionDeStock.services.ClientService;
import com.lakhalifi.GestionDeStock.validator.ClientValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository){
        this.clientRepository= clientRepository;
    }

    @Override
    public ClientDto save(ClientDto dto) {
        List<String> errors= ClientValidator.validate(dto);

        if(!errors.isEmpty()){
            log.error("Client is not valid {}", dto);
            throw new InvalidEntityException("Le client n'est pas valide", ErrorCodes.CLIENT_NOT_VALID, errors);
        }

        return ClientDto.fromEntity(clientRepository.save(ClientDto.toEntity(dto)));
    }

    @Override
    public ClientDto findById(Integer id) {
        if(id == null){
            log.error("Client ID is null");
            return null;
        }

        Optional<Client> client= clientRepository.findById(id);

        return Optional.of(ClientDto.fromEntity(client.get())).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun client avec l'ID = "+id+" n'a ete trouve dans la BDD",
                        ErrorCodes.CLIENT_NOT_FOUND)
        );
    }

    @Override
    public List<ClientDto> findAll() {
        return clientRepository.findAll().stream()
                .map(ClientDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {

        if(id == null){
            log.error("Client ID is null");
            return;
        }

        clientRepository.deleteById(id);
    }
}
