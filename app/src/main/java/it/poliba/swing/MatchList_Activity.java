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

        final TextView add = new TextView(MatchList_Activity.this);
        final LinearLayout offerte = findViewById(R.id.l2);
        final LinearLayout richieste = findViewById(R.id.l3);
        Boolean bitOff=false;
        Boolean bitRic=true;
        utente ut1 = new utente();
        ArrayList<Offerta> offerteSingoleDelMatch = new ArrayList<>();




        String syncServerURL = "https://swing-app.de1a.cloud.realm.io/temp10";
        final SyncConfiguration config = new SyncConfiguration.Builder(SyncUser.current(), syncServerURL).build();
        final Realm realm = Realm.getInstance(config);




        if(bitRic == true){
            add.setText("Bari-noci   ore: 15:00  ciao ciao");
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
            offerte.addView(add);
        }

        if(bitOff == true){
            add.setText("Bari-noci   ore: 15:00  ciao ciao");
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
            offerte.addView(add);
        }


        // RICEZIONE RICHIESTE ATTIVE
        RealmResults<Richiesta> richiesteSingAttive = getRichiesteSingole(ut1.getEmail(),realm);
        RealmResults<Richiesta_Periodica> richiestePeriodAttive = getRichiestePeriodiche(ut1.getEmail(),realm);

        // RICEZIONE OFFERTE ATTIVE
        RealmResults<Offerta> offerteSingAttive = getOfferteSingole(ut1.getEmail(),realm);
        RealmResults<Offerta_Periodica> offertePeriodAttive = getOffertePeriodiche(ut1.getEmail(),realm);

        // MATCH PER RICHIESTE SINGOLE ATTIVE
        for (int i=0; i < richiesteSingAttive.size(); i++){
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
        RealmQuery querySing =realm.where(Offerta.class).equalTo("mailUtente",mailUtente);
        return querySing.findAll();
    }

    public RealmResults<Offerta_Periodica> getOffertePeriodiche(String mailUtente, Realm realm){
        RealmQuery queryPeriod = realm.where(Offerta_Periodica.class).equalTo("mailUtente",mailUtente);
        return queryPeriod.findAll();
    }
}
