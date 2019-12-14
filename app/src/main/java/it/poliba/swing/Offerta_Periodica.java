package it.poliba.swing;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import io.realm.RealmList;

public class Offerta_Periodica extends Offerta {
    public Offerta_Periodica(int codOfferta, RealmList luogoPartenza, String luogoArrivo, Date data, Time ora, float prezzo) {
        super(codOfferta, luogoPartenza, luogoArrivo, data, ora, prezzo);


    }
}
