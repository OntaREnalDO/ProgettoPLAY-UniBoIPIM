package com.desarts.playprogetto;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class BaseEsercizi {

    protected int currentQuestion = 0;
    protected int score = 0;
    protected String codiceEsercizioCorrente = "";
    protected boolean[] answeredCorrectlyFirstAttempt = new boolean[5];
    protected String difficulty;
    protected String[] questions;
    protected String[][] answers;
    protected int[] correctAnswers;
    protected int[] attempts = new int[5];
    protected boolean confirmedExercise = false;
    protected Utente utenteCorrente = GestoreUtenti.getUtenteCorrente();

    @FXML
    protected Label questionLabel;
    @FXML
    protected Button nextButton;
    @FXML
    protected Button homeButton;

    public abstract void loadQuestionsAndAnswers();

    protected void showCorrectAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Risposta Corretta");
        alert.setHeaderText(null);
        alert.setContentText("Risposta Corretta! Puoi procedere");
        alert.showAndWait();
        nextButton.setDisable(false);
    }

    protected void showErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Risposta Errata");
        alert.setHeaderText(null);
        alert.setContentText("Risposta Errata. Riprova.");
        alert.showAndWait();
    }

    protected String generaCodiceEsercizio() {
        String typeCode = ""; // Codice specifico per ogni tipo di esercizio
        int levelCode;
        switch (difficulty.toLowerCase()) {
            case "easy":
                levelCode = 1;
                break;
            case "intermediate":
                levelCode = 2;
                break;
            case "expert":
                levelCode = 3;
                break;
            default:
                throw new IllegalArgumentException("Difficoltà non riconosciuta");
        }
        return typeCode + levelCode; // Costruisce il codice come "C1", "Q2", "T3", ecc.
    }
}
}
