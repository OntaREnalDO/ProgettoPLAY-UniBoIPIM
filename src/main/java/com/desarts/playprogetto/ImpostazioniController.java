package com.desarts.playprogetto;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ImpostazioniController {

    @FXML
    private Label usernameLabel;
    @FXML
    private Button loginButton;
    @FXML
    private Button logoutButton;

    // Metodo iniziale per configurare la visibilità dei componenti
    public void initialize() {
        aggiornaSchermataWelcome();
    }

    private void aggiornaSchermataWelcome() {
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
        // Questo dovrebbe essere sostituito con la tua logica di autenticazione
        return false;
    }

    // Simula l'ottenimento del nome utente loggato
    private String getLoggedInUsername() {
        // Sostituisci con la logica appropriata per ottenere il nome utente
        return "Utente1";
    }

    @FXML
    private void handleLoginAction() {
        // Logica per gestire il login
        MainProgettoPlay.showLoginScene();
    }

    @FXML
    private void handleLogoutAction() {
        // Logica per gestire il logout

        // Dopo il logout, aggiorna l'interfaccia
        aggiornaSchermataWelcome();
    }

    @FXML
    private void handleBackAction() {
        // Torna alla schermata di welcome
        MainProgettoPlay.showWelcomeScene();
    }
}
