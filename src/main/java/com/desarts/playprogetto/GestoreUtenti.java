package com.desarts.playprogetto;

import java.io.*;
import java.util.*;
import java.io.IOException;


public class GestoreUtenti {

    static boolean loginCheck = false;
    static Utente utenteCorrente = null;

    //crea un file dove verranno inseriti gli utenti
    private static final String FILE_TXT = "src/main/resources/com/desarts/playprogetto/listaUtenti.txt";
    public static void registraUtente(String nomeUtente, String password) throws IOException {
        List<Utente> utenti = leggiUtenti();

        // Verifica se l'utente esiste gia'
        for (Utente utente : utenti) {
            if (utente.getNomeUtente().equals(nomeUtente)) {
                throw new IllegalArgumentException("Nome utente gia' esistente");
            }
        }

        // Aggiungi nuovo utente
        utenti.add(new Utente(nomeUtente, password));

        // Scrivi la lista aggiornata nel file TXT
        scriviUtenti(utenti);
    }

    // Metodo per effettuare il login
    public static boolean loginUtente(String username, String password) throws IOException {
        List<Utente> utenti = leggiUtenti();
        for (Utente utente : utenti) {
            if (utente.getNomeUtente().equals(username) && utente.getPassword().equals(password)) {
                loginCheck = true;
                utenteCorrente = utente;
                return true; // Login riuscito
            }
        }
        loginCheck = false;
        return false; // Credenziali non valide
    }

    public static void logoutUtente(){
        loginCheck = false;
        //aggiungere metodo di salvataggio
        utenteCorrente = null;
        MainProgettoPlay.showWelcomeScene();
    };



    private static List<Utente> leggiUtenti() throws IOException {
        List<Utente> utenti = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_TXT))) {
            String line;
            while ((line = reader.readLine()) != null) {
                utenti.add(Utente.fromDataString(line));
            }
        }
        return utenti;
    }


    //aggiungo l'utente alla lista
    private static void scriviUtenti(List<Utente> utenti) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_TXT))) {
            for (Utente utente : utenti) {
                writer.write(utente.toDataString());
                writer.newLine();
            }
        }
    }

    //aggiorna punteggio
    public static void aggiornaPunteggio(String nomeUtente, String codiceEsercizio, int punteggio) throws IOException {
        List<Utente> utenti = leggiUtenti();
        for (Utente utente : utenti) {
            if (utente.getNomeUtente().equals(nomeUtente)) {
                utente.setPunteggio(codiceEsercizio, punteggio);
                scriviUtenti(utenti);
            }
        }
        throw new IllegalArgumentException("Utente non trovato");
    }

}