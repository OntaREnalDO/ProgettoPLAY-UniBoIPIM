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

    // Metodo iniziale per configurare la visibilità dei componenti
    public void initialize() throws IOException {
        aggiornaSchermataWelcome();
    }

    private void aggiornaSchermataWelcome() throws IOException {
        boolean isLoggedIn = checkIfUserIsLoggedIn();
        usernameLabel.setVisible(isLoggedIn);
        logoutButton.setVisible(isLoggedIn);

        loginButton.setVisible(!isLoggedIn);

        if (isLoggedIn) {
            // Imposta il testo della label con il nome utente
            usernameLabel.setText("Benvenuto, " + getLoggedInUsername());
        }
    }

    // Simula il controllo dello stato di login
    private boolean checkIfUserIsLoggedIn() {
        // logica di autenticazione
        return GestoreUtenti.loginCheck;


    }

    // Simula l'ottenimento del nome utente loggato
    private String getLoggedInUsername() throws IOException {

        return GestoreUtenti.utenteCorrente.getNomeUtente();
    }

    @FXML
    private void handleLoginAction() {
        // Logica per gestire il login
        MainProgettoPlay.showLoginScene();
    }

    @FXML
    private void handleLogoutAction() throws IOException {
        // Logica per gestire il logout
        showLogoutAlert();
        // Dopo il logout, aggiorna l'interfaccia
        aggiornaSchermataWelcome();
    }

    @FXML
    private void handleBackAction() {
        // Torna alla schermata di welcome
        MainProgettoPlay.showWelcomeScene();
    }



    //lista degli alert
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

    // Alert di conferma logout a due opzioni
    public static void showLogoutAlert() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Conferma Uscita");
        alert.setHeaderText("Sei sicuro di voler uscire?");
        alert.setContentText("Seleziona OK per uscire, o Cancella per tornare all'applicazione.");

        // Personalizzazione dei bottoni
        alert.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);

        // Mostra l'Alert e attendi la risposta dell'utente
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                // L'utente ha confermato di voler uscire e si effettua logout
                GestoreUtenti.logoutUtente();
                //chiude l'applicazione
                //Platform.exit();

            } else {
                // L'utente ha selezionato di non uscire
                alert.close(); // Chiudi solo l'alert se l'utente decide di non uscire
            }
        });
    }

}
