package com.desarts.playprogetto;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class DifficultyController {

    private String exerciseType;  // Variabile per memorizzare il tipo di esercizio selezionato
    private Stage stage; // Aggiunta della variabile Stage

    // Metodo per impostare il tipo di esercizio
    public void setExerciseType(String exerciseType) {
        this.exerciseType = exerciseType;
    }

    // Aggiunta di un metodo per impostare lo Stage
    public void setStage(Stage stage) {
        this.stage = stage;
    }

        @FXML
    private Button facile;

    @FXML
    private Button intermedio;

    @FXML
    private Button difficile;

    @FXML
    private Button BackButton;
/*
    // Metodo per gestire il clic sul bottone "FACILE"
    @FXML
    private void handleFacileAction() {
        // Logica per la difficoltà facile
    }

    // Metodo per gestire il clic sul bottone "INTERMEDIO"
    @FXML
    private void handleIntermedioAction() {
        // Logica per la difficoltà intermedia
    }

    // Metodo per gestire il clic sul bottone "DIFFICILE"
    @FXML
    private void handleDifficileAction() {
        // Logica per la difficoltà difficile
    }
    */
    // Gestisce il clic sul pulsante "Facile"
    @FXML
    private void handleEasy() throws IOException {
        loadExercise("easy");
    }

    // Gestisce il clic sul pulsante "Intermedio"
    @FXML
    private void handleIntermediate() throws IOException {
        loadExercise("intermediate");
    }

    // Gestisce il clic sul pulsante "Esperto"
    @FXML
    private void handleExpert() throws IOException {
        loadExercise("expert");
    }

    // Carica l'esercizio in base alla difficoltà selezionata
    private void loadExercise(String difficulty) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(exerciseType + ".fxml"));
        Parent root = loader.load();

        Object controller = loader.getController();
        // Imposta la difficoltà nel controller specifico dell'esercizio
        if (controller instanceof QuizAppController) {
            ((QuizAppController) controller).setDifficulty(difficulty);
        } else if (controller instanceof CosaStampaController) {
            ((CosaStampaController) controller).setDifficulty(difficulty);
        } else if (controller instanceof TrovaErroreController) {
            ((TrovaErroreController) controller).setDifficulty(difficulty);
        } else if (controller instanceof CompletaFraseController) {
            ((CompletaFraseController) controller).setDifficulty(difficulty);
        }

        // Mostra la scena dell'esercizio
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(exerciseType + " - " + difficulty);
        stage.show();
    }

    // Metodo per gestire il clic sul bottone "X" nella ToolBar
    @FXML
    private void handleCloseAction() {
       //torna a selezione esercizi
        MainProgettoPlay.showHomeScene();}

}
