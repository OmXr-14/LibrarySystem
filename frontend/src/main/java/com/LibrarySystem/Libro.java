package com.LibrarySystem;

public class Libro{

    private String titolo;
    private String autore;
    private int annoPubblciazione;
    private Boolean disponibilità;

    public Libro(String titolo, String autore, int annoPubblciazione, boolean disponibilità){
        this.titolo = titolo;
        this.autore = autore;
        this.annoPubblciazione = annoPubblciazione;
        this.disponibilità = true;
    }

    //Getter
    public String getTitolo(){return titolo;}
    public String getAutore(){return autore;}
    public int getAnnoPubblicazione(){return annoPubblciazione;}
    public boolean isDisponibile(){return disponibilità;}

    //Setter
    public void setDisponibilita(boolean disponibilità){this.disponibilità = disponibilità;}


    //ToString per stampare dettagli Libro

    @Override
    public String toString(){
        return titolo + "di" + autore + "pubblicato il" + annoPubblciazione + (disponibilità ? "[Disponibile]" : "[Non disponibile]");
    }
    
}