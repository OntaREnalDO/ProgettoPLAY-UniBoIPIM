package com.desarts.playprogetto;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

import java.io.IOException;

public class ImpostazioniController {

    @FXML
    private Label usernameLabel;
    @FXML
    private Button loginButton;
    @FXML
    private Button logoutButton;

    public void initialize() throws IOException {
        aggiornaSchermataWelcome();
    }

    private void aggiornaSchermataWelcome() throws IOException {
        boolean isLoggedIn = checkIfUserIsLoggedIn();
        usernameLabel.setVisible(isLoggedIn);
        logoutButton.setVisible(isLoggedIn);

        loginButton.setVisible(!isLoggedIn);

        if (isLoggedIn) {
            usernameLabel.setText("Benvenuto, " + getLoggedInUsername());
        }
    }

    private boolean checkIfUserIsLoggedIn() {
        return GestoreUtenti.loginCheck;


    }

    private String getLoggedInUsername() throws IOException {

        return GestoreUtenti.utenteCorrente.getNomeUtente();
    }

    @FXML
    private void handleLoginAction() {
        MainProgettoPlay.showLoginScene();
    }

    @FXML
    private void handleLogoutAction() throws IOException {
        showLogoutAlert();
        aggiornaSchermataWelcome();
    }

    @FXML
    private void handleBackAction() {
        MainProgettoPlay.showWelcomeScene();
    }



    static void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    static void showWelcomeAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void showLogoutAlert() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Conferma Uscita");
        alert.setHeaderText("Sei sicuro di voler uscire?");
        alert.setContentText("Seleziona OK per uscire, o Cancella per tornare all'applicazione.");

        alert.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                GestoreUtenti.logoutUtente();

            } else {
                alert.close(); // Chiudi solo l'alert se l'utente decide di non uscire
            }
        });
    }

}
