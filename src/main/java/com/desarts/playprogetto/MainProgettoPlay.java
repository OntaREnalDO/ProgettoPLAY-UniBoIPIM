package com.desarts.playprogetto;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainProgettoPlay extends Application {

    private static Stage primaryStage; 
    private static HomeController homeController;




    @Override
    public void start(Stage stage) {
        MainProgettoPlay.primaryStage = stage;
        showWelcomeScene(); 
        Image icon = new Image("com/desarts/playprogetto/immagini/25521.jpg");
        primaryStage.getIcons().add(icon);   
        primaryStage.setResizable(false);  
        primaryStage.setTitle("Progetto Play by DesArts");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void showWelcomeScene() {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(MainProgettoPlay.class.getResource( "welcome.fxml")));
            primaryStage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void showLoginScene() {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(MainProgettoPlay.class.getResource("login.fxml")));
            primaryStage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


        public static void showImpostazioniScene() {
            try {
                Parent root = FXMLLoader.load(Objects.requireNonNull(MainProgettoPlay.class.getResource("impostazioni.fxml")));
                primaryStage.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    public static void showRegisterScene() {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(MainProgettoPlay.class.getResource("register.fxml")));
            primaryStage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void showHomeScene() {
        try {
            FXMLLoader loader = new FXMLLoader(MainProgettoPlay.class.getResource("home.fxml"));
            Parent root = loader.load();

            homeController = loader.getController();
            homeController.setStage(primaryStage);
            homeController.updateProgressBars(); 
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("PLAY");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static HomeController getHomeController() {
        return homeController;
    }
}
