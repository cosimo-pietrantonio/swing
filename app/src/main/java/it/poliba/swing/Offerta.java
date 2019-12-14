package it.poliba.swing;

import android.os.Bundle;
import java.sql.*;
import java.util.ArrayList;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.view.View;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Offerta extends RealmObject {
    @PrimaryKey
    private int codOfferta;
    @Required
    private ArrayList<String> luogoPartenza;
    @Required
    private String luogoArrivo;
    @Required
    private Date data;
    @Required
    private Time ora;
    @Required
    private float prezzo;
    @Required
    private boolean periodico;

}
