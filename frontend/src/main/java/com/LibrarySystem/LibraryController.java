package com.LibrarySystem;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import java.util.*;

public class LibraryController{

    @FXML private ListView<String> listViewLibri;
    @FXML private ComboBox<Utente> comboUtenti;
    @FXML private TextField inputIdLibro;

    @FXML
    public void initialize(){
        aggiornaListaLibri();

        try{
            comboUtenti.getItems().addAll(ApiClient.getTuttiUtenti());
        }catch(Exception e){
            System.err.println("Impossibile caricare gli utenti: " + e.getMessage());
        }

        listViewLibri.getSelectionModel().selectedItemProperty().addListener((observable, odlValue, newValue)->{
            if(newValue!=null){
                try{
                int indiceInzio = newValue.indexOf("[") +1;
                int indiceFine = newValue.indexOf("]");

                if(indiceInzio > 0 && indiceFine > indiceInzio){
                    String idEstratto = newValue.substring(indiceInzio,indiceFine);

                    inputIdLibro.setText(idEstratto);
                }
            }catch(Exception e ){
                System.err.println("Errore durante l'estrazione dell'ID: " + e.getMessage());
            }
            }
        });
    }

    @FXML
    public void gestisciPrestito(){

        Utente utenteselezionato = comboUtenti.getValue();
        String idLibro = inputIdLibro.getText().trim();

        if(utenteselezionato == null || idLibro.isEmpty()){
            mostraMessaggio("Errore", "Inserisci sia ID utente che ID Libro");
            return;
        }
        if (utenteselezionato.getIdUtente() == null) {
            mostraMessaggio("Errore Critico", "L'ID dell'utente è NULL! Gson non lo sta leggendo bene.");
            return;
        }

        try{
            String risultato = ApiClient.prestaLibro(utenteselezionato.getIdUtente().trim(), idLibro);
            mostraMessaggio("Esito Prestito", risultato);

            aggiornaListaLibri();

        }catch(Exception e){
            mostraMessaggio("Errore dal Server", e.getMessage());
        }
    }



    private void aggiornaListaLibri(){
        try{
            List<Libro> libriDalServer = ApiClient.getTuttiLibri();
            listViewLibri.getItems().clear();

            for(Libro l : libriDalServer){
                listViewLibri.getItems().add(l.toString());
            }
        }catch(Exception e ){
            mostraMessaggio("Errore di connessione","Impossibile caricare i libri");
        }
    }

   private void mostraMessaggio(String titolo, String contenuto) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titolo);
        alert.setHeaderText(null);
        alert.setContentText(contenuto);
        alert.showAndWait();
    }
}