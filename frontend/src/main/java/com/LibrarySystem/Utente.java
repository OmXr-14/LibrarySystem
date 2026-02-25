package com.LibrarySystem;
import java.util.ArrayList;
import java.util.List;

public class Utente {
    private String name;
    private int idUtente;
    private List<Libro> libriPrestito;


    public Utente(String name, int idUtente){
        this.name = name;
        this.idUtente = idUtente;
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
    public int getIdUtente(){return idUtente;}
    public String getName(){return name;}



    @Override
    public String toString(){
        return name + "(ID:" + idUtente + ")";
    }
}
