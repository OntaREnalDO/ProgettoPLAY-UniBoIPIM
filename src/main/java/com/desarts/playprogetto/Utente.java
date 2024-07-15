package com.desarts.playprogetto;

public class Utente {
    private static String nomeUtente;
    private String password;
    private int punteggio;

    // Costruttore per creare un utente appena registrato con punteggio 0
            public Utente(String nomeUtente, String password, int punteggio) {
        this.nomeUtente = nomeUtente;
        this.password = password;
        this.punteggio = 0;
    }



    // Getter e Setter
    public String getNomeUtente() {
        return nomeUtente;
    }

    public String getPassword() {
        return password;
    }

    public int getPunteggio() {
        return punteggio;
    }

    public void setPunteggio(int punteggio) {
        this.punteggio = punteggio;
    }


    @Override
    public String toString() {
            return nomeUtente + ", " + password + ", " + punteggio;
        }
    }
