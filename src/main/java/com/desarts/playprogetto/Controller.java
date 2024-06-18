package com.desarts.playprogetto;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Controller {

    @FXML
    private Button loginButton;

    @FXML
    private Button classificaButton;

    @FXML
    private Button chiudiButton;

    @FXML
    private Button iniziaButton;

    @FXML
    private Label statusLabel;

    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        statusLabel.setText("Hai cliccato su Login!");
        // Aggiungi qui la logica per gestire l'azione del pulsante Login
    }

    @FXML
    private void handleClassificaButtonAction(ActionEvent event) {
        statusLabel.setText("Hai cliccato su Classifica!");
        // Aggiungi qui la logica per gestire l'azione del pulsante Classifica
    }

    @FXML
    private void handleChiudiButtonAction(ActionEvent event) {
        statusLabel.setText("Hai cliccato su Chiudi Applicazione!");
        // Aggiungi qui la logica per gestire l'azione del pulsante Chiudi Applicazione
        System.exit(0); // Esempio di chiusura dell'applicazione
    }

    @FXML
    private void handleIniziaButtonAction(ActionEvent event) {
        statusLabel.setText("Hai cliccato su Inizia!");
        // Aggiungi qui la logica per gestire l'azione del pulsante Inizia
    }
}
