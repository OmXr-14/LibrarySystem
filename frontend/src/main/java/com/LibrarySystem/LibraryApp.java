package com.LibrarySystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class LibraryApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Cerca il file fxml nella cartella resources
        URL fxmlLocation = getClass().getResource("/libreria.fxml");
        
        if (fxmlLocation == null) {
            System.err.println("Errore: impossibile trovare il file libreria.fxml");
            System.exit(1);
        }

        // Carica l'interfaccia
        Parent root = FXMLLoader.load(fxmlLocation);

        // Imposta e mostra la scena
        Scene scene = new Scene(root, 500, 400);
        primaryStage.setTitle("Gestione Biblioteca con FXML");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}