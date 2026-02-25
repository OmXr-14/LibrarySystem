package com.LibrarySystem;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class LibraryController {

    // L'istanza della nostra biblioteca
    private Bibblioteca biblioteca;

    // --- Collegamenti con l'interfaccia FXML (devono corrispondere agli fx:id) ---
    @FXML
    private ListView<String> listViewLibri;

    @FXML
    private TextField inputIdUtente;

    @FXML
    private TextField inputTitoloLibro;

    // Questo metodo viene chiamato automaticamente da JavaFX dopo aver caricato l'FXML
    @FXML
    public void initialize() {
        // Inizializziamo i dati fittizi
        biblioteca = new Bibblioteca();
        biblioteca.aggiungereLibro(new Libro("Il Signore degli Anelli", "Tolkien", 1954, true));
        biblioteca.aggiungereLibro(new Libro("1984", "Orwell", 1949, true));
        biblioteca.registraUtente(new Utente("Mario Rossi", 1));

        aggiornaListaLibri();
    }

    // --- Metodi collegati ai bottoni (devono corrispondere a onAction="#nomeMetodo") ---
    
    @FXML
    public void gestisciPrestito() {
        try {
            int id = Integer.parseInt(inputIdUtente.getText());
            String titolo = inputTitoloLibro.getText();
            
            Libro prestato = biblioteca.prestitoLibro(id, titolo);
            
            if (prestato != null) {
                mostraMessaggio("Successo", "Libro '" + titolo + "' prestato con successo!");
            } else {
                mostraMessaggio("Errore", "Impossibile prestare il libro. Controlla i dati o la disponibilità.");
            }
            
            aggiornaListaLibri();
            pulisciCampi();
            
        } catch (NumberFormatException e) {
            mostraMessaggio("Errore di input", "L'ID Utente deve essere un numero valido.");
        }
    }

    @FXML
    public void gestisciRestituzione() {
        try {
            int id = Integer.parseInt(inputIdUtente.getText());
            String titolo = inputTitoloLibro.getText();
            
            // Attenzione: attualmente restituzioneLibro è void e stampa solo in console.
            // Sarebbe ideale fargli restituire un boolean per sapere se è andata a buon fine!
            biblioteca.restituzioneLibro(id, titolo);
            
            mostraMessaggio("Operazione inviata", "Restituzione elaborata per '" + titolo + "'.");
            aggiornaListaLibri();
            pulisciCampi();
            
        } catch (NumberFormatException e) {
            mostraMessaggio("Errore di input", "L'ID Utente deve essere un numero valido.");
        }
    }

    // --- Metodi di supporto (non visibili all'FXML) ---
    
    private void aggiornaListaLibri() {
        listViewLibri.getItems().clear();
        for (Libro l : biblioteca.libriDisponibili()) {
            listViewLibri.getItems().add(l.toString());
        }
    }

    private void pulisciCampi() {
        inputIdUtente.clear();
        inputTitoloLibro.clear();
    }

    private void mostraMessaggio(String titolo, String contenuto) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titolo);
        alert.setHeaderText(null);
        alert.setContentText(contenuto);
        alert.showAndWait();
    }
}