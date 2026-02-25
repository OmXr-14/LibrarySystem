package com.librarysystem.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.librarysystem.backend.model.Libro;

import com.librarysystem.backend.repository.LibroRepository;

@RestController
@RequestMapping("/api/libri")
public class LibroController {


    @Autowired
    private LibroRepository libroRepository;

    @GetMapping //Recupera tutti i libri
    public List<Libro> getTuttiLibri(){
        return libroRepository.findAll();
    }

    @GetMapping("/disponibili")
    public List<Libro> getLibriDisponibili(){
        return libroRepository.findByDisponibileTrue();
    }

    @PostMapping
    public Libro aggiungLibro(@RequestBody Libro libro){
        libro.setDisponibile(true);
        return libroRepository.save(libro);
    }
    
}
