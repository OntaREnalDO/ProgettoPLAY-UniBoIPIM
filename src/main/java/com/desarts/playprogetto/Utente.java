package com.desarts.playprogetto;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


public class Utente {
    private String nomeUtente;
    private String passwordHash;
    private String salt;//per decoding della password
    private Map<String, Integer> punteggi;


    // Costruttore per creare un utente appena registrato con punteggio 0 per ogni esercizio
    public Utente(String nomeUtente, String password) throws NoSuchAlgorithmException {
        this.nomeUtente = nomeUtente;
        this.salt = generateSalt();  // Genera un nuovo salt per ogni nuovo utente
        this.passwordHash = hashPassword(password, this.salt);  // Usa il salt con password per crittografare
        this.punteggi = inizializzaLinkedHashMap();
    }

    //uso hashmap ordinata
    private Map<String, Integer> inizializzaLinkedHashMap() {
        Map<String, Integer> codiciEsercizi = new LinkedHashMap<>(); // Usa LinkedHashMap per mantenere l'ordine
        char[] levels = {'A', 'B', 'C', 'D'}; // I livelli di difficoltà
        for (char level : levels) {
            for (int i = 1; i <= 3; i++) {
                codiciEsercizi.put("" + level + i, 0); // Crea chiavi da "A1"a"D3"
            }
        }
        return codiciEsercizi;
    }

    // Costruttore per utenti con dati già registrati
    public Utente(String nomeUtente, String passwordHash, String salt, Map<String, Integer> punteggi) {
        this.nomeUtente = nomeUtente;
        this.passwordHash = passwordHash;
        this.salt = salt;
        this.punteggi = new HashMap<>(punteggi);
    }


    // Metodo per crittografare la password
    private String hashPassword(String password, String salt) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(Base64.getDecoder().decode(salt));  // Aggiunge salt al digest
        byte[] hashedBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(hashedBytes);
    }


    // Genera un salt
    public static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    // Verifica la password inserita con l'hash memorizzato
    public boolean verificaPassword(String passwordInput) throws NoSuchAlgorithmException {
        String inputHash = hashPassword(passwordInput, this.salt);
        return this.passwordHash.equals(inputHash);
    }


    // Getters e Setters
    public String getNomeUtente() {
        return nomeUtente;
    }

    public String getPassword() {
        return passwordHash;
    }

    public Map<String, Integer> getPunteggi() {
        return punteggi;
    }

    public int getPunteggioSingolo(String codiceEsercizio) {
        return punteggi.getOrDefault(codiceEsercizio, 0);

    }

    public void setPunteggioSingolo(String codiceEsercizio, int punteggio) {
        this.punteggi.put(codiceEsercizio, punteggio);
    }

    // Converti i dati dell'utente in una stringa per la scrittura su file
    public String toDataString() {
        StringBuilder sb = new StringBuilder();
        sb.append(nomeUtente).append(",").append(passwordHash).append(",").append(salt);
        for (Map.Entry<String, Integer> entry : punteggi.entrySet()) {
            sb.append(",").append(entry.getKey()).append(":").append(entry.getValue());
        }
        return sb.toString();
    }


    // Crea un utente da una stringa di dati letta da file
    public static Utente fromDataString(String dataString) throws NoSuchAlgorithmException {
        String[] data = dataString.split(",");
        if (data.length < 3) {
            throw new IllegalArgumentException("Formato dati non valido: dati insufficienti.");
        }
        String nomeUtente = data[0];
        String passwordHash = data[1];
        String salt = data[2];
        Map<String, Integer> punteggi = new HashMap<>();

        for (int i = 3; i < data.length; i++) {
            String[] punteggioData = data[i].split(":");
            punteggi.put(punteggioData[0], Integer.parseInt(punteggioData[1]));
        }

        return new Utente(nomeUtente, passwordHash, salt, punteggi);
    }
}