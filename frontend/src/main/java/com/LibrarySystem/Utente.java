package com.LibrarySystem;
import java.util.ArrayList;
import java.util.List;

public class Utente {
    private String name;
    private String id;
    private List<Libro> libriPrestito;

    public Utente(){}

    public Utente(String name, String id){
        this.name = name;
        this.id = id;
        this.libriPrestito = new ArrayList<>();
    }

    //Getter lista di libri
    public List<Libro> getLibriPrestito(){
        return libriPrestito;
    }

    //Aggiungere un libro al prestito
    public void prendiInPrestito(Libro libro){
        libriPrestito.add(libro);
    }

    //Restituire un libro in prestito
    public void restiusciLibro(Libro libro){
        libriPrestito.remove(libro);
    }

    //Getter 
    public String getIdUtente(){return id;}
    public String getName(){return name;}



    @Override
    public String toString(){
        return name;
    }
}
