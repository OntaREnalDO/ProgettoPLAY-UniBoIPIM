package com.desarts.playprogetto;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainProgettoPlay extends Application {

    private static Stage primaryStage;
    private static Scene welcomeScene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        MainProgettoPlay.primaryStage = primaryStage;

        // Carica la schermata welcome.fxml
        FXMLLoader welcomeLoader = new FXMLLoader(getClass().getResource("C:/Users/fandr/IdeaProjects/PLAYprogetto/src/main/resources/com/desarts/playprogetto/welcome.fxml"));
        Parent welcomeRoot = welcomeLoader.load();
        welcomeScene = new Scene(welcomeRoot);

        // Imposta il titolo della finestra principale
        primaryStage.setTitle("Progetto Play by DesArts");

        // Imposta la scena iniziale
        primaryStage.setScene(welcomeScene);

        // Mostra la finestra principale
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    // Metodo per cambiare la scena alla schermata di login
    public static void showLoginScene() {
        try {
            FXMLLoader loader = new FXMLLoader(MainProgettoPlay.class.getResource("\"C:/Users/fandr/IdeaProjects/PLAYprogetto/src/main/resources/com/desarts/playprogetto/login.fxml"));
            Parent root = loader.load();
            primaryStage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Metodo per cambiare la scena alla schermata delle impostazioni
    public static void showImpostazioniScene() {
        try {
            FXMLLoader loader = new FXMLLoader(MainProgettoPlay.class.getResource("\"C:/Users/fandr/IdeaProjects/PLAYprogetto/src/main/resources/com/desarts/playprogetto/impostazioni.fxml"));
            Parent root = loader.load();
            primaryStage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Metodo per tornare alla schermata welcome.fxml
    public static void showWelcomeScene() {
        primaryStage.setScene(welcomeScene);
    }
}
