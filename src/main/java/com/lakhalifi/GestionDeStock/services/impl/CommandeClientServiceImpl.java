package com.lakhalifi.GestionDeStock.services.impl;

import com.lakhalifi.GestionDeStock.dto.CommandeClientDto;
import com.lakhalifi.GestionDeStock.dto.LigneCommandeClientDto;
import com.lakhalifi.GestionDeStock.exception.EntityNotFoundException;
import com.lakhalifi.GestionDeStock.exception.ErrorCodes;
import com.lakhalifi.GestionDeStock.exception.InvalidEntityException;
import com.lakhalifi.GestionDeStock.model.Article;
import com.lakhalifi.GestionDeStock.model.Client;
import com.lakhalifi.GestionDeStock.model.CommandeClient;
import com.lakhalifi.GestionDeStock.model.LigneCommandeClient;
import com.lakhalifi.GestionDeStock.repository.ArticleRepository;
import com.lakhalifi.GestionDeStock.repository.ClientRepository;
import com.lakhalifi.GestionDeStock.repository.CommandeClientRepository;
import com.lakhalifi.GestionDeStock.repository.LigneCommandeClientRepository;
import com.lakhalifi.GestionDeStock.services.CommandeClientService;
import com.lakhalifi.GestionDeStock.validator.CommandeClientValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommandeClientServiceImpl implements CommandeClientService {

    private CommandeClientRepository commandeClientRepository;
    private LigneCommandeClientRepository ligneCommandeClientRepository;
    private ClientRepository clientRepository;
    private ArticleRepository articleRepository;

    @Autowired
    public CommandeClientServiceImpl(CommandeClientRepository commandeClientRepository,
                                     LigneCommandeClientRepository ligneCommandeClientRepository,
                                     ClientRepository clientRepository,
                                     ArticleRepository articleRepository){
        this.commandeClientRepository= commandeClientRepository;
        this.ligneCommandeClientRepository= ligneCommandeClientRepository;
        this.clientRepository= clientRepository;
        this.articleRepository= articleRepository;

    }

    @Override
    public CommandeClientDto save(CommandeClientDto dto) {

        List<String> errors= CommandeClientValidator.validate(dto);

        if(!errors.isEmpty()){
            log.error("Commande client n'est pas valide");
            throw new InvalidEntityException("La commande client n'est pas valide", ErrorCodes.COMMANDE_CLIENT_NOT_VALID, errors);
        }

        Optional<Client> client= clientRepository.findById(dto.getClient().getId());
        if(client.isEmpty()){
            log.warn("Client with ID {} was not found in the DB", dto.getClient().getId());
            throw new EntityNotFoundException("Aucun client avec l'ID "+dto.getClient().getId()+" n'a ete trouve dans la BDD", ErrorCodes.CLIENT_NOT_FOUND);
        }

        List<String> articleErrors= new ArrayList<>();

        if(dto.getLigneCommandeClients() != null){//test pas bloquant

            dto.getLigneCommandeClients().forEach(ligneCmdClt -> {//Ajout de validation de ligneCmdClt ici ?
                if(ligneCmdClt.getArticle() != null){
                    Optional<Article> article= articleRepository.findById(ligneCmdClt.getArticle().getId());
                    if(article.isEmpty()){
                        articleErrors.add("L'article avec l'ID "+ligneCmdClt+" n'existe pas");
                    }
                }else{
                    articleErrors.add("Impossible d'enregistrer une commande avec un article NULL");
                }
            });

        }

        if (!articleErrors.isEmpty()){//test bloquant
            log.warn("");
            throw new InvalidEntityException("Article n'existe pas dans la BDD", ErrorCodes.ARTICLE_NOT_FOUND, articleErrors);
        }
        //savedCmdClt: saved command client
        CommandeClient savedCmdClt= commandeClientRepository.save(CommandeClientDto.toEntity(dto));

        if(dto.getLigneCommandeClients() != null){
            dto.getLigneCommandeClients().forEach(ligCmdClt -> {
                LigneCommandeClient ligneCommandeClient= LigneCommandeClientDto.toEntity(ligCmdClt);
                ligneCommandeClient.setCommandeClient(savedCmdClt);
                ligneCommandeClientRepository.save(ligneCommandeClient);
            });
        }

        return CommandeClientDto.fromEntity(savedCmdClt);
    }

    @Override
    public CommandeClientDto findById(Integer id) {
        if(id == null){
            log.error("Commande client ID is NULL");
            return null;
        }

        return commandeClientRepository.findById(id)
                .map(CommandeClientDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Aucune commande client n'a ete trouve avec l'ID "+id, ErrorCodes.COMMANDE_CLIENT_NOT_FOUND
                ));
    }

    @Override
    public CommandeClientDto findByCode(String code) {
        if(!StringUtils.hasLength(code)){
            log.error("Commande client CODE is NULL");
            return null;
        }
        return commandeClientRepository.findCommandeClientByCode(code)
                .map(CommandeClientDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Aucune commande client n'a ete trouve avec le CODE "+code, ErrorCodes.COMMANDE_CLIENT_NOT_FOUND
                ));
    }

    @Override
    public List<CommandeClientDto> findAll() {
        return commandeClientRepository.findAll().stream()
                .map(CommandeClientDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {

        if(id == null){
            log.error("Commande client ID is NULL");
            return;
        }

        commandeClientRepository.deleteById(id);

    }
}
