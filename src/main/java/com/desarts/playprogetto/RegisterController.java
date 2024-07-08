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

import static com.desarts.playprogetto.GestoreUtenti.registraUtente;

public class RegisterController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private void handleRegisterButtonAction(ActionEvent event) throws IOException {


        String username = usernameField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        // Verifica che le password siano identiche
        if (!password.equals(confirmPassword)) {
            showAlert("Errore di Registrazione", "Le password non corrispondono.");
        } else {
            // Utente viene registrato con registraUtente() di GestoriUtenti e mostrato avviso di successo
            registraUtente(username, password, 0);
            showWelcomeAlert("Registrazione Effettuata", "Benvenuto, " + username + "! Account creato con successo.");
            MainProgettoPlay.showLoginScene();
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
    public void handleBackButtonAction(ActionEvent event) {
        MainProgettoPlay.showWelcomeScene();
    }


}

