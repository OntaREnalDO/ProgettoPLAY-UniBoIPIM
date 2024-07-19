package com.desarts.playprogetto;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
public class CosaStampaController {

    private int currentQuestion = 0;
    private int score = 0;
    private String codiceEsercizioCorrente = ""; 

    private String difficulty;

    private String[] questions;
    private String[] correctAnswers;
    private int[] attempts;
    Utente utenteCorrente = GestoreUtenti.getUtenteCorrente();

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

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
        loadQuestionsAndAnswers();
        attempts = new int[questions.length];
        loadQuestion();
    }

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
    private void loadQuestion() {
        if (questions == null) {
            return; 
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
        if (currentQuestion == correctAnswers.length - 1) {
            nextButton.setText("Conferma esercizio");
        } else {
            nextButton.setText("Prossima domanda");
        }
    }

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

    private void showCorrectAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Risposta Corretta");
        alert.setHeaderText(null);
        alert.setContentText("Bravo! Risposta Corretta");
        alert.showAndWait();
        nextButton.setDisable(false);
    }

    private void showErrorAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

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

    private void showResult() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Risultato del Quiz");
        alert.setHeaderText(null);
        String resultText = "Il tuo punteggio è: " + score + "/500\n";
        if (score >= 300) {
            resultText += "Hai completato l'esercizio! Congratulazioni " + utenteCorrente.getNomeUtente() + "!";

            int currentStoredScore = utenteCorrente.getPunteggioSingolo(codiceEsercizioCorrente);

            if (currentStoredScore == 0 || score > currentStoredScore) {
                try {
                    salvaPartita();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }   else {
            resultText += "Esercizio non superato! Riprova!";
        }
        alert.setContentText(resultText);

        ButtonType buttonTypeConfirm = new ButtonType("Fine esercizio");
        alert.getButtonTypes().setAll(buttonTypeConfirm);

        alert.showAndWait().ifPresent(type -> {
            if (type == buttonTypeConfirm) {
                returnToHome();
            }
        });
    }

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

    @FXML
    private void conferma(ActionEvent event) {
        handleAnswer();
    }

    private void returnToHome() {
        MainProgettoPlay.showHomeScene();
    }

    private void salvaPartita() throws IOException, NoSuchAlgorithmException {
        GestoreUtenti.aggiornaPunteggioUtente(utenteCorrente.getNomeUtente(), codiceEsercizioCorrente, score);
    }

    private void returnToHomeWithoutSaving() {
        Stage stage = (Stage) nextButton.getScene().getWindow();
        stage.close();
        MainProgettoPlay.showHomeScene();
    }

    private String generaCodiceEsercizio(String difficulty) {
        String typeCode = "B";
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

        return typeCode + levelCode; 
    }
}
