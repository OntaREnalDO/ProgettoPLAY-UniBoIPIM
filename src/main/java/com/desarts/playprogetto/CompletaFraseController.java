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

public class CompletaFraseController {

    private int currentQuestion = 0;  
    private int score = 0;  
    private String codiceEsercizioCorrente = ""; 

    private boolean[] answeredCorrectlyFirstAttempt = new boolean[5];
    private String difficulty;
    private String[] questionImages;
    private String[][] answers; 
    private int[] correctAnswers;  
    private int[] attempts = new int[5];
    private boolean confirmedExercise = false;  
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
    private ScrollPane questionScrollPane; 
    @FXML
    private ImageView questionImageView;

    private Button[] answerButtons; 

    @FXML
    public void initialize() {
        answerButtons = new Button[]{answerButton1, answerButton2, answerButton3, answerButton4};
    }

 
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
        loadQuestionsAndAnswers();
        loadQuestion();
    }


    private void loadQuestionsAndAnswers() {
        switch (difficulty) {
            case "easy":
                questionImages = new String[]{
                        "/com/desarts/playprogetto/immagini/cf1.png",
                        "/com/desarts/playprogetto/immagini/cf2.png",
                        "/com/desarts/playprogetto/immagini/cf3.png",
                        "/com/desarts/playprogetto/immagini/cf4.png",
                        "/com/desarts/playprogetto/immagini/cf5.png"
                };
                answers = new String[][]{
                        {"A) System.out.println(Hello, World!);", "B) Console.WriteLine(\"Hello, World!\");", "C) System.out.println(\"Hello, World!\");", "D) echo \"Hello, World!\";"},
                        {"A) System.out.println(a - b);", "System.out.println(a * b);", "C) System.out.println(a / b);", "D) System.out.println(a + b);"},
                        {"A) if (a > b)", "B) if (a != b)", "C) if (a == b)", "D) if (a < b)"},
                        {"A) for (int i = 5; i >= 1; i--)", "B) for (int i = 1; i <= 5; i++)", "C) for (int i = 1; i < 5; i++)", "D) for (int i = 0; i <= 5; i++)"},
                        {"A) str.length()", "B) str.size()", "C) str.length", "D) str.size"}
                };
                correctAnswers = new int[]{2, 3, 3, 1, 0};
                break;
            case "intermediate":
                questionImages = new String[]{
                        "/com/desarts/playprogetto/immagini/ci1.png",
                        "/com/desarts/playprogetto/immagini/ci2.png",
                        "/com/desarts/playprogetto/immagini/ci3.png",
                        "/com/desarts/playprogetto/immagini/ci4.png",
                        "/com/desarts/playprogetto/immagini/ci5.png"
                };
                answers = new String[][]{
                        {"A) for (int i = 0; i < str.length(); i++)", "B) for (int i = str.length() - 1; i >= 0; i--)", "C) for (int i = str.length() - 1; i < 0; i--)", "D) for (int i = str.length() - 1; i >= 0; i++)"},
                        {"A) factorial += i", "B) factorial -= i", "C) factorial *= i", "D) factorial /= i"},
                        {"A) if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u')", "B) if (ch == 'a' && ch == 'e' && ch == 'i' && ch == 'o' && ch == 'u')", "C) if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' && ch == 'u')", "D) if (ch == 'a' || ch == 'e' && ch == 'i' || ch == 'o' || ch == 'u')"},
                        {"A) if (str.charAt(i) == str.charAt(str.length() - 1 - i))", "B) if (str.charAt(i) != str.charAt(str.length() - 1 - i))", "C) if (str.charAt(i) == str.charAt(str.length() / 2 - i))", "D) if (str.charAt(i) != str.charAt(str.length() / 2 - i))"},
                        {"A) if (arr[i] < max)", "B) if (arr[i] == max)", "C) if (arr[i] <= max) ", "D) if (arr[i] > max)"}
                };
                correctAnswers = new int[]{1, 2, 0, 1, 3};
                break;
            case "expert":
                questionImages = new String[]{
                        "/com/desarts/playprogetto/immagini/ce1.png",
                        "/com/desarts/playprogetto/immagini/ce2.png",
                        "/com/desarts/playprogetto/immagini/ce3.png",
                        "/com/desarts/playprogetto/immagini/ce4.png",
                        "/com/desarts/playprogetto/immagini/ce5.png"
                };
                answers = new String[][]{
                        {"A) else if (num > max2 && num != max1)", "B) if (num >= max2 && num != max1)", "C) else if (num >= max2 && num != max1)", "D) if (num > max2 && num != max1)"},
                        {"A) if (matrix[i][i] != matrix[j][j])", "B) if (matrix[j][i] != matrix[j][i])", "C) if (matrix[i][j] == matrix[j][i])", "D) if (matrix[i][j] != matrix[j][i])"},
                        {"A) rotated[(i - n) % arr.length] = arr[i];", "B) rotated[(i + n) % arr.length] = arr[i];", "C) rotated[(i * n) % arr.length] = arr[i];", "D) rotated[(i / n) % arr.length] = arr[i];"},
                        {"A) if (arr1[i] < arr2[j])", "B) if (arr1[i] > arr2[j])", "C) if (arr1[i] == arr2[j])", "D) if (arr1[i] != arr2[j])"},
                        {"A) if (arr[j] < arr[j + 1])", "B) if (arr[j] > arr[j + 1])", "C) if (arr[j] == arr[j + 1])", "D) if (arr[j] != arr[j + 1])"}
                };
                correctAnswers = new int[]{0, 3, 1, 0, 1};
                break;
        }
    }

    private void loadQuestion() {
        if (questionImages == null) {
            return; 
        }

        if (currentQuestion < questionImages.length) {
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
       
        if (currentQuestion == questionImages.length - 1) {
            nextButton.setText("Conferma esercizio");
        } else {
            nextButton.setText("Prossima domanda");
        }
    }

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

    private void showErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Risposta Errata");
        alert.setHeaderText(null);
        alert.setContentText("Risposta Errata. Riprova.");
        alert.showAndWait();
    }


    @FXML
    private void nextQuestion() {
        currentQuestion++;
        if (currentQuestion < questionImages.length) {
            loadQuestion();
        } else {
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

    private void returnToHome() {
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

    private String generaCodiceEsercizio(String difficulty) {
        String typeCode = "C";
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
