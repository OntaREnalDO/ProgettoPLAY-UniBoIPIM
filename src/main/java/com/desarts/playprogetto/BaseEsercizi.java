package com.desarts.playprogetto;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public abstract class BaseEsercizi {

    protected int currentQuestion = 0;  // Indice della domanda corrente
    protected int score = 0;  // Punteggio corrente dell'utente
    protected String[] questions;  // Array delle domande
    protected String[][] answers;  // Matrice delle risposte
    protected Object[] correctAnswers;  // Array degli indici delle risposte corrette
    protected int[] attempts;  // Array dei tentativi per ogni domanda
    protected boolean confirmedExercise = false;  // Indica se l'esercizio e' stato confermato
    protected int difficulty;  // Difficolta' corrente
    protected String codiceEsercizioCorrente;  // Codice dell'esercizio corrente

    @FXML
    protected Button nextButton;
    @FXML
    protected Label questionLabel;
    @FXML
    protected Button homeButton;

    protected Utente utenteCorrente = GestoreUtenti.getUtenteCorrente();


    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
        loadQuestionsAndAnswers();
        loadQuestion();
    }

    protected abstract void loadQuestionsAndAnswers();

    protected void loadQuestion() {
        if (questions == null || currentQuestion >= questions.length) {
            showResult();
            return;
        }
        questionLabel.setText(questions[currentQuestion]);
        // cambiare a seconda del tipo di esercizio
    }

    protected void handleAnswer(int index) {
        Object correctAnswer = correctAnswers[currentQuestion];

        // Verifica se la risposta corretta e' di tipo String
        if (correctAnswer instanceof String) {
            String answerStr = (String) correctAnswer;//cast a String
            if (answerStr.equals(String.valueOf(index))) {
                incrementScore();
            } else {
                incrementAttempts();
            }
        }
        // Verifica se la risposta corretta e' di tipo Integer
        else if (correctAnswer instanceof Integer) {
            Integer answerInt = (Integer) correctAnswer;//cast a Integer
            if (answerInt.equals(index)) {
                incrementScore();
            } else {
                incrementAttempts();
            }
        } else {
            // Gestisci il caso in cui correctAnswer non sia ne' String ne' Integer
            showAlert("Tipo di risposta non gestito", "Contattare il supporto tecnico.");
        }
    }

    private void incrementScore() {
        if (attempts[currentQuestion] == 0) {
            score += 100;
        }
        showCorrectAlert();
    }

    private void incrementAttempts() {
        attempts[currentQuestion]++;
        showErrorAlert();
    }


    protected void showCorrectAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Risposta Corretta");
        alert.setHeaderText(null);
        alert.setContentText("Ottimo! Risposta corretta.");
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

    // Mostra un alert di risposta errata
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    protected void showResult() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Risultato del Quiz");
        alert.setHeaderText(null);
        String resultText = "Il tuo punteggio e': " + score + "/500\n";
        if (score >= 300) {
            resultText += "Hai completato l'esercizio! Congratulazioni " + utenteCorrente.getNomeUtente() + "!";

            //metodo per incrementare la progress bar

        } else {
            resultText += "Esercizio non superato! Riprova!";
        }
        alert.setContentText(resultText);
        alert.showAndWait();
        returnToHome();

    }

    @FXML
    protected void returnToHome() {
        // logica per salvare il punteggio e tornare alla home
        try {
            GestoreUtenti.aggiornaPunteggioUtente(utenteCorrente.getNomeUtente(), codiceEsercizioCorrente, score);
            MainProgettoPlay.showHomeScene();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void returnToHomeWithoutSaving() {
        MainProgettoPlay.showHomeScene();
    }

    protected abstract String generaCodiceEsercizio();


}
