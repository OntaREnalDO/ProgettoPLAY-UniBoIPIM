package com.desarts.playprogetto;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.Objects;
public class CosaStampaController {

    private int currentQuestion = 0;
    private int score = 0;
    private String difficulty;

    private String[] questions;
    private String[] correctAnswers;
    private int[] attempts;

    @FXML
    private Button nextButton;

    @FXML
    private TextArea text;

    @FXML
    private Button homeButton;

    @FXML
    private Label questionLabel;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ImageView questionImageView;

    @FXML
    private Button conferma;

    private String currentAnswer = "Risposta corretta";

    // Imposta la difficoltà e carica le domande e risposte corrispondenti
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
        loadQuestionsAndAnswers();
        attempts = new int[questions.length];
        loadQuestion();
    }

    // Carica le domande e le risposte in base alla difficoltà
    private void loadQuestionsAndAnswers() {
        switch (difficulty) {
            case "easy":
                questions = new String[]{
                        "/com/desarts/playprogetto/immagini/csf1.png",
                        "/com/desarts/playprogetto/immagini/csf2.png",
                        "/com/desarts/playprogetto/immagini/csf3.png",
                        "/com/desarts/playprogetto/immagini/csf4.png",
                        "/com/desarts/playprogetto/immagini/csf5.png"
                };
                correctAnswers = new String[]{
                        "8",
                        "0 1 2",
                        "3 2 1",
                        "100",
                        "0 1 2"
                };
                break;
            case "intermediate":
                questions = new String[]{
                        "/com/desarts/playprogetto/immagini/csi1.png",
                        "/com/desarts/playprogetto/immagini/csi2.png",
                        "/com/desarts/playprogetto/immagini/csi3.png",
                        "/com/desarts/playprogetto/immagini/csi4.png",
                        "/com/desarts/playprogetto/immagini/csi5.png",
                };
                correctAnswers = new String[]{
                        "15",
                        "120",
                        "Alice",
                        "red",
                        "true"
                };
                break;
            case "expert":
                questions = new String[]{
                        "/com/desarts/playprogetto/immagini/cse1.png",
                        "/com/desarts/playprogetto/immagini/cse2.png",
                        "/com/desarts/playprogetto/immagini/cse3.png",
                        "/com/desarts/playprogetto/immagini/cse4.png",
                        "/com/desarts/playprogetto/immagini/cse5.png",
                };
                correctAnswers = new String[]{
                        "Bark",
                        "Bark",
                        "Banana",
                        "Costruttore A\nCostruttore B",
                        "Display from C"
                };
                break;
        }
        attempts = new int[questions.length];
    }
    // Carica la domanda corrente
    private void loadQuestion() {
        if (questions == null) {
            return; // Gestisce il caso in cui le domande non sono ancora caricate
        }

        if (currentQuestion < correctAnswers.length) {
            try{
                Image image = new Image(questions[currentQuestion]);
                questionImageView.setImage(image);
                text.clear();
                conferma.setDisable(false);
                nextButton.setDisable(true);
            } catch(Exception e){
                e.printStackTrace();
            }

        } else {
            showResult();
        }
        // Cambia il testo del pulsante all'ultima domanda
        if (currentQuestion == correctAnswers.length - 1) {
            nextButton.setText("Conferma esercizio");
        } else {
            nextButton.setText("Prossima domanda");
        }
    }

    // Gestisce la risposta
    private void handleAnswer() {
        String userAnswer = text.getText().trim();
        if (userAnswer.equals(correctAnswers[currentQuestion])) {
            if (attempts[currentQuestion] == 0) {
                score++;
            }
            showCorrectAlert();
            conferma.setDisable(true);
            nextButton.setDisable(false);
        } else {
            attempts[currentQuestion]++;
            showErrorAlert("Risposta errata","riprova");
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
    }

    // Mostra un alert di risposta errata
    private void showErrorAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    // Carica la domanda successiva o mostra il risultato
    @FXML
    private void nextQuestion() {
        if(!nextButton.isDisabled()) {
            currentQuestion++;
            if (currentQuestion < correctAnswers.length) {
                loadQuestion();
            } else
                showResult();
        }
    }

    // Mostra il risultato finale
    private void showResult() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Risultato");
        alert.setHeaderText(null);
        if(score>2)
            alert.setContentText("Complimenti! Hai superato l'esercizio.\nIl tuo punteggio è: " + score + "/5\nPremi su \"Conferma l'esercizio\" per salvare il tuo punteggio!");
        else
            alert.setContentText("Peccato! Non hai superato l'esercizio.\nIl tuo punteggio è: " + score + "/5\nPremi su \"Conferma l'esercizio\" per salvare il tuo punteggio!");


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

    //conferma risposta
    @FXML
    private void conferma(ActionEvent event) {
        handleAnswer();
    }

    private void returnToHome() {
        // Carica la scena Home
        MainProgettoPlay.showHomeScene();
        // Add code to return to home screen and save the result
    }

    private void returnToHomeWithoutSaving() {
        Stage stage = (Stage) nextButton.getScene().getWindow();
        stage.close(); // Chiude la finestra dell'esercizio
        MainProgettoPlay.showHomeScene(); // Mostra la scena Home
    }
}
