package it.poliba.swing;

import java.util.Date;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Richiesta_Periodico extends RealmObject {
    @PrimaryKey
    private int CodRichiesta;

    //@Required
    private String mailUtente;

    private int numPosti;
    @Required
    private String LuogoPartenza;
    @Required
    private String LuogoArrivo;
    @Required
    private String dataPartenza;
    @Required
    RealmList<String> giorni = new RealmList<>();

    public Richiesta_Periodico(int codRichiesta, String mailUtente, int numPosti, String luogoPartenza, String luogoArrivo, String dataPartenza, RealmList<String> giorni) {
        CodRichiesta = codRichiesta;
        this.mailUtente = mailUtente;
        this.numPosti = numPosti;
        LuogoPartenza = luogoPartenza;
        LuogoArrivo = luogoArrivo;
        this.dataPartenza = dataPartenza;
        this.giorni = giorni;
    }

    public Richiesta_Periodico() {
    }

    public RealmList<String> getGiorni() {
        return giorni;
    }

    public void setGiorni(RealmList<String> giorni) {
        this.giorni = giorni;
    }


    public int getCodRichiesta() {
        return CodRichiesta;
    }

    public void setCodRichiesta(int codRichiesta) {
        CodRichiesta = codRichiesta;
    }

    public String getMailUtente() {
        return mailUtente;
    }

    public void setMailUtente(String mailUtente) {
        this.mailUtente = mailUtente;
    }

    public int getNumPosti() {
        return numPosti;
    }

    public void setNumPosti(int numPosti) {
        this.numPosti = numPosti;
    }

    public String getLuogoPartenza() {
        return LuogoPartenza;
    }

    public void setLuogoPartenza(String luogoPartenza) {
        LuogoPartenza = luogoPartenza;
    }

    public String getLuogoArrivo() {
        return LuogoArrivo;
    }

    public void setLuogoArrivo(String luogoArrivo) {
        LuogoArrivo = luogoArrivo;
    }

    public String getDataPartenza() {
        return dataPartenza;
    }

    public void setDataPartenza(String dataPartenza) {
        this.dataPartenza = dataPartenza;
    }
}
