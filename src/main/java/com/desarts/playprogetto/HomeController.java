
package com.desarts.playprogetto;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class HomeController {

    private Stage stage;
    private static Utente utenteCorrente;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private Button cosa_stampa;

    @FXML
    private Label usernameLabel;

    @FXML
    private Button completa;

    @FXML
    private Button quiz;

    @FXML
    private  ProgressBar progressBar1;

    @FXML
    private  ProgressBar progressBar2;

    @FXML
    private  ProgressBar progressBar3;

    @FXML
    private  ProgressBar progressBar4;

    @FXML
    public void initialize() throws IOException, NoSuchAlgorithmException {
        utenteCorrente = GestoreUtenti.getUtenteCorrente();
        handleUsernameLabel(); // Aggiorna il nome utente nella label
        updateProgressBars();
        GestoreUtenti.aggiornaPunteggioUtente(utenteCorrente.getNomeUtente());
    }





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

    @FXML
    public void handleUsernameLabel() {
        //Scrive il nome utente nella schermata home
        if (utenteCorrente != null && usernameLabel != null) {
            String nomeUtenteCorrente = utenteCorrente.getNomeUtente();
            usernameLabel.setText(nomeUtenteCorrente);
        }
    }

    // Carica la finestra di selezione della difficoltà
    private void loadDifficultySelection(String exerciseType) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("difficolta_cs.fxml"));
        Parent root = loader.load();

        DifficultyController controller = loader.getController();
        controller.setExerciseType(exerciseType);
        controller.setStage(stage); // Impostazione dello Stage nel controller

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Seleziona Difficoltà");
        stage.show();
    }

    public void handleImpostazioniAction(ActionEvent actionEvent) {
        MainProgettoPlay.showImpostazioniScene();
    }

    public void handleExitAction(ActionEvent actionEvent) {
        ImpostazioniController.showLogoutAlert();
    }

    public void handleIndietroAction(ActionEvent actionEvent) {
        MainProgettoPlay.showWelcomeScene();
    }

    public void updateProgressBars() {
        if (utenteCorrente != null) {

            // Aggiorna la progress bar per ogni gruppo di esercizi
            updateSingleProgressBar(progressBar1, new String[]{"A1", "A2", "A3"}, utenteCorrente);
            updateSingleProgressBar(progressBar2, new String[]{"B1", "B2", "B3"}, utenteCorrente);
            updateSingleProgressBar(progressBar3, new String[]{"C1", "C2", "C3"}, utenteCorrente);
            updateSingleProgressBar(progressBar4, new String[]{"D1", "D2", "D3"}, utenteCorrente);
        }
    }

    // Metodo ausiliario per aggiornare una singola progress bar
    private void updateSingleProgressBar(ProgressBar progressBar, String[] codiciEsercizi, Utente utente) {
        Platform.runLater(() -> {
            int eserciziCompletati = 0;

            for (String codiceEsercizio : codiciEsercizi) {
                if (utente.getPunteggioSingolo(codiceEsercizio) >= 300) {
                    eserciziCompletati++;
                }
            }
            double progress = (double)eserciziCompletati / 3.0;//aumenta in proporzione agli esercizi completati
            progressBar.setProgress(progress);
        });
    }


}
