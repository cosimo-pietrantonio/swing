package it.poliba.swing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.SyncConfiguration;
import io.realm.SyncUser;

public class Profilo_Activity extends AppCompatActivity implements io.realm.RealmModel{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilo);

        // cofigurazione del DB nell'activity
        String syncServerURL = "https://swing-app.de1a.cloud.realm.io/temp12";
        final SyncConfiguration config = new SyncConfiguration.Builder(SyncUser.current(), syncServerURL).build();
        final Realm realm = Realm.getInstance(config);

        final Intent a = new Intent(this, ModficaProfilo_Activity.class);
        Utente utente = new Utente();
        final Utente U =new Utente();
        TextView e = findViewById(R.id.em);
        TextView c = findViewById(R.id.co);
        TextView n = findViewById(R.id.no);
        TextView d = findViewById(R.id.da);
        TextView pa = findViewById(R.id.pa);
        Button modifica = findViewById(R.id.modifica);


        //ricezione dati utete loggato
        Intent b = getIntent();
        Bundle b1 = b.getExtras();
        if (b1 != null) {
            utente = b1.getParcelable("object_key");
        }

        //aggiorno i dati dell'utente
        RealmQuery<Utente> query = realm.where(Utente.class);
        query.equalTo("email", utente.getEmail());
        final RealmResults<Utente> result = query.findAll();
        utente.setCognome(result.get(0).getCognome());
        utente.setNome(result.get(0).getNome());
        utente.setDataNascita(result.get(0).getDataNascita());
        utente.setPassword(result.get(0).getPassword());


        //lettura richieste e offerte ad esso associato
        RealmQuery<Richiesta> query1 = realm.where(Richiesta.class);
        RealmQuery<Offerta> query2 = realm.where(Offerta.class);
        //query1.equalTo("email", Utente.getEmail());
        //query2.equalTo("email", Utente.getEmail());
        final RealmResults<Richiesta> result1 = query1.findAll();
        final RealmResults<Offerta> result2 = query2.findAll();


        e.setText(utente.getEmail());
        c.setText(utente.getCognome());
        n.setText(utente.getNome());
        d.setText(utente.getDataNascita());
        pa.setText("Password: "+utente.getPassword());

        U.setEmail(utente.getEmail());
        U.setDataNascita(utente.getDataNascita());
        U.setNome(utente.getNome());
        U.setCognome(utente.getCognome());
        U.setPassword(utente.getPassword());


        modifica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle b1 = new Bundle();
                b1.putParcelable("object_key", U);
                a.putExtras(b1);
                startActivity(a);
                finish();
            }
        });



    }
}
