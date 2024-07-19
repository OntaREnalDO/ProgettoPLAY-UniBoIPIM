package com.desarts.playprogetto;

import java.io.*;
import java.util.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;


public class GestoreUtenti{

    static boolean loginCheck = false;
    static Utente utenteCorrente = null;
    private static final String FILE_TXT = "src/main/resources/com/desarts/playprogetto/listaUtenti.txt";

    public static void registraUtente(String nomeUtente, String password) throws IOException, NoSuchAlgorithmException {
        List<Utente> utenti = leggiUtenti();

        for (Utente utente : utenti) {
            if (utente.getNomeUtente().equals(nomeUtente)) {
                ImpostazioniController.showAlert("Errore di Registrazione", "Nome utente gia' esistente");
                throw new IllegalArgumentException("Nome utente gia' esistente");
            }
        }

        utenti.add(new Utente(nomeUtente, password));

        scriviUtenti(utenti);
    }

        public static boolean loginUtente(String username, String password) {
            try {
                List<Utente> utenti = leggiUtenti();
                for (Utente utente : utenti) {
                    if (utente.getNomeUtente().equals(username) && utente.verificaPassword(password)) {
                        loginCheck = true;
                        utenteCorrente = utente;
                        return true; // Login riuscito
                    }
                }
            } catch (IOException | NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            loginCheck = false;
            return false; 
        }


    public static void logoutUtente(){

        loginCheck = false;
        utenteCorrente = null;
        MainProgettoPlay.showWelcomeScene();
    }



    private static List<Utente> leggiUtenti() throws IOException {
        List<Utente> utenti = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_TXT))) {
            String line;
            while ((line = reader.readLine()) != null) {
                utenti.add(Utente.fromDataString(line));
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return utenti;
    }


    private static void scriviUtenti(List<Utente> utenti) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_TXT))) {
            for (Utente utente : utenti) {
                writer.write(utente.toDataString());
                writer.newLine();
            }
        }
    }

    public static void aggiornaPunteggioUtente(String username, String codiceEsercizio, int punteggio) throws IOException, NoSuchAlgorithmException {
        List<Utente> utenti = leggiUtenti();
        boolean found = false;
        for (Utente utente : utenti) {
            if (utente.getNomeUtente().equals(username)) {
                utente.setPunteggioSingolo(codiceEsercizio, punteggio);
                found = true;
                break;
            }
        }
        if (!found) {
            throw new IllegalArgumentException("Utente non trovato");
        }
        scriviUtenti(utenti);
    }

    //per aggiornare le progress bar in diretta
    public static void aggiornaPunteggioUtente(String username) throws IOException, NoSuchAlgorithmException {
        List<Utente> utenti = leggiUtenti();
        boolean found = false;
        for (Utente utente : utenti) {
            if (utente.getNomeUtente().equals(username)){
                found = true;
                break;
            }
        }
        if (!found) {
            throw new IllegalArgumentException("Utente non trovato");
        }
        scriviUtenti(utenti);
    }

    public static Utente getUtenteCorrente() {
        return utenteCorrente;
    }
}
