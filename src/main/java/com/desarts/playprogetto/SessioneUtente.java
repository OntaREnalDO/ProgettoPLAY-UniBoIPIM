package com.desarts.playprogetto;

public class SessioneUtente {

    private static Utente utenteCorrente;

    public static Utente getUtenteCorrente(){
        return utenteCorrente;
    }

    public static void setUtenteCorrente(Utente utente){
        utenteCorrente = utente;
    }


    //metodo che cambia punteggio di utente con setPunteggio

}
