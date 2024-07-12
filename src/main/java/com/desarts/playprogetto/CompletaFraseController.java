package com.desarts.playprogetto;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class CompletaFraseController {

    private int currentQuestion = 0;  // Indice della domanda corrente
    private int score = 0;  // Punteggio dell'utente
    private String difficulty;  // Difficoltà selezionata
    private String[] questions;  // Array delle domande
    private String[][] answers;  // Array delle risposte
    private int[] correctAnswers;  // Indici delle risposte corrette
    private int[] attempts = new int[5];  // Tiene traccia dei tentativi per ogni domanda
    private boolean confirmedExercise = false;  // Indica se l'esercizio è stato confermato


    @FXML
    private Button nextButton;

    @FXML
    private Button answerButton1;
    @FXML
    private Button answerButton2;
    @FXML
    private Button answerButton3;
    @FXML
    private Button answerButton4;
    @FXML
    private Button homeButton;

    @FXML
    private Label questionLabel;

    private Button[] answerButtons; // Array dei pulsanti di risposta

    @FXML
    public void initialize() {
        answerButtons = new Button[]{answerButton1, answerButton2, answerButton3, answerButton4};
    }

    // Imposta la difficoltà e carica le domande e risposte corrispondenti
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
        loadQuestionsAndAnswers();
        loadQuestion();
    }

    // Carica le domande e le risposte in base alla difficoltà
    private void loadQuestionsAndAnswers() {
        switch (difficulty) {
            case "easy":
                questions = new String[]{
                        "Qual è la capitale d'Italia?",
                        "Qual è la capitale della Francia?",
                        "Qual è la capitale della Germania?",
                        "Qual è la capitale della Spagna?",
                        "Qual è la capitale del Regno Unito?"
                };
                answers = new String[][]{
                        {"Roma", "Milano", "Napoli", "Torino"},
                        {"Parigi", "Marsiglia", "Lione", "Nizza"},
                        {"Berlino", "Monaco", "Amburgo", "Colonia"},
                        {"Madrid", "Barcellona", "Valencia", "Siviglia"},
                        {"Londra", "Manchester", "Birmingham", "Liverpool"}
                };
                correctAnswers = new int[]{0, 0, 0, 0, 0};
                break;
            case "intermediate":
                questions = new String[]{
                        "Qual è la capitale del Giappone?",
                        "Qual è la capitale della Cina?",
                        "Qual è la capitale della Russia?",
                        "Qual è la capitale dell'India?",
                        "Qual è la capitale del Brasile?"
                };
                answers = new String[][]{
                        {"Tokyo", "Osaka", "Nagoya", "Sapporo"},
                        {"Pechino", "Shanghai", "Shenzhen", "Guangzhou"},
                        {"Mosca", "San Pietroburgo", "Novosibirsk", "Ekaterinburg"},
                        {"Nuova Delhi", "Mumbai", "Bangalore", "Chennai"},
                        {"Brasilia", "Rio de Janeiro", "San Paolo", "Salvador"}
                };
                correctAnswers = new int[]{0, 0, 0, 0, 0};
                break;
            case "expert":
                questions = new String[]{
                        "Qual è la capitale dell'Australia?",
                        "Qual è la capitale del Canada?",
                        "Qual è la capitale dell'Egitto?",
                        "Qual è la capitale dell'Argentina?",
                        "Qual è la capitale della Nigeria?"
                };
                answers = new String[][]{
                        {"Canberra", "Sydney", "Melbourne", "Brisbane"},
                        {"Ottawa", "Toronto", "Vancouver", "Montreal"},
                        {"Il Cairo", "Alessandria", "Giza", "Luxor"},
                        {"Buenos Aires", "Cordoba", "Rosario", "Mendoza"},
                        {"Abuja", "Lagos", "Kano", "Ibadan"}
                };
                correctAnswers = new int[]{0, 0, 0, 0, 0};
                break;
        }
    }
    // Carica la domanda corrente
    private void loadQuestion() {
        if (questions == null) {
            return; // Gestisce il caso in cui le domande non sono ancora caricate
        }

        if (currentQuestion < questions.length) {
            questionLabel.setText(questions[currentQuestion]);
            for (int i = 0; i < answerButtons.length; i++) {
                answerButtons[i].setText(answers[currentQuestion][i]);
                answerButtons[i].setDisable(false);
                final int index = i;
                answerButtons[i].setOnAction(event -> handleAnswer(index));
            }
            nextButton.setDisable(true);
        } else {
            showResult();
        }
        // Cambia il testo del pulsante all'ultima domanda
        if (currentQuestion == questions.length - 1) {
            nextButton.setText("Conferma esercizio");
        } else {
            nextButton.setText("Prossima domanda");
        }
    }

    // Gestisce la selezione della risposta
    private void handleAnswer(int index) {
        if (index == correctAnswers[currentQuestion]) {
            if (attempts[currentQuestion] == 0) {
                score++;
            }
            showCorrectAlert();
        } else {
            attempts[currentQuestion]++;
            showErrorAlert();
        }
    }

    // Mostra un alert di risposta corretta
    private void showCorrectAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Risposta Corretta");
        alert.setHeaderText(null);
        alert.setContentText("Bravo! Risposta Corretta");
        alert.showAndWait();
        nextButton.setDisable(false);
        for (Button btn : answerButtons) {
            btn.setDisable(true);
        }
    }

    // Mostra un alert di risposta errata
    private void showErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Risposta Errata");
        alert.setHeaderText(null);
        alert.setContentText("Risposta Errata. Riprova.");
        alert.showAndWait();
    }

    // Carica la domanda successiva o mostra il risultato
    @FXML
    private void nextQuestion() {
        currentQuestion++;
        if (currentQuestion < questions.length) {
            loadQuestion();
        } else {
            showResult();
        }
    }

    // Mostra il risultato finale
    private void showResult() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Risultato");
        alert.setHeaderText(null);
        alert.setContentText("Il tuo punteggio è: " + score + "/5\nConferma l'esercizio per salvare il tuo punteggio!");

        ButtonType buttonTypeConfirm  = new ButtonType("Conferma l'esercizio");
        ButtonType buttonTypeCancel  = new ButtonType("Non salvare");
        alert.getButtonTypes().setAll(buttonTypeConfirm, buttonTypeCancel);

        alert.showAndWait().ifPresent(type -> {
            if (type == buttonTypeConfirm) {
                returnToHome();
            } else {
                returnToHomeWithoutSaving();
            }
        });
    }

    //Per gestire il bottone "Torna alla home" durante l'esercizio
    @FXML
    private void handleHome(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Conferma");
        alert.setHeaderText("Sei sicuro di voler uscire dall'esercizio?");
        alert.setContentText("I progressi fatti non verranno salvati");

        ButtonType buttonTypeCancel = new ButtonType("Annulla");
        ButtonType buttonTypeHome = new ButtonType("Torna alla home");
        alert.getButtonTypes().setAll(buttonTypeCancel, buttonTypeHome);

        alert.showAndWait().ifPresent(type -> {
            if (type == buttonTypeHome) {
                returnToHomeWithoutSaving();
            }
        });
    }

    private void returnToHome() {
        // Carica la scena Home
        MainProgettoPlay.showHomeScene();
        // Add code to return to home screen and save the result
    }

    @FXML
    private void returnToHomeWithoutSaving() {
        Stage stage = (Stage) nextButton.getScene().getWindow();
        if (!confirmedExercise) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Conferma");
            alert.setHeaderText(null);
            alert.setContentText("Sei sicuro di voler uscire dall'esercizio? I progressi fatti non verranno salvati.");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    MainProgettoPlay.showHomeScene();
                    // Add code to return to home screen without saving progress
                }
            });
        }
    }
}
