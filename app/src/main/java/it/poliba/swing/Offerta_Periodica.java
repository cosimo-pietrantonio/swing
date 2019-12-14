package it.poliba.swing;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class Offerta_Periodica extends Offerta {
    public Offerta_Periodica(int codOfferta, ArrayList<String> luogoPartenza, String luogoArrivo, Date data, Time ora, float prezzo) {
        super(codOfferta, luogoPartenza, luogoArrivo, data, ora, prezzo);
    }
}
