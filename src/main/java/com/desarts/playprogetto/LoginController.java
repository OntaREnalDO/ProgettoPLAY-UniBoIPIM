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

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void handleLoginButtonAction(ActionEvent event) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        //Verifica delle credenziali
        if (GestoreUtenti.loginUtente(username, password)) {
            showWelcomeAlert("Login Effettuato", "Benvenuto, " + username + "!");

            //aggiornare con metodi di sessione utente

            MainProgettoPlay.showHomeScene();
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
            MainProgettoPlay.showWelcomeScene(); // Mostra la schermata di benvenuto
        }


    @FXML
    public void handleRegisterButtonAction(ActionEvent event) {
        MainProgettoPlay.showRegisterScene();
    }


}
