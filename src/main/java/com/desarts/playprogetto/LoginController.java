package com.desarts.playprogetto;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;


public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (GestoreUtenti.loginUtente(username, password)) {
            showWelcomeAlert("Login Effettuato", "Benvenuto, " + username + "!");
            MainProgettoPlay.showWelcomeScene();
        } else {
            showAlert("Errore di Login", "Credenziali non valide. Riprova.");
        }
    }




    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void showWelcomeAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    public void handleBackButtonAction(ActionEvent actionEvent) {
            MainProgettoPlay.showWelcomeScene(); 
        }


    @FXML
    public void handleRegisterButtonAction(ActionEvent event) {
        MainProgettoPlay.showRegisterScene();
    }



}
