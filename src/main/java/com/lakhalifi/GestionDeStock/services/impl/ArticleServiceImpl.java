package com.lakhalifi.GestionDeStock.services.impl;

import com.lakhalifi.GestionDeStock.dto.ArticleDto;
import com.lakhalifi.GestionDeStock.exception.EntityNotFoundException;
import com.lakhalifi.GestionDeStock.exception.ErrorCodes;
import com.lakhalifi.GestionDeStock.exception.InvalidEntityException;
import com.lakhalifi.GestionDeStock.model.Article;
import com.lakhalifi.GestionDeStock.repository.ArticleRepository;
import com.lakhalifi.GestionDeStock.services.ArticleService;
import com.lakhalifi.GestionDeStock.validator.ArticleValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    private ArticleRepository articleRepository;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository)
    {
        this.articleRepository= articleRepository;
    }

    @Override
    public ArticleDto save(ArticleDto dto)
    {
        List<String> errors= ArticleValidator.validate(dto);
        if(!errors.isEmpty()){
            log.error("Article is not valid {]", dto);
            throw new InvalidEntityException("L'article n'est pas valide", ErrorCodes.ARTICLE_NOT_VALID, errors);
        }

        //the save method in a Spring Data JPA JpaRepository returns the saved entity.
        return ArticleDto.fromEntity(articleRepository.save(
                ArticleDto.toEntity(dto)
        ));
    }

    @Override
    public ArticleDto findById(Integer id) {
        if(id == null){
            log.error("Article ID is null");
            return null;
        }

        /*
        By using Optional<Article>, you are explicitly indicating that
        the result of the findById operation may or may not yield an Article.
        This helps you handle cases where the Article is not found without causing
        runtime errors, like NullPointerException. It encourages better error handling
        and forces you to deal with the absence of a value in a more explicit manner.
        */
        Optional<Article> article= articleRepository.findById(id);

        /* ATT: article is with Optional<Article> type and get() method retrieves the value if it's present */
        //Optional.of(...): This part wraps the result of the conversion (ArticleDto) in a new Optional. So, you have an Optional<ArticleDto> now.
        /*
        .orElseThrow(...): This is a method call on the Optional. If the Optional is not empty, it returns
        the contained value. However, if the Optional is empty, it throws an exception. In this case,
        the exception thrown is an EntityNotFoundException with a specific error message and an error code.
        This indicates that if there is no Article found, an EntityNotFoundException is thrown with a custom error message and code (ErrorCodes.ARTICLE_NOT_FOUND).
         */
        return Optional.of(ArticleDto.fromEntity(article.get())).orElseThrow(() ->
                new EntityNotFoundException(
                    "Aucun article avec l'ID = " + id + " n'a ete trouve dans la BDD",
                    ErrorCodes.ARTICLE_NOT_FOUND)
        );
    }

    @Override
    public ArticleDto findByCodeArticle(String codeArticle) {

        if(!StringUtils.hasLength(codeArticle)){
            log.error("Article CODE is null");
            return null;
        }
        Optional<Article> article= articleRepository.findArticleByCodeArticle(codeArticle);

        return Optional.of(ArticleDto.fromEntity(article.get())).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun article avec le CODE = " + codeArticle + " n'a ete pas trouve dans la BDD",
                        ErrorCodes.ARTICLE_NOT_FOUND)
        );
    }

    @Override
    public List<ArticleDto> findAll() {
        return articleRepository.findAll().stream()
                .map(ArticleDto::fromEntity)
                .collect(Collectors.toList());
    }
    /*
    The .stream() method is called on the list of articles. It transforms
    the list into a Stream of Article objects. A Stream is a sequence of
    elements that can be processed sequentially or in parallel.
    */
    /*
    .map(ArticleDto::fromEntity): The .map() operation is used to transform each
    Article object in the stream into an ArticleDto object. It does this by calling
    a method named fromEntity on the ArticleDto class for each Article in the stream.
    This operation essentially converts the Article objects to their corresponding ArticleDto representations.
    */
    /*
    .collect(Collectors.toList()): Finally, the .collect() operation gathers the
    transformed ArticleDto objects into a list. This list is what the method returns.
    */

    @Override
    public void delete(Integer id) {

        if(id == null){
            log.error("Article ID is null");
            return;
        }
        articleRepository.deleteById(id);
    }
}
