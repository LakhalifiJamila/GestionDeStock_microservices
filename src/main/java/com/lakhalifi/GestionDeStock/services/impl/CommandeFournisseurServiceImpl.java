package com.lakhalifi.GestionDeStock.services.impl;

import com.lakhalifi.GestionDeStock.dto.CommandeFournisseurDto;
import com.lakhalifi.GestionDeStock.dto.LigneCommandeFournisseurDto;
import com.lakhalifi.GestionDeStock.exception.EntityNotFoundException;
import com.lakhalifi.GestionDeStock.exception.ErrorCodes;
import com.lakhalifi.GestionDeStock.exception.InvalidEntityException;
import com.lakhalifi.GestionDeStock.model.Article;
import com.lakhalifi.GestionDeStock.model.CommandeFournisseur;
import com.lakhalifi.GestionDeStock.model.Fournisseur;
import com.lakhalifi.GestionDeStock.model.LigneCommandeFournisseur;
import com.lakhalifi.GestionDeStock.repository.ArticleRepository;
import com.lakhalifi.GestionDeStock.repository.CommandeFournisseurRepository;
import com.lakhalifi.GestionDeStock.repository.FournisseurRepository;
import com.lakhalifi.GestionDeStock.repository.LigneCommandeFournisseurRepository;
import com.lakhalifi.GestionDeStock.services.CommandeFournisseurService;
import com.lakhalifi.GestionDeStock.validator.CommandeFournisseurValidator;
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
public class CommandeFournisseurServiceImpl implements CommandeFournisseurService {

    private CommandeFournisseurRepository commandeFournisseurRepository;
    private LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository;
    private FournisseurRepository fournisseurRepository;
    private ArticleRepository articleRepository;

    @Autowired
    public CommandeFournisseurServiceImpl(CommandeFournisseurRepository commandeFournisseurRepository,
                                          LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository,
                                          FournisseurRepository fournisseurRepository, ArticleRepository articleRepository) {
        this.commandeFournisseurRepository = commandeFournisseurRepository;
        this.ligneCommandeFournisseurRepository = ligneCommandeFournisseurRepository;
        this.fournisseurRepository = fournisseurRepository;
        this.articleRepository = articleRepository;
    }

    @Override
    public CommandeFournisseurDto save(CommandeFournisseurDto dto) {

        List<String> errors= CommandeFournisseurValidator.validate(dto);

        if(!errors.isEmpty()){
            log.error("Commande fournisseur n'est pas valide");
            throw new InvalidEntityException("La commande fournisseur n'est pas valide", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_VALID, errors);
        }

        Optional<Fournisseur> fournisseur= fournisseurRepository.findById(dto.getFournisseur().getId());
        if(fournisseur.isEmpty()){
            log.warn("Fournisseur with ID {} was not found in the DB", dto.getFournisseur().getId());
            throw new EntityNotFoundException("Aucun fournisseur avec l'ID "+dto.getFournisseur().getId()+" n'a ete trouve dans la BDD", ErrorCodes.FOURNISSEUR_NOT_FOUND);
        }

        List<String> articleErrors= new ArrayList<>();

        if(dto.getLigneCommandeFournisseurs() != null){
            dto.getLigneCommandeFournisseurs().forEach(ligCmdFrs -> {
                if(ligCmdFrs.getArticle() != null){
                    Optional<Article> article= articleRepository.findById(ligCmdFrs.getArticle().getId());
                    if(article.isEmpty()){
                        articleErrors.add("L'article avec l'ID "+ligCmdFrs.getArticle().getId()+" n'existe pas");
                    }
                }else {
                    articleErrors.add("Impossible d'enregistrer une commande avec un article NULL");
                }
            });
        }

        if(!articleErrors.isEmpty()){
            log.warn("");
            throw new InvalidEntityException("Article n'existe pas dans la BDD", ErrorCodes.ARTICLE_NOT_FOUND, articleErrors);
        }
        CommandeFournisseur savedCmdFrs= commandeFournisseurRepository.save(CommandeFournisseurDto.toEntity(dto));

        if(dto.getLigneCommandeFournisseurs() != null){
            dto.getLigneCommandeFournisseurs().forEach(ligCmdFrs -> {
                LigneCommandeFournisseur ligneCommandeFournisseur= LigneCommandeFournisseurDto.toEntity(ligCmdFrs);
                ligneCommandeFournisseur.setCommandeFournisseur(savedCmdFrs);
                ligneCommandeFournisseurRepository.save(ligneCommandeFournisseur);
            });
        }

        return CommandeFournisseurDto.fromEntity(savedCmdFrs);
    }

    @Override
    public CommandeFournisseurDto findById(Integer id) {
        if(id == null){
            log.error("Commande fournisseur ID is NULL");
            return null;
        }

        return commandeFournisseurRepository.findById(id)
                .map(CommandeFournisseurDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Aucune commande fournisseur n'a ete trouve avec l'ID "+id, ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND));
    }

    @Override
    public CommandeFournisseurDto findByCode(String code) {
        if(!StringUtils.hasLength(code)){
            log.error("Commande fournisseur CODE is NULL");
            return null;
        }

        return commandeFournisseurRepository.findCommandeFournisseurByCode(code)
                .map(CommandeFournisseurDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune commande fournisseur n'a ete trouve avec le CODE "+code, ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND
                ));
    }

    @Override
    public List<CommandeFournisseurDto> findAll() {
        return commandeFournisseurRepository.findAll().stream()
                .map(CommandeFournisseurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if(id == null){
            log.error("Commande fournisseur ID is NULL");
            return;
        }
        commandeFournisseurRepository.deleteById(id);
    }
}
