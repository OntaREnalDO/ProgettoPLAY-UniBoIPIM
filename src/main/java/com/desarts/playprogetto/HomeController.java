
package com.desarts.playprogetto;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;

public class HomeController {

    @FXML
    private Button cosa_stampa;

    @FXML
    private Button debugging;

    @FXML
    private Button completa;

    @FXML
    private Button quiz;

    @FXML
    private ProgressBar progressBar1;

    @FXML
    private ProgressBar progressBar2;

    @FXML
    private ProgressBar progressBar3;

    @FXML
    private ProgressBar progressBar4;

    // Metodo per gestire il click sul bottone COSA STAMPA
    @FXML
    private void handleCosaStampaAction() {
        MainProgettoPlay.showDifficultyScene();
    }

    // Metodo per gestire il click sul bottone DEBUGGING
    @FXML
    private void handleDebuggingAction() {
        // Aggiungi la logica qui
    }

    // Metodo per gestire il click sul bottone COMPLETA
    @FXML
    private void handleCompletaAction() {
        // Aggiungi la logica qui
    }

    // Metodo per gestire il click sul bottone QUIZ
    @FXML
    private void handleQuizAction() {
        // Aggiungi la logica qui
    }

    // Inizializza i componenti, se necessario
    @FXML
    public void initialize() {
        // Configura i componenti all'avvio, se necessario
    }

    public void handleImpostazioniAction(ActionEvent actionEvent) {MainProgettoPlay.showImpostazioniScene();    }

    public void handleDifficoltaAction(ActionEvent actionEvent) {
    }
}
