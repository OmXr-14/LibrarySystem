package com.LibrarySystem;
import java.util.*;

public class Bibblioteca {
    
    private List<Libro> catalogo;
    private List<Utente> utenti;

    public Bibblioteca(){
        this.catalogo = new ArrayList<>();
        this.utenti = new ArrayList<>();
    }

    //Aggiungere Libro
    public void aggiungereLibro(Libro libro){ catalogo.add(libro);}

    //Rimuovi Libro
    public void rimuoviLibro(Libro libro){ catalogo.remove(libro);}

    //visualizza Libri Disponibili
    public List<Libro> libriDisponibili(){
        List<Libro> libriD = new ArrayList<>();

        for(Libro l : catalogo){
            if(l.isDisponibile()){
                libriD.add(l);
            }
        }
        return libriD;
    }


    //Gestione Utenti
    public void registraUtente(Utente u){
        utenti.add(u);
    }

    //Prestiti
    public Libro prestitoLibro(String  idUtente, String titoloLibro){
        //Trova Utente
            Utente utente = null;
            for(Utente u : utenti){
                if(u.getIdUtente()==idUtente){
                    utente = u;
                    break;
                }
            }
            if(utente == null){
                System.out.println("Utente non trovato");
                return null;
            }

        //Trova Libro
            Libro libro = null;
            for(Libro l : catalogo){
                if(l.getTitolo().equalsIgnoreCase(titoloLibro)){
                    libro = l;
                    break;
                }
            }
            if(libro == null){
                System.err.println("Libro non trovato");
                return null;
            }

        if(!libro.isDisponibile()){
             System.err.println("Libro non disponibile");
             return null;
        }

        libro.setDisponibilita(false);

        utente.prendiInPrestito(libro);

        System.err.println("Prestito effettuato: " + libro.getTitolo() + " a " + utente.getName());
        
        return libro;
    }


    //Restituzione
    public void restituzioneLibro(String idUtente, String titoloLibro){

        Utente utente = null;
       for(Utente u : utenti){
                if(u.getIdUtente()==idUtente){
                    utente = u;
                    break;
                }
        }

        if (utente == null) {
             System.out.println("Utente non trovato");
             return;
        }


        List<Libro> libriUtente = utente.getLibriPrestito();
        Libro libroRestituire = null;
        for(Libro l : libriUtente){
            if(l.getTitolo().equalsIgnoreCase(titoloLibro)){
                libroRestituire = l;
                break;
            }
        }
         if (libroRestituire == null) {
             System.out.println("Libro non trovato");
             return;
        }

        libroRestituire.setDisponibilita(true);
        utente.restiusciLibro(libroRestituire);
    }

}
