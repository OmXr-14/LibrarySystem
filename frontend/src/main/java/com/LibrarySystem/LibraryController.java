package com.LibrarySystem;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import java.util.*;

public class LibraryController{

    @FXML private ListView<String> listViewLibri;
    @FXML private TextField inputIdUtente;
    @FXML private TextField inputIdLibro;

    @FXML
    public void initialize(){
        aggiornaListaLibri();
    }

    @FXML
    public void gestisciPrestito(){

        String idUtente = inputIdUtente.getText();
        String idLibro = inputIdLibro.getText();

        if(idUtente.isEmpty() || idLibro.isEmpty()){
            mostraMessaggio("Errore", "Inserisci sia ID utente che ID Libro");
            return;
        }

        try{
            String risultato = ApiClient.prestaLibro(idUtente, idLibro);
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