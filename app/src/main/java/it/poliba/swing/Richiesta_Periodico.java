package it.poliba.swing;

import java.util.Date;

import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Richiesta_Periodico extends Richiesta {




    public Richiesta_Periodico(String codRichiesta, int numPosti, String luogoPartenza, String luogoArrivo, Date dataPartenza, String codRich) {
        super(codRichiesta, numPosti, luogoPartenza, luogoArrivo, dataPartenza);
    }
}
