package com.librarysystem.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "libri")
public class Libro {

    @Id
    private String id;

    private String titolo;
    private String autore;
    private int annoPubblicazione;
    private Boolean disponibile;


    public Libro(){}

    public Libro(String titolo, String autore, int annoPubblicazione, Boolean disponibile){
        this.titolo = titolo;
        this.autore = autore;
        this.annoPubblicazione = annoPubblicazione;
        this.disponibile = disponibile;
    }


    //Getter

    public String getId(){ return id;}
    public String getTitolo(){ return titolo;}
    public String getAutore(){return autore;}
    public int getAnnoPubblicazione(){return annoPubblicazione;}
    public Boolean getDisponibile(){return disponibile;}

    //Setter

    public void setId(String id){ this.id = id;}
    public void setTitolo(String titolo){ this.titolo=titolo;}
    public void setAutore(String autore){this.autore = autore;}
    public void setAnnoPubbilcazione(int annoPubblicazione){this.annoPubblicazione = annoPubblicazione;}
    public void setDisponibile(Boolean disponibile){this.disponibile = disponibile;}


    


}
