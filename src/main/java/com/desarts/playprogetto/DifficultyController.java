package com.desarts.playprogetto;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class DifficultyController {

    @FXML
    private Button facile;

    @FXML
    private Button intermedio;

    @FXML
    private Button difficile;

    @FXML
    private Button BackButton;

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

    // Metodo per gestire il clic sul bottone "X" nella ToolBar
    @FXML
    private void handleCloseAction() {
       //torna a selezione esercizi
        MainProgettoPlay.showHomeScene();}

}
