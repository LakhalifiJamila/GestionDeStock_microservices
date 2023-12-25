package com.lakhalifi.GestionDeStock.exception;

import lombok.Getter;

import java.util.List;

//Si on veut insérer ou mettre à jour une entité dans la base de donnée et cette entité a un ou plusieurs champs invalides, on va lever cette exception
public class InvalidEntityException extends RuntimeException {

    @Getter
    private ErrorCodes errorCode;

    @Getter
    private List<String> errors;

    public InvalidEntityException(String message){
        super(message);
    }

    public InvalidEntityException(String message, Throwable cause){
        super(message, cause);
    }

    public InvalidEntityException(String message, Throwable cause, ErrorCodes errorCode){
        super(message);
        this.errorCode= errorCode;
    }

    public InvalidEntityException(String message, ErrorCodes errorCode){
        super(message);
        this.errorCode= errorCode;
    }

    public InvalidEntityException(String message, ErrorCodes errorCode, List<String> errors){
        super(message);
        this.errorCode= errorCode;
        this.errors= errors;
    }

}
