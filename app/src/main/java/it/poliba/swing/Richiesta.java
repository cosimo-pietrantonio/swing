package it.poliba.swing;

import java.sql.Time;
import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Richiesta extends RealmObject {
    @PrimaryKey
    private String CodRichiesta;
    @Required
    private int numPosti;
    @Required
    private String LuogoPartenza;
    @Required
    private String LuogoArrivo;
    @Required
    private Date dataPartenza;
    @Required
    private Time oraPartenza;




}
