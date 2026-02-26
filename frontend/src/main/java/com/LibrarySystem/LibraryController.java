package com.LibrarySystem;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import java.util.*;

public class LibraryController{

    @FXML private ListView<String> listViewLibriD;
    @FXML private ListView<String> listViewLibriN;
    @FXML private ComboBox<Utente> comboUtenti;
    @FXML private ComboBox<Libro> comboLibri;

    @FXML
    public void initialize(){
        aggiornaListaLibri();

        try{
            comboUtenti.getItems().addAll(ApiClient.getTuttiUtenti());
        }catch(Exception e){
            System.err.println("Impossibile caricare gli utenti: " + e.getMessage());
        }
    }

    @FXML
    public void gestisciPrestito(){

        Utente utenteselezionato = comboUtenti.getValue();
        Libro libroSelezionato = comboLibri.getValue();

        if(utenteselezionato == null || comboLibri == null){
            mostraMessaggio("Errore", "Inserisci l'Utente che il Libro");
            return;
        }

        try{
            String risultato = ApiClient.prestaLibro(utenteselezionato.getId().trim(), libroSelezionato.getId().trim());
            mostraMessaggio("Esito Prestito", risultato);

            aggiornaListaLibri();

        }catch(Exception e){
            mostraMessaggio("Errore dal Server", e.getMessage());
        }
    }


    @FXML
    public void gestisciRestituzione(){
        Utente utenteSelezionato = comboUtenti.getValue();
        Libro libroSelezionato = comboLibri.getValue();

          if(utenteSelezionato == null || comboLibri == null){
            mostraMessaggio("Errore", "Inserisci l'Utente che il Libro");
            return;
        }

        try{
            String risultato = ApiClient.restituisciLibro(utenteSelezionato.getId().trim(), libroSelezionato.getId().trim());
            mostraMessaggio("Esito Restituzione", risultato);

            aggiornaListaLibri();
        }catch(Exception e){
            mostraMessaggio("Errore dal Server", e.getMessage());
        }

        
    }



    private void aggiornaListaLibri(){
        try{
            List<Libro> libriDalServer = ApiClient.getTuttiLibri();
            listViewLibriD.getItems().clear();
            listViewLibriN.getItems().clear();

            comboLibri.getItems().clear();
            comboLibri.getItems().addAll(libriDalServer);

            for(Libro l : libriDalServer){
                if(l.isDisponibile()){
                listViewLibriD.getItems().add(l.toString());
                }else{
                    listViewLibriN.getItems().add(l.toString());
                }
            }
        }catch(Exception e ){
            mostraMessaggio("Errore", "Impossibile caricare i libri: " + e.getMessage());
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