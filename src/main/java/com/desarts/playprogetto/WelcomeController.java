package com.desarts.playprogetto;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;


public class WelcomeController {

    @FXML
    private void handleIniziaAction(ActionEvent event) {
        if(GestoreUtenti.loginCheck){
            ImpostazioniController.showWelcomeAlert("Benvenuto" + "nome utente", "Inizia a giocare!");
        MainProgettoPlay.showHomeScene();
    }    else{
            ImpostazioniController.showAlert("", "Devi prima effettuare l'accesso");
        }
    }

    @FXML
    private void handleLoginAction(ActionEvent event) {
        MainProgettoPlay.showLoginScene();
    }

    @FXML
    private void handleImpostazioniAction(ActionEvent event) {
        MainProgettoPlay.showImpostazioniScene();
    }


}
