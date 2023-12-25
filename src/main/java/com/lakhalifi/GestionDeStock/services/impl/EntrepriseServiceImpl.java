package com.lakhalifi.GestionDeStock.services.impl;

import com.lakhalifi.GestionDeStock.dto.EntrepriseDto;
import com.lakhalifi.GestionDeStock.exception.EntityNotFoundException;
import com.lakhalifi.GestionDeStock.exception.ErrorCodes;
import com.lakhalifi.GestionDeStock.exception.InvalidEntityException;
import com.lakhalifi.GestionDeStock.model.Entreprise;
import com.lakhalifi.GestionDeStock.repository.EntrepriseRepository;
import com.lakhalifi.GestionDeStock.services.EntrepriseService;
import com.lakhalifi.GestionDeStock.validator.EntrepriseValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EntrepriseServiceImpl implements EntrepriseService {

    private EntrepriseRepository entrepriseRepository;

    @Autowired
    public EntrepriseServiceImpl(EntrepriseRepository entrepriseRepository){
        this.entrepriseRepository= entrepriseRepository;
    }

    @Override
    public EntrepriseDto save(EntrepriseDto dto) {
        List<String> errors= EntrepriseValidator.validate(dto);
        if(!errors.isEmpty()){
            log.error("Entreprise is not valid {}", dto);
            throw new InvalidEntityException("L'entreprise n'est pas valide", ErrorCodes.ENTREPRISE_NOT_VALID, errors);
        }

        return EntrepriseDto.fromEntity(entrepriseRepository.save(EntrepriseDto.toEntity(dto)));
    }

    @Override
    public EntrepriseDto findById(Integer id) {
        if(id == null){
            log.error("Entreprise ID is null");
            return null;
        }

        Optional<Entreprise> entreprise= entrepriseRepository.findById(id);

        return Optional.of(EntrepriseDto.fromEntity(entreprise.get())).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucune entreprise avec l'ID = "+id+" n'a ete âs trouve dans la BDD",
                        ErrorCodes.ENTREPRISE_NOT_FOUND)
        );
    }

    @Override
    public List<EntrepriseDto> findAll() {
        return entrepriseRepository.findAll().stream()
                .map(EntrepriseDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {

        if(id == null){
            log.error("Entreprise ID is null");
            return;
        }
        entrepriseRepository.deleteById(id);

    }
}
