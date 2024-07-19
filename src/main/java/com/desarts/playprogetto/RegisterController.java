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

import static com.desarts.playprogetto.GestoreUtenti.registraUtente;

public class RegisterController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private void handleRegisterButtonAction(ActionEvent event) throws IOException, NoSuchAlgorithmException {


        String username = usernameField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();
    if(username.equals("") || password.equals("") || confirmPassword.equals("")) {//garantisce che le casele di testo non siano vuote
        ImpostazioniController.showAlert("Errore di Registrazione", "Compila tutti i campi.");
    }else if (!password.equals(confirmPassword)) { // Verifica che le password siano identiche
            ImpostazioniController.showAlert("Errore di Registrazione", "Le password non corrispondono.");

        }  else {
            registraUtente(username, password);
            ImpostazioniController.showWelcomeAlert("Registrazione Effettuata", "Benvenuto, " + username + "! Account creato con successo.");
            MainProgettoPlay.showLoginScene();
        }
    }



    @FXML
    public void handleBackButtonAction(ActionEvent event) {
        MainProgettoPlay.showWelcomeScene();
    }


}

