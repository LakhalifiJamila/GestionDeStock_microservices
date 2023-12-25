package com.lakhalifi.GestionDeStock.dto.auth;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class AuthenticationRequest {
    private String login;
    private String password;

    @JsonCreator
    public AuthenticationRequest(@JsonProperty("login") String login, @JsonProperty("password") String password) {
        this.login = login;
        this.password = password;
    }
}
