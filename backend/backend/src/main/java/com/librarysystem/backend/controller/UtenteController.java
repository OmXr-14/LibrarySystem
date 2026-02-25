package com.librarysystem.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.librarysystem.backend.repository.UtenteRepository;
import com.librarysystem.backend.model.*;

import java.util.*;

@RestController
@RequestMapping("/api/utenti")
public class UtenteController {


    @Autowired
    private UtenteRepository utenteRepository;

    @GetMapping //recupera tutti gli utenti
    public List<Utente> getTuttiUtenti(){
        return utenteRepository.findAll();
    }
    
    @PostMapping
    public Utente registraUtente(@RequestBody Utente utente){
        return utenteRepository.save(utente);
    }
}
