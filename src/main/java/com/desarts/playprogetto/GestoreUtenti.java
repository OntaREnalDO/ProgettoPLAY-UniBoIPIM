package com.desarts.playprogetto;

import java.io.*;
import java.util.*;
import java.io.IOException;


public class GestoreUtenti {

    private static boolean login = false;

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
        utenti.add(new Utente(nomeUtente, password, 0));

        // Scrivi la lista aggiornata nel file TXT
        scriviUtenti(utenti);
    }

    // Metodo per effettuare il login
    public static boolean loginUtente(String username, String password) throws IOException {
        List<Utente> utenti = leggiUtenti();
        for (Utente utente : utenti) {
            if (utente.getNomeUtente().equals(username) && utente.getPassword().equals(password)) {
                login = true;
                return true; // Login riuscito
            }
        }
        login = false;
        return false; // Credenziali non valide
    }

    public static void loginUtente(){
        login = false;
        MainProgettoPlay.showWelcomeScene();
    }


    private static List<Utente> leggiUtenti() throws IOException {
        List<Utente> utenti = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_TXT))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String nomeUtente = data[0];
                String password = data[1];
                int punteggio = Integer.parseInt(data[2]);
                utenti.add(new Utente(nomeUtente, password, punteggio));
            }
        }

        return utenti;
    }


    //aggiungo l'utente alla lista
    private static void scriviUtenti(List<Utente> utenti) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_TXT))) {
            for (Utente utente : utenti) {
                String data = utente.getNomeUtente() + "," + utente.getPassword() + "," + utente.getPunteggio();
                writer.write(data);
                writer.newLine();
            }
        }
    }
}