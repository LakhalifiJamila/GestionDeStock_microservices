package com.lakhalifi.GestionDeStock.services.impl;

import com.lakhalifi.GestionDeStock.dto.FournisseurDto;
import com.lakhalifi.GestionDeStock.exception.EntityNotFoundException;
import com.lakhalifi.GestionDeStock.exception.ErrorCodes;
import com.lakhalifi.GestionDeStock.exception.InvalidEntityException;
import com.lakhalifi.GestionDeStock.model.Fournisseur;
import com.lakhalifi.GestionDeStock.repository.FournisseurRepository;
import com.lakhalifi.GestionDeStock.services.FournisseurService;
import com.lakhalifi.GestionDeStock.validator.FournisseurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FournisseurServiceImpl implements FournisseurService {

    private FournisseurRepository fournisseurRepository;

    @Autowired
    public FournisseurServiceImpl(FournisseurRepository fournisseurRepository){
        this.fournisseurRepository= fournisseurRepository;
    }

    @Override
    public FournisseurDto save(FournisseurDto dto) {
       List<String> errors= FournisseurValidator.validate(dto);
       if(!errors.isEmpty()){
           log.error("Fournisseur is not valid {}", dto);
           throw new InvalidEntityException("Le fournisseur n'est pas valide", ErrorCodes.FOURNISSEUR_NOT_VALID, errors);
       }

       return FournisseurDto.fromEntity(fournisseurRepository.save(FournisseurDto.toEntity(dto)));
    }

    @Override
    public FournisseurDto findById(Integer id) {
        if(id == null){
            log.error("Fournisseur ID is null");
            return null;
        }

        Optional<Fournisseur> fournisseur= fournisseurRepository.findById(id);

        return Optional.of(FournisseurDto.fromEntity(fournisseur.get())).orElseThrow(() ->
                new EntityNotFoundException("Aucun fournisseur avec l'ID = "+id+" n'a ete trouve dans la BDD",
                        ErrorCodes.FOURNISSEUR_NOT_FOUND)
        );
    }

    @Override
    public List<FournisseurDto> findAll() {
        return fournisseurRepository.findAll().stream()
                .map(FournisseurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {

        if(id == null){
            log.error("Fournisseur ID is null");
            return;
        }

        fournisseurRepository.deleteById(id);

    }
}
