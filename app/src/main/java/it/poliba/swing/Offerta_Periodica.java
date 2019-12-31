package it.poliba.swing;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Offerta_Periodica extends RealmObject {

    @PrimaryKey
    private int codOfferta;
    @Required
    private String luogoPartenza;
    @Required
    private String luogoArrivo;
    @Required
    private String dataInizio;
    @Required
    RealmList<String> giorni = new RealmList<>();

    private String emailUtente;

    public Offerta_Periodica(int codOfferta, String luogoPartenza, String luogoArrivo, String dataInizio, RealmList<String> giorni) {
        this.codOfferta = codOfferta;
        this.luogoPartenza = luogoPartenza;
        this.luogoArrivo = luogoArrivo;
        this.dataInizio = dataInizio;
        this.giorni = giorni;
    }

    public Offerta_Periodica () {
    }

    public int getCodOfferta() {
        return codOfferta;
    }

    public void setCodOfferta(int codOfferta) {
        this.codOfferta = codOfferta;
    }

    public String getLuogoPartenza() {
        return luogoPartenza;
    }

    public void setLuogoPartenza(String luogoPartenza) {
        this.luogoPartenza = luogoPartenza;
    }

    public String getLuogoArrivo() {
        return luogoArrivo;
    }

    public void setLuogoArrivo(String luogoArrivo) {
        this.luogoArrivo = luogoArrivo;
    }

    public String getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(String dataInizio) {
        this.dataInizio = dataInizio;
    }

    public RealmList<String> getGiorni() {
        return giorni;
    }

    public void setGiorni(RealmList<String> giorni) {
        this.giorni = giorni;
    }

    public String getEmailUtente() {
        return emailUtente;
    }

    public void setEmailUtente(String emailUtente) {
        this.emailUtente = emailUtente;
    }

}
