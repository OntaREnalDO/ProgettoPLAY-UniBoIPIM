package com.desarts.playprogetto;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;


import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class TrovaErroreController {

    private int currentQuestion = 0;  // Indice della domanda corrente
    private int score = 0;  // Punteggio dell'utente
    private String codiceEsercizioCorrente = ""; //codice dell'esercizio completato

    private boolean[] answeredCorrectlyFirstAttempt = new boolean[5];  // Tiene traccia delle risposte corrette al primo tentativo
    private String difficulty;  // Difficoltà selezionata
    private String[] questionImages;
    // Array delle domande
    private String[][] answers;  // Array delle risposte
    private int[] correctAnswers;  // Indici delle risposte corrette
    private int[] attempts = new int[5];  // Tiene traccia dei tentativi per ogni domanda
    private boolean confirmedExercise = false;  // Indica se l'esercizio è stato confermato
    //creo istanza di utenteCorrente
    Utente utenteCorrente = GestoreUtenti.getUtenteCorrente();

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
    @FXML
    private ScrollPane questionScrollPane; // ScrollPane per le immagini delle domande
    @FXML
    private ImageView questionImageView; // ImageView per visualizzare le immagini delle domande

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
                questionImages = new String[]{
                        "/com/desarts/playprogetto/immagini/ef1.png",
                        "/com/desarts/playprogetto/immagini/ef2.png",
                        "/com/desarts/playprogetto/immagini/ef3.png",
                        "/com/desarts/playprogetto/immagini/ef4.png",
                        "/com/desarts/playprogetto/immagini/ef5.png"
                };
                answers = new String[][]{
                        {"A) Cambiare \"5\" in 5", "B) Cambiare int in String per la variabile a", "C) Cambiare System.out.println(a + b); in System.out.println(a - b);", "D) Cambiare b in una stringa"},
                        {"A) Cambiare 5 in 3", "B) Cambiare i-- in i++", "C) Cambiare int in double per la variabile i", "D) Cambiare < in <="},
                        {"A) Cambiare <= in <", "B) Cambiare i++ in i--", "C) Cambiare int in double per la variabile i", "D) Aggiungere un elemento all'array"},
                        {"A) Cambiare int in double per la variabile x", "B) Cambiare 5 in 10", "C) Cambiare System.out.println(\"x is 5\"); in System.out.println(x); ", "D) Cambiare = in =="},
                        {"A) Cambiare <= in <", "B) Cambiare str.charAt(i) in str.charAt(i - 1)", "C) Cambiare String in int per la variabile str", "D) Cambiare i++ in i--"}
                };
                correctAnswers = new int[]{0, 1, 0, 3, 0};
                break;
            case "intermediate":
                questionImages = new String[]{
                        "/com/desarts/playprogetto/immagini/ei1.png",
                        "/com/desarts/playprogetto/immagini/ei2.png",
                        "/com/desarts/playprogetto/immagini/ei3.png",
                        "/com/desarts/playprogetto/immagini/ei4.png",
                        "/com/desarts/playprogetto/immagini/ei5.png"
                };
                answers = new String[][]{
                        {"A) Cambiare <= in <; i++)", "B) Cambiare total += arr[i] in total += i", "C) Cambiare int[] arr in String[] arr", "D) Cambiare System.out.println(sum(arr)); in System.out.println(sum(arr));"},
                        {"A) Cambiare = in ==", "B) Cambiare return 1 in return 0", "C) Cambiare int in double per la variabile n", "D) Cambiare return n * factorial(n - 1); in return n + factorial(n - 1);"},
                        {"A) Cambiare private String name in public String name", "B) Cambiare p.setName(\"Alice\"); in p.name = \"Alice\";", "C) Cambiare == in =", "D) Cambiare getName() in getNome()"},
                        {"A) Cambiare private String color in public String color", "B) Cambiare Colour in color", "C) Cambiare Car myCar in Car mycar", "D) Cambiare myCar.getColor() in myCar.getColor;"},
                        {"A) Cambiare arr[i] == target in arr[i] = target", "B Cambiare for (int i = 0; i < arr.length; i++) in for (int i = 0; i <= arr.length; i++)",  "C) Cambiare boolean found in int found", "D) Non ci sono errori"}
                };
                correctAnswers = new int[]{0, 0, 2, 1, 3};
                break;
            case "expert":
                questionImages = new String[]{
                        "/com/desarts/playprogetto/immagini/ee1.png",
                        "/com/desarts/playprogetto/immagini/ee2.png",
                        "/com/desarts/playprogetto/immagini/ee3.png",
                        "/com/desarts/playprogetto/immagini/ee4.png",
                        "/com/desarts/playprogetto/immagini/ee5.png"
                };
                answers = new String[][]{
                        {"A) Cambiare a.makeSound() in ((Dog) a).makeSound()", "B) Cambiare class Dog extends Animal in class Dog implements Animal", "C) Cambiare public void makeSound() in private void makeSound()", "D) Non ci sono errori"},
                        {"A) Cambiare list.add(\"Apple\"); in list.add(0, \"Apple\")", "B) Cambiare ArrayList<String> in List<String>", "C) Cambiare list.get(3) in list.get(2)", "D) Non ci sono errori"},
                        {"A) Non ci sono errori", "B) Cambiare class B extends A in class B", "C) Cambiare public A() in private A()", "D) Aggiungere super() al costruttore di B"},
                        {"A) Cambiare class C extends B in class C", "B) Cambiare @Override in @Overload", "C) Cambiare System.out.println(\"Display from C\") in System.out.println(\"Display from B\")", "D) Non ci sono errori"},
                        {"A) Cambiare super(5); in super.setVal(5);", "B) Cambiare class D extends C in class D", "C) Aggiungere un costruttore di default a C", "D) Non ci sono errori"}
                };
                correctAnswers = new int[]{0, 2, 3, 1, 1};
                break;
        }
    }
    // Carica la domanda corrente
    private void loadQuestion() {
        if (questionImages == null) {
            return; // Gestisce il caso in cui le domande non sono ancora caricate
        }

        if (currentQuestion < questionImages.length) {
            //carica immagine domanda corrente
            Image questionImage = new Image(questionImages[currentQuestion]);
            questionImageView.setImage(questionImage);

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
        if (currentQuestion == questionImages.length - 1) {
            nextButton.setText("Conferma esercizio");
        } else {
            nextButton.setText("Prossima domanda");
        }
    }

    // Gestisce la selezione della risposta
    private void handleAnswer(int index) {
        if (index == correctAnswers[currentQuestion]) {
            if (attempts[currentQuestion] == 0) {
                score+=100;
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
        if (currentQuestion < questionImages.length) {
            loadQuestion();
        } else {
            showResult();
        }
    }

    // Mostra il risultato finale
    private void showResult() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Risultato del Quiz");
        alert.setHeaderText(null);
        // Preparazione del messaggio di risultato
        String resultText = "Il tuo punteggio è: " + score + "/500\n";
        // Verifica se l'esercizio è stato superato
        if (score >= 300) {
            resultText += "Hai completato l'esercizio! Congratulazioni " + utenteCorrente.getNomeUtente() + "!";

            // Recupera il punteggio corrente salvato per l'esercizio
            int currentStoredScore = utenteCorrente.getPunteggioSingolo(codiceEsercizioCorrente);

            // Aggiorna il punteggio solo se il nuovo è maggiore di quello già registrato
            //quindi salva anche il punteggio se l'esercizio non e' mai stato fatto
            // Logica per l'aggiornamento del punteggio e della progress bar
            if (currentStoredScore == 0 || score > currentStoredScore) {
                // Salva il nuovo punteggio se è la prima volta o se è migliore del precedente
                try {
                    salvaPartita();
                    //HomeController.incrementProgressBar(1.0 / 3.0, "C");
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
    }

    private void salvaPartita() throws IOException, NoSuchAlgorithmException {
        GestoreUtenti.aggiornaPunteggioUtente(utenteCorrente.getNomeUtente(), codiceEsercizioCorrente, score);
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

                }
            });
        }
    }

    //genera codice esercizio per salvatagio su file
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

        return typeCode + levelCode; // Costruisce il codice come "D1", "D2", "D3"
    }

}