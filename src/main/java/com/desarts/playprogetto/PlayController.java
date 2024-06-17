package com.desarts.playprogetto;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PlayController {
    @FXML
    private Label welcomeText;


    @FXML
    protected void tastoPLAY() {
        welcomeText.setText("Progetto PLAY");
    }
}