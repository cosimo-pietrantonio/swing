package it.poliba.swing;

import java.util.Date;

import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Richiesta_Periodico extends Richiesta {


    boolean giorniRichiesti[];




    public Richiesta_Periodico(int codRichiesta, String mailUtente, int numPosti, String luogoPartenza, String luogoArrivo, Date dataPartenza) {
        super(codRichiesta, mailUtente, numPosti, luogoPartenza, luogoArrivo, dataPartenza);
    }

    public Richiesta_Periodico() {
    }


}
