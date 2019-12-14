package it.poliba.swing;

import java.util.Date;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Richiesta_Periodico extends Richiesta {
    @Required
    RealmList<String> giorni = new RealmList<>();


    public Richiesta_Periodico(int codRichiesta, int numPosti, String luogoPartenza, String luogoArrivo, String dataPartenza, RealmList<String> giorni) {
        super(codRichiesta, numPosti, luogoPartenza, luogoArrivo, dataPartenza);
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
}
