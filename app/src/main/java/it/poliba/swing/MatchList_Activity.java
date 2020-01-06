package it.poliba.swing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.SyncConfiguration;
import io.realm.SyncUser;

public class MatchList_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_list_);


        final LinearLayout offerteATT = findViewById(R.id.l);
        final LinearLayout offerteTRO = findViewById(R.id.l4);
        final LinearLayout richiesteATT = findViewById(R.id.l3);
        final LinearLayout richiesteTRO = findViewById(R.id.l2);
        Boolean bitOff=false;
        Boolean bitRic=true;
        Utente ut1 = new Utente();
        ArrayList<Offerta> offerteSingoleDelMatch = new ArrayList<>();


        //ricezione dati utete loggato
        Intent getter = getIntent();
        Bundle b1 = getter.getExtras();
        if (b1 != null) {
            ut1 = b1.getParcelable("object_key");
        }

        String syncServerURL = "https://swingdb.de1a.cloud.realm.io/temp12";
        final SyncConfiguration config = new SyncConfiguration.Builder(SyncUser.current(), syncServerURL).build();
        final Realm realm = Realm.getInstance(config);




        // RICEZIONE RICHIESTE ATTIVE
        RealmResults<Richiesta> richiesteSingAttive = getRichiesteSingole(ut1.getEmail(),realm);
        RealmResults<Richiesta_Periodica> richiestePeriodAttive = getRichiestePeriodiche(ut1.getEmail(),realm);


        //AGGIUNTA DI RICHIESTE SINGOLE ATTIVE AL LAYOUT
        for(int i=0; i<richiesteSingAttive.size(); i++){
            final TextView add = new TextView(MatchList_Activity.this);
            add.setText(richiesteSingAttive.get(i).getLuogoPartenza()+ "-" + richiesteSingAttive.get(i).getLuogoArrivo() + " " + richiesteSingAttive.get(i).getOra() + " " + richiesteSingAttive.get(i).getDataPartenza());
            add.setPadding(5,5,0,15);
            add.setTextColor(Color.BLACK);
            add.setTextSize(25);
            add.setBackgroundColor(Color.WHITE);
            add.setFocusable(true);
            add.setSingleLine(true);
            add.setEllipsize(TextUtils.TruncateAt.MARQUEE);
            add.setMarqueeRepeatLimit(100);
            add.setFocusable(true);
            add.setFocusableInTouchMode(true);
            add.setHorizontallyScrolling(true);
            add.setSelected(true);
            richiesteATT.addView(add);
        }



        //AGGIUNTA DI RICHIESTE periodiche ATTIVE AL LAYOUT
        for(int i=0; i<richiestePeriodAttive.size(); i++){
            final TextView add = new TextView(MatchList_Activity.this);
            String gio= "";
            for(int j=0; j<richiestePeriodAttive.get(i).getGiorni().size();j++){
                gio = gio + " " + richiestePeriodAttive.get(i).getGiorni().get(j);
            }

            String temp= richiestePeriodAttive.get(i).getLuogoPartenza()+ "-" + richiestePeriodAttive.get(i).getLuogoArrivo()+ " " + gio ;
            add.setText(temp);
            add.setPadding(5,5,0,15);
            add.setTextColor(Color.BLACK);
            add.setTextSize(25);
            add.setBackgroundColor(Color.WHITE);
            add.setFocusable(true);
            add.setSingleLine(true);
            add.setEllipsize(TextUtils.TruncateAt.MARQUEE);
            add.setMarqueeRepeatLimit(100);
            add.setFocusable(true);
            add.setFocusableInTouchMode(true);
            add.setHorizontallyScrolling(true);
            add.setSelected(true);
            richiesteATT.addView(add);
        }




        // RICEZIONE OFFERTE ATTIVE
        RealmResults<Offerta> offerteSingAttive = getOfferteSingole(ut1.getEmail(),realm);
        RealmResults<Offerta_Periodica> offertePeriodAttive = getOffertePeriodiche(ut1.getEmail(),realm);

        //AGGIUNTA DI OFFERTE SINGOLE ATTIVE AL LAYOUT
        if(offerteSingAttive.size()!=0){
            for(int i=1; i<offerteSingAttive.size(); i++){
                final TextView add = new TextView(MatchList_Activity.this);
                add.setText(offerteSingAttive.get(i).getLuogoPartenza()+ "-" + offerteSingAttive.get(i).getLuogoArrivo() + " " + offerteSingAttive.get(i).getOra() + " " + offerteSingAttive.get(i).getData());
                add.setPadding(5,5,0,15);
                add.setTextColor(Color.BLACK);
                add.setTextSize(25);
                add.setBackgroundColor(Color.WHITE);
                add.setFocusable(true);
                add.setSingleLine(true);
                add.setEllipsize(TextUtils.TruncateAt.MARQUEE);
                add.setMarqueeRepeatLimit(100);
                add.setFocusable(true);
                add.setFocusableInTouchMode(true);
                add.setHorizontallyScrolling(true);
                add.setSelected(true);
                offerteATT.addView(add);
            }
        }


        //AGGIUNTA DI OFFERTE PERIODICHE ATTIVE AL LAYOUT
        if(offertePeriodAttive.size()!=0){
            for(int i=1; i<offertePeriodAttive.size(); i++){
                final TextView add = new TextView(MatchList_Activity.this);
                String gio= "";
                for(int j=0; j<offertePeriodAttive.get(i).getGiorni().size();j++){
                    gio = gio + " " + offertePeriodAttive.get(i).getGiorni().get(j);
                }

                add.setText(offertePeriodAttive.get(i).getLuogoPartenza()+ "-" + offertePeriodAttive.get(i).getLuogoArrivo() + " " + gio);
                add.setPadding(5,5,0,15);
                add.setTextColor(Color.BLACK);
                add.setTextSize(25);
                add.setBackgroundColor(Color.WHITE);
                add.setFocusable(true);
                add.setSingleLine(true);
                add.setEllipsize(TextUtils.TruncateAt.MARQUEE);
                add.setMarqueeRepeatLimit(100);
                add.setFocusable(true);
                add.setFocusableInTouchMode(true);
                add.setHorizontallyScrolling(true);
                add.setSelected(true);
                offerteATT.addView(add);
            }
        }




        // MATCH PER RICHIESTE SINGOLE ATTIVE
        for (int i=1; i < richiesteSingAttive.size(); i++){
            Richiesta r = richiesteSingAttive.get(i);
            final RealmQuery<Offerta> queryMatchSingolo = realm.where(Offerta.class)
                    .equalTo("luogoPartenza", r.getLuogoPartenza())
                    .equalTo("luogoArrivo", r.getLuogoArrivo());
                if (queryMatchSingolo.equalTo("data", r.getDataPartenza()).count() != 0) {

                    final RealmResults<Offerta> queryRes = queryMatchSingolo.equalTo("data", r.getDataPartenza()).findAll();
                    int intPostiRichiesta = r.getNumPosti();
                    for (int j = 0; j < queryRes.size(); j++) {
                        if (queryRes.get(j).getNumPostiDisponibili() >= intPostiRichiesta) {
                            offerteSingoleDelMatch.add(queryRes.get(j));
                        }
                    }
                }
        }

        //  MATCH PER RICHIESTE PERIODICHE ATTIVE
        for(int k=0; k < richiestePeriodAttive.size(); k++){
            Richiesta_Periodica rp = richiestePeriodAttive.get(k);

        }


    }



    public RealmResults<Richiesta> getRichiesteSingole(String mailUtente, Realm realm){
        RealmQuery querySing = realm.where(Richiesta.class).equalTo("mailUtente",mailUtente);
        return querySing.findAll();
    }

    public RealmResults<Richiesta_Periodica> getRichiestePeriodiche (String mailUtente, Realm realm){
        RealmQuery queryPeriod = realm.where(Richiesta_Periodica.class).equalTo("mailUtente",mailUtente);
        return queryPeriod.findAll();

    }

    public RealmResults<Offerta> getOfferteSingole(String mailUtente, Realm realm){
        RealmQuery querySing =realm.where(Offerta.class).equalTo("emailUtente",mailUtente);
        return querySing.findAll();
    }

    public RealmResults<Offerta_Periodica> getOffertePeriodiche(String mailUtente, Realm realm){
        RealmQuery queryPeriod = realm.where(Offerta_Periodica.class).equalTo("emailUtente",mailUtente);
        return queryPeriod.findAll();
    }
}
