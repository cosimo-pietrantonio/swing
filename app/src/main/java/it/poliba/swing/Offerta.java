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
    private String  luogoPartenza;
    @Required
    private String luogoArrivo;
    @Required
    private Date data;
    @Required
    private String ora;


    private float prezzo;

    public Offerta() {
    }

    public Offerta(int codOfferta, String luogoPartenza, String luogoArrivo, Date data, float prezzo) {
        this.codOfferta = codOfferta;
        this.luogoPartenza = luogoPartenza;
        this.luogoArrivo = luogoArrivo;
        this.data = data;
        this.prezzo = prezzo;
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

    public Date getData() {
        return data;
    }

    public float getPrezzo() {
        return prezzo;
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

    public void setData(Date data) {
        this.data = data;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }

    public String getOra() {
        return ora;
    }

    public void setOra(String ora) {
        this.ora = ora;
    }
}
