
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

public class HomeController {

    private Stage stage;
    private Utente utenteCorrente;

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
    public void initialize() {
        utenteCorrente = GestoreUtenti.getUtenteCorrente();
        if (utenteCorrente != null) {
            handleUsernameLabel();
            updateProgressBars();
        }
    }

    @FXML
    private void handleCompletaFraseAction() throws IOException {
        loadDifficultySelection("CompletaFrase");
    }

    @FXML
    private void handleCosaStampaAction() throws IOException {
        loadDifficultySelection("CosaStampa");
    }

    @FXML
    private void handleTrovaErroreAction() throws IOException {
        loadDifficultySelection("TrovaErrore");
    }

    @FXML
    private void handleQuizAction() throws IOException {
        loadDifficultySelection("QuizApp");
    }

    @FXML
    public void handleUsernameLabel() {
        Utente utenteCorrente = GestoreUtenti.getUtenteCorrente();
        if (utenteCorrente != null && usernameLabel != null) {
            String nomeUtenteCorrente = utenteCorrente.getNomeUtente();
            usernameLabel.setText(nomeUtenteCorrente);
        }
    }

    private void loadDifficultySelection(String exerciseType) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("difficolta_cs.fxml"));
        Parent root = loader.load();

        DifficultyController controller = loader.getController();
        controller.setExerciseType(exerciseType);
        controller.setStage(stage); 

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

            updateSingleProgressBar(progressBar1, new String[]{"A1", "A2", "A3"}, utenteCorrente);
            updateSingleProgressBar(progressBar2, new String[]{"B1", "B2", "B3"}, utenteCorrente);
            updateSingleProgressBar(progressBar3, new String[]{"C1", "C2", "C3"}, utenteCorrente);
            updateSingleProgressBar(progressBar4, new String[]{"D1", "D2", "D3"}, utenteCorrente);
        }
    }

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
