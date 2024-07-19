package com.desarts.playprogetto;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class DifficultyController {

    private String exerciseType;  
    private Stage stage;

    public void setExerciseType(String exerciseType) {
        this.exerciseType = exerciseType;
    }

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

    @FXML
    private void handleEasy() throws IOException {
        loadExercise("easy");
    }

    @FXML
    private void handleIntermediate() throws IOException {
        loadExercise("intermediate");
    }

    @FXML
    private void handleExpert() throws IOException {
        loadExercise("expert");
    }

    private void loadExercise(String difficulty) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(exerciseType + ".fxml"));
        Parent root = loader.load();

        Object controller = loader.getController();
        if (controller instanceof QuizAppController) {
            ((QuizAppController) controller).setDifficulty(difficulty);
        } else if (controller instanceof CosaStampaController) {
            ((CosaStampaController) controller).setDifficulty(difficulty);
        } else if (controller instanceof TrovaErroreController) {
            ((TrovaErroreController) controller).setDifficulty(difficulty);
        } else if (controller instanceof CompletaFraseController) {
            ((CompletaFraseController) controller).setDifficulty(difficulty);
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(exerciseType + " - " + difficulty);
        stage.show();
    }

    @FXML
    private void handleCloseAction() {
       //torna a selezione esercizi
        MainProgettoPlay.showHomeScene();}

}
