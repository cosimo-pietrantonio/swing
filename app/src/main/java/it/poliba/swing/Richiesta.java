package it.poliba.swing;

import java.sql.Time;
import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Richiesta extends RealmObject {
    @PrimaryKey
    private int CodRichiesta;

    //@Required
    private String mailUtente;
    @Required
    private int numPosti;
    @Required
    private String LuogoPartenza;
    @Required
    private String LuogoArrivo;

    private Date dataPartenza;

    public Richiesta(int codRichiesta, String mailUtente, int numPosti, String luogoPartenza, String luogoArrivo, Date dataPartenza) {
        CodRichiesta = codRichiesta;
        this.mailUtente = mailUtente;
        this.numPosti = numPosti;
        LuogoPartenza = luogoPartenza;
        LuogoArrivo = luogoArrivo;
        this.dataPartenza = dataPartenza;
    }

    public Richiesta() {
    }

    public int getCodRichiesta() {
        return CodRichiesta;
    }

    public void setCodRichiesta(int codRichiesta) {
        CodRichiesta = codRichiesta;
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

    public Date getDataPartenza() {
        return dataPartenza;
    }

    public void setDataPartenza(Date dataPartenza) {
        this.dataPartenza = dataPartenza;
    }
}