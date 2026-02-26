package com.librarysystem.backend.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Document(collection = "utenti")
public class Utente {

    @Id
    private String id;

    private String name;

    @DocumentReference
    private List<Libro> libriPrestito = new ArrayList<>();

    public Utente(){};

    public Utente (String name, String id){
        this.name= name;
        this.id = id;
    }



    //Getter
    public String getId(){return id;}
    public String getName(){return name;}
    public List<Libro> getLibriPrestito(){ return libriPrestito;}

    //Setter
    public void setId(String id){this.id = id;}
    public void setName(String name){this.name = name;}
    public void setLibriPrestito(List<Libro> libriPrestito){this.libriPrestito = libriPrestito;}


    //Metodi per i prestiti

    public void prendiInPrestito(Libro libro){
        this.libriPrestito.add(libro);
    }

    public void restituisciLibro(Libro libro){
        
        this.libriPrestito.removeIf(l->l.getId().equals(libro.getId()));
    }
}


