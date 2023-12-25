package com.lakhalifi.GestionDeStock.controller;

import com.lakhalifi.GestionDeStock.controller.api.ArticleApi;
import com.lakhalifi.GestionDeStock.dto.ArticleDto;
import com.lakhalifi.GestionDeStock.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArticleController implements ArticleApi {

    private ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService){
        this.articleService= articleService;
    }

    @Override
    public ArticleDto save(ArticleDto dto) {
        System.out.println("=============================");
        return articleService.save(dto);
    }

    @Override
    public ArticleDto findById(Integer id) {
        return articleService.findById(id);
    }

    @Override
    public ArticleDto findByCodeArticle(String codeArticle) {
        return articleService.findByCodeArticle(codeArticle);
    }

    @Override
    public List<ArticleDto> findAll() {
        return articleService.findAll();
    }

    @Override
    public void delete(Integer id) {
        articleService.delete(id);
    }
}
