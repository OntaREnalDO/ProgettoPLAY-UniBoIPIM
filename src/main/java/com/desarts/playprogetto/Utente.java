package com.desarts.playprogetto;

import java.util.HashMap;
import java.util.Map;

public class Utente {
    private String nomeUtente;
    private String password;
    private Map<String, Integer> punteggi;

    // Costruttore per creare un utente appena registrato con punteggio 0
            public Utente(String nomeUtente, String password) {
        this.nomeUtente = nomeUtente;
        this.password = password;
        this.punteggi = new HashMap<>();
    }

//costruttore di utente con dati gia registrati
    public Utente(String nomeUtente, String password, Map<String, Integer> punteggi) {
        this.nomeUtente = nomeUtente;
        this.password = password;
        this.punteggi = punteggi;
    }

    // Getters e Setters
    public String getNomeUtente() {
        return nomeUtente;
    }

    public String getPassword() {
        return password;
    }

    public Map<String, Integer> getPunteggio() {
        return punteggi;
    }

    public int getPunteggioSingolo(String codiceEsercizio){
                return punteggi.get(codiceEsercizio);
    }

    public void setPunteggio(String codiceEsercizio, int punteggio) {

                this.punteggi.put(codiceEsercizio, punteggio);
    }

    //convertire i dati dell'utente in una stringa per la scrittura su file
    public String toDataString() {
            StringBuilder sb = new StringBuilder();
            sb.append(nomeUtente).append(",").append(password);
            //iteriamo su ogni entry della mappa
            for (Map.Entry<String, Integer> entry : punteggi.entrySet()) {
                sb.append(",").append(entry.getKey()).append(":").append(entry.getValue());
            }
                return sb.toString();
        }

        //creare un utente a partire da una stringa di dati letta da file
        public static Utente fromDataString(String dataString) {
                String[] data = dataString.split(",");
                String nomeUtente = data[0];
                String password = data[1];
                Utente utente = new Utente(nomeUtente, password);
                for(int i=2;i<data.length; i++){
                    String[] punteggioData = data[i].split(",");
                    utente.setPunteggio(punteggioData[0], Integer.parseInt(punteggioData[1]));
                }
                return utente;
        }


    }
