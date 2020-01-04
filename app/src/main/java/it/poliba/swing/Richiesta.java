package it.poliba.swing;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Richiesta extends RealmObject {
    @PrimaryKey
    private int codRichiesta;

    //@Required
    private String emailUtente;

    private int numPosti;
    @Required
    private String luogoPartenza;
    @Required
    private String luogoArrivo;
    @Required
    private String dataPartenza;
    @Required
    private String ora;



    public Richiesta() {
    }

    public int getCodRichiesta() {
        return codRichiesta;
    }

    public void setCodRichiesta(int codRichiesta) {
        this.codRichiesta = codRichiesta;
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
        this.luogoPartenza = luogoPartenza;
    }

    public String getLuogoArrivo() {
        return luogoArrivo;
    }

    public void setLuogoArrivo(String luogoArrivo) {
        this.luogoArrivo = luogoArrivo;
    }

    public String getDataPartenza() {
        return dataPartenza;
    }

    public String getMailUtente() {
        return emailUtente;
    }

    public void setMailUtente(String mailUtente) {
        this.emailUtente = mailUtente;
    }

    public void setDataPartenza(String dataPartenza) {
        this.dataPartenza = dataPartenza;
    }

    public String getOra() {
        return ora;
    }

    public void setOra(String ora) {
        this.ora = ora;
    }
}
