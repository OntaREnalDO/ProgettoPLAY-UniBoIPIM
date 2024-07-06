
package com.desarts.playprogetto;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

import java.io.IOException;

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
/*
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
    */

    // Gestisce il clic sul pulsante "Completa la frase"
    @FXML
    private void handleCompletaFraseAction() throws IOException {
        loadDifficultySelection("CompletaFrase");
    }

    // Gestisce il clic sul pulsante "Cosa stampa"
    @FXML
    private void handleCosaStampaAction() throws IOException {
        loadDifficultySelection("CosaStampa");
    }

    // Gestisce il clic sul pulsante "Trova l'errore"
    @FXML
    private void handleTrovaErroreAction() throws IOException {
        loadDifficultySelection("TrovaErrore");
    }

    // Gestisce il clic sul pulsante "Quiz"
    @FXML
    private void handleQuizAction() throws IOException {
        loadDifficultySelection("QuizApp");
    }

    // Carica la finestra di selezione della difficoltà
    private void loadDifficultySelection(String exerciseType) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("difficolta_cs.fxml"));
        Parent root = loader.load();

        DifficultyController controller = loader.getController();
        controller.setExerciseType(exerciseType);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Seleziona Difficoltà");
        stage.show();
    }

    public void handleImpostazioniAction(ActionEvent actionEvent) {MainProgettoPlay.showImpostazioniScene();    }

    public void handleExit(ActionEvent actionEvent) {MainProgettoPlay.showWelcomeScene();   }

    public void handleDifficoltaAction(ActionEvent actionEvent) {
    }
}
