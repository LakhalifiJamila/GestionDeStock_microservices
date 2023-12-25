package com.lakhalifi.GestionDeStock.services.auth;

import com.lakhalifi.GestionDeStock.exception.EntityNotFoundException;
import com.lakhalifi.GestionDeStock.exception.ErrorCodes;
import com.lakhalifi.GestionDeStock.model.Utilisateur;
import com.lakhalifi.GestionDeStock.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {

    @Autowired
    private UtilisateurRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Utilisateur utilisateur= repository.findUtilisateurByEmail(email).orElseThrow(() ->
                new EntityNotFoundException("Aucun utilisateur avec l'email fourni", ErrorCodes.UTILISATEUR_NOT_FOUND)
        );
        //System.out.println("==========================");
        return new User(utilisateur.getEmail(),utilisateur.getMotDePasse(), Collections.emptyList());
    }
}
