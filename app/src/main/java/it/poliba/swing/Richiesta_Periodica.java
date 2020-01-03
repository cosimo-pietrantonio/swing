package it.poliba.swing;

import java.util.Date;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Richiesta_Periodica extends RealmObject {
    @PrimaryKey
    private int codRichiesta;
    //@Required
    private String mailUtente;

    private int numPosti;
    @Required
    private String luogoPartenza;
    @Required
    private String luogoArrivo;
    @Required
    private String dataPartenza;
    @Required
    RealmList<String> giorni = new RealmList<>();

    public Richiesta_Periodica(int codRichiesta, String mailUtente, int numPosti, String luogoPartenza, String luogoArrivo, String dataPartenza, RealmList<String> giorni) {
        this.codRichiesta = codRichiesta;
        this.mailUtente = mailUtente;
        this.numPosti = numPosti;
        this.luogoPartenza = luogoPartenza;
        this.luogoArrivo = luogoArrivo;
        this.dataPartenza = dataPartenza;
        this.giorni = giorni;
    }

    public Richiesta_Periodica() {
    }

    public RealmList<String> getGiorni() {
        return giorni;
    }

    public void setGiorni(RealmList<String> giorni) {
        this.giorni = giorni;
    }


    public int getCodRichiesta() {
        return codRichiesta;
    }

    public void setCodRichiesta(int codRichiesta) {
        codRichiesta = codRichiesta;
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
        return luogoPartenza;
    }

    public void setLuogoPartenza(String luogoPartenza) {
        luogoPartenza = luogoPartenza;
    }

    public String getLuogoArrivo() {
        return luogoArrivo;
    }

    public void setLuogoArrivo(String luogoArrivo) {
        luogoArrivo = luogoArrivo;
    }

    public String getDataPartenza() {
        return dataPartenza;
    }

    public void setDataPartenza(String dataPartenza) {
        this.dataPartenza = dataPartenza;
    }

}
