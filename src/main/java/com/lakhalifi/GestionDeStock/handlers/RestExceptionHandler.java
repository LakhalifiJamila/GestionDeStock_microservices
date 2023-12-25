package com.lakhalifi.GestionDeStock.handlers;

/*
les exceptions représentent l'erreur elle-même, tandis
que les gestionnaires d'exceptions sont les mécanismes
mis en place pour gérer ces erreurs de manière appropriée.
*/

import com.lakhalifi.GestionDeStock.exception.EntityNotFoundException;
import com.lakhalifi.GestionDeStock.exception.InvalidEntityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/*
RestExceptionHandler est annoté avec @ControllerAdvice, ce qui indique qu'il s'agit
d'un gestionnaire d'exceptions global pour les contrôleurs de l'application.
*/

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {


    /*
    La méthode handleException est annotée avec @ExceptionHandler(EntityNotFoundException.class).
    Elle gère les exceptions de type EntityNotFoundException. Lorsqu'une
    exception de ce type est levée dans n'importe quel contrôleur, cette méthode
    sera appelée pour la gérer et renvoyer une réponse adaptée.
    */

    /*
    WebRequest is useful when you need to access request-specific data within a controller or another Spring component.
    */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDto> handleException(EntityNotFoundException exception, WebRequest webRequest){

        final HttpStatus notFound= HttpStatus.NOT_FOUND;
        final ErrorDto errorDto= ErrorDto.builder()
                .code(exception.getErrorCode())
                .httpCode(notFound.value())
                .message(exception.getMessage())
                .build();

        return new ResponseEntity<>(errorDto, notFound);
    }

    @ExceptionHandler(InvalidEntityException.class)
    public ResponseEntity<ErrorDto> handleException(InvalidEntityException exception, WebRequest webRequest){
        final HttpStatus badRequest= HttpStatus.BAD_REQUEST;

        final ErrorDto errorDto= ErrorDto.builder()
                .code(exception.getErrorCode())
                .httpCode(badRequest.value())
                .message(exception.getMessage())
                .errors(exception.getErrors())
                .build();

        return new ResponseEntity<>(errorDto, badRequest);
    }

}
