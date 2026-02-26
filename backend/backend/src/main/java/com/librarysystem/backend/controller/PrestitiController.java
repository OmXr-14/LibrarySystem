package com.librarysystem.backend.controller;

import com.librarysystem.backend.model.Libro;
import com.librarysystem.backend.model.Utente;
import com.librarysystem.backend.repository.LibroRepository;
import com.librarysystem.backend.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;


@RestController
@RequestMapping("/api/prestiti")
public class PrestitiController {
    

    @Autowired
    private LibroRepository libroRepository;
    
    @Autowired
    private UtenteRepository utenteRepository;


    @PostMapping("/presta")
    public ResponseEntity<String> prestaLibro(@RequestParam String idUtente, @RequestParam String idLibro){

        Optional<Utente> utenteOpt = utenteRepository.findById(idUtente);
        Optional<Libro> libroOpt = libroRepository.findById(idLibro);

        if(utenteOpt.isEmpty()){ return ResponseEntity.badRequest().body("Utente non esistente");}
        if(libroOpt.isEmpty()){ return ResponseEntity.badRequest().body("Libro non esistente");}


        Utente utente = utenteOpt.get();
        Libro libro = libroOpt.get();

        if (!libro.getDisponibile()) {
            return ResponseEntity.badRequest().body("Il libro non è attualmente disponibile.");
        }

        //Aggiorna stato del libro e lo salva
        libro.setDisponibile(false);
        libroRepository.save(libro);

        //Aggiunge il Libro al Utente e lo salva
        utente.prendiInPrestito(libro);
        utenteRepository.save(utente);

        return ResponseEntity.ok("Prestito avvenuto con successo: " + libro.getTitolo() + " a " + utente.getName());
    }


    @PostMapping("/restituisci")
    public ResponseEntity<String> restituisciLibro(@RequestParam String idUtente, @RequestParam String idLibro){

        Optional<Utente> utenteOpt = utenteRepository.findById(idUtente);
        Optional<Libro> libroOpt = libroRepository.findById(idLibro);

        if(utenteOpt.isEmpty()){ return ResponseEntity.badRequest().body("Utente non esistente");}
        if(libroOpt.isEmpty()){ return ResponseEntity.badRequest().body("Libro non esistente");}

        Utente utente = utenteOpt.get();
        Libro libro = libroOpt.get();

        if(libro.getDisponibile()){
            return ResponseEntity.badRequest().body("Impossibile restituire: Il libro è già disponibile in biblioteca.");
        }

        boolean utenteHaLibro = false;

        for(Libro l : utente.getLibriPrestito()){
            if(l.getId().equals(libro.getId())){
                utenteHaLibro = true;
                break;
            }
        }

        if(!utenteHaLibro){
            return ResponseEntity.badRequest().body("Attenzione: Questo utente non ha in prestito questo libro!");
        }

        //Aggiorna stato del libro 
        libro.setDisponibile(true);
        libroRepository.save(libro);

        //Rimuove il libro dal Utente e lo salva
        utente.restituisciLibro(libro);
        utenteRepository.save(utente);

        return ResponseEntity.ok("Restituzione completata per il libro: " +libro.getTitolo());
    }
}
