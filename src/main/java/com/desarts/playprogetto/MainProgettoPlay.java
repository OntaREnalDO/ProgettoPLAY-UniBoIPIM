package com.desarts.playprogetto;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class MainProgettoPlay extends Application {

    private static Stage primaryStage;  // Lo stage principale dell'applicazione



    @Override
    public void start(Stage stage) {
        MainProgettoPlay.primaryStage = stage;
        showWelcomeScene();  // Mostra la scena di benvenuto all'avvio
        Image icon = new Image("com/desarts/playprogetto/25521.jpg");
        primaryStage.getIcons().add(icon);   // Aggiunge l'icona in alto a sinistra nella schermata
        primaryStage.setResizable(false);    // Non ti permette di ingrandire la schermata
        primaryStage.setTitle("Progetto Play by DesArts");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    // Metodo per visualizzare la scena di benvenuto
    public static void showWelcomeScene() {
        try {
            Parent root = FXMLLoader.load(MainProgettoPlay.class.getResource("welcome.fxml"));
            primaryStage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Metodo per visualizzare la scena di login
    public static void showLoginScene() {
        try {
            Parent root = FXMLLoader.load(MainProgettoPlay.class.getResource("login.fxml"));
            primaryStage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


        // Metodo per visualizzare la scena delle impostazioni
        public static void showImpostazioniScene() {
            try {
                Parent root = FXMLLoader.load(MainProgettoPlay.class.getResource("impostazioni.fxml"));
                primaryStage.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    // Metodo per visualizzare la scena di registrazione
    public static void showRegisterScene() {
        try {
            Parent root = FXMLLoader.load(MainProgettoPlay.class.getResource("register.fxml"));
            primaryStage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Metodo per visualizzare la scena home
    public static void showHomeScene() {
        try {
            FXMLLoader loader = new FXMLLoader(MainProgettoPlay.class.getResource("home.fxml"));
            Parent root = loader.load();

            HomeController homeController = loader.getController();
            homeController.setStage(primaryStage);

            primaryStage.setTitle("PLAY");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
/*
    // Metodo per visualizzare la scena difficolta
    public static void showDifficultyScene() {
        try {
            Parent root = FXMLLoader.load(MainProgettoPlay.class.getResource("/com/desarts/playprogetto/difficolta_cs.fxml"));
            primaryStage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
*/

}
