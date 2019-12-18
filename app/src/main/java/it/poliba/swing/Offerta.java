package it.poliba.swing;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.Date;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.view.View;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Offerta extends RealmObject {
    @PrimaryKey
    private int codOfferta;
    @Required
    private String luogoPartenza;
    @Required
    private String luogoArrivo;
    @Required
    private String data;
    @Required
    private String ora;
    @Required
    private String emailUtente;

    private double prezzo;

    private int numPostiDisponibili;

    public Offerta() {
    }


    public int getCodOfferta() {
        return codOfferta;
    }

    public String getLuogoPartenza() {
        return luogoPartenza;
    }

    public String getLuogoArrivo() {
        return luogoArrivo;
    }

    public String getData() {
        return data;
    }


    public void setCodOfferta(int codOfferta) {
        this.codOfferta = codOfferta;
    }

    public void setLuogoPartenza(String luogoPartenza) {
        this.luogoPartenza = luogoPartenza;
    }

    public void setLuogoArrivo(String luogoArrivo) {
        this.luogoArrivo = luogoArrivo;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public String getOra() {
        return ora;
    }

    public void setOra(String ora) {
        this.ora = ora;
    }

    public int getNumPostiDisponibili() {
        return numPostiDisponibili;
    }


    public String getEmailUtente() {
        return emailUtente;
    }

    public void setEmailUtente(String emailUtente) {
        this.emailUtente = emailUtente;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setNumPostiDisponibili(int numPostiDisponibili) {
        this.numPostiDisponibili = numPostiDisponibili;
    }
}