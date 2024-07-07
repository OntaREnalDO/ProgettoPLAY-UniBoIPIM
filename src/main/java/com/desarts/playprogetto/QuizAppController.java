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

public class QuizAppController {

    private int currentQuestion = 0;  // Indice della domanda corrente
    private int score = 0;  // Punteggio dell'utente
    private boolean[] answeredCorrectlyFirstAttempt = new boolean[5];  // Tiene traccia delle risposte corrette al primo tentativo
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
                        "1) Quale parola chiave è usata per dichiarare una classe in Java?",
                        "2) Quale metodo della classe String restituisce la lunghezza della stringa?",
                        "3) Quale delle seguenti è una parola chiave per definire una variabile che non può essere modificata?",
                        "4) Qual è il risultato dell'operazione 5 / 2 in Java?",
                        "5) Quale delle seguenti parole chiave è usata per terminare un ciclo in Java?"
                };
                answers = new String[][]{
                        {"A) class", "B) Class", "C) object", "D) ClassName"},
                        {"A) size()", "B) count()", "C) length()", "D) getSize()"},
                        {"A) static", "B) final", "C) const", "D) immutable"},
                        {"A) 2.5", "B) 2", "C) 2.0", "D) 3"},
                        {"A) end", "B) stop", "C) exit", "D) break"}
                };
                correctAnswers = new int[]{0, 2, 1, 1, 3};
                break;
            case "intermediate":
                questions = new String[]{
                        "1) Quale delle seguenti non è un tipo di dato primitivo in Java?",
                        "2) Quale eccezione viene sollevata quando si tenta di accedere a un indice fuori dai limiti di un array in Java?",
                        "3) Quale delle seguenti affermazioni è vera riguardo ai costruttori in Java?",
                        "4) Quale delle seguenti opzioni rappresenta il modo corretto per dichiarare un array di interi in Java?",
                        "5) Quale delle seguenti parole chiave viene utilizzata per creare una sottoclasse in Java?"
                };
                answers = new String[][]{
                        {"A) int", "B) boolean", "C) String", "D) char"},
                        {"A) ArrayIndexOutOfBoundsException", "B) IndexOutOfBoundsException", "C) NullPointerException", "D) IllegalArgumentException"},
                        {"A) I costruttori non possono essere sovraccaricati", "B) I costruttori devono avere lo stesso nome della classe", "C) I costruttori possono restituire un valore", "D) I costruttori possono essere chiamati direttamente come un metodo"},
                        {"A) int arr[] = new int[10];", "B) int arr = new int[10];", "C) int[] arr = new int[10];", "D) int arr[10] = new int[];"},
                        {"A) implements", "B) inherits", "C) extends", "D) uses"}
                };
                correctAnswers = new int[]{2, 0, 1, 2, 2};
                break;
            case "expert":
                questions = new String[]{
                        "1) Qual è la differenza tra ArrayList e LinkedList in Java?",
                        "2) Quale delle seguenti annotazioni è utilizzata per specificare che un metodo è destinato a sovrascrivere un metodo in una superclasse?",
                        "3) Cos'è il polimorfismo in Java?",
                        "4) Cos'è una classe astratta in Java?",
                        "5) Quale classe Java è utilizzata per la gestione delle connessioni di rete?"
                };
                answers = new String[][]{
                        {"A) ArrayList utilizza una lista collegata, LinkedList un array dinamico", "B) ArrayList ha tempi di accesso più veloci, LinkedList ha tempi di inserimento e rimozione più veloci", "C) ArrayList non può contenere elementi duplicati, LinkedList sì", "D) ArrayList è thread-safe, LinkedList no"},
                        {"A) @Override", "B) @Overload", "C) @Overriding", "D) @Implement"},
                        {"A) La capacità di una variabile, funzione o oggetto di assumere più forme", "B) L'ereditarietà multipla", "C) La capacità di un metodo di avere più implementazioni in classi diverse", "D) La creazione di oggetti di classi diverse"},
                        {"A) Una classe che non può avere metodi", "B) Una classe che non può essere istanziata", "C) Una classe che non può avere variabili", "D) Una classe che deve essere finale"},
                        {"A) Network", "B) ServerSocket", "C) SocketConnection", "D) Connection"}
                };
                correctAnswers = new int[]{1, 0, 0, 1, 1};
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
                goToHome();
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
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
            Parent root = loader.load();

            // Ottieni il controller della Home e imposta lo stage
            HomeController homeController = loader.getController();
            Stage stage = (Stage) nextButton.getScene().getWindow();
            homeController.setStage(stage);

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Home");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Add code to return to home screen and save the result
    }

    private void returnToHomeWithoutSaving() {
        // Carica la scena Home
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
            Parent root = loader.load();

            // Ottieni il controller della Home e imposta lo stage
            HomeController homeController = loader.getController();
            Stage stage = (Stage) nextButton.getScene().getWindow();
            homeController.setStage(stage);

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Home");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Add code to return to home screen without saving progress
    }

    @FXML
    private void goToHome() {
        Stage stage = (Stage) nextButton.getScene().getWindow();
        if (!confirmedExercise) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Conferma");
            alert.setHeaderText(null);
            alert.setContentText("Sei sicuro di voler uscire dall'esercizio? I progressi fatti non verranno salvati.");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
                        Parent root = loader.load();

                        // Ottieni il controller della Home e imposta lo stage
                        HomeController homeController = loader.getController();
                        homeController.setStage(stage);

                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.setTitle("Home");
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}