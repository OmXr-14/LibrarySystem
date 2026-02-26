package com.LibrarySystem;

public class Libro{
    private String id;
    private String titolo;
    private String autore;
    private int annoPubblciazione;
    private Boolean disponibile;

    public Libro(String titolo, String autore, int annoPubblciazione, boolean disponibile){
        this.titolo = titolo;
        this.autore = autore;
        this.annoPubblciazione = annoPubblciazione;
        this.disponibile = true;
    }

    //Getter
    public String getId(){return id;}
    public String getTitolo(){return titolo;}
    public String getAutore(){return autore;}
    public int getAnnoPubblicazione(){return annoPubblciazione;}
    public boolean isDisponibile(){return disponibile;}

    //Setter
    public void setDisponibilita(boolean disponibilità){this.disponibile = disponibilità;}
    public void setId(String id){this.id = id;}


    //ToString per stampare dettagli Libro

    @Override
    public String toString(){
        return "[ID: " + id +" ]" + titolo + " di " + autore + " pubblicato il " + annoPubblciazione + (disponibile ? " [Disponibile] " : "[Non disponibile]"  );
    }
    
}