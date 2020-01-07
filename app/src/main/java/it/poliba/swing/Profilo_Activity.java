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

        final Intent a = new Intent(this, ModficaProfilo_Activity.class);
        Utente utente = new Utente();
        TextView e = findViewById(R.id.em);
        TextView c = findViewById(R.id.co);
        TextView n = findViewById(R.id.no);
        TextView d = findViewById(R.id.da);
        TextView pa = findViewById(R.id.pa);
        Button modifica = findViewById(R.id.modifica);


        // cofigurazione del DB nell'activity
        String syncServerURL = "https://swingdatabase.de1a.cloud.realm.io/temp12";
        final SyncConfiguration config = new SyncConfiguration.Builder(SyncUser.current(), syncServerURL).build();
        final Realm realm = Realm.getInstance(config);

        //ricezione dati utete loggato dalla Home
        Intent b = getIntent();
        Bundle b1 = b.getExtras();
        if (b1 != null) {
            utente = b1.getParcelable("object_key");
        }

        //Leggo i dati aggiornati dell'utente loggato
        RealmQuery<Utente> query = realm.where(Utente.class);
        query.equalTo("email", utente.getEmail());
        final RealmResults<Utente> newUtente = query.findAll();

        //setto il testo delle text view
        e.setText(newUtente.get(0).getEmail());
        c.setText(newUtente.get(0).getCognome());
        n.setText(newUtente.get(0).getNome());
        d.setText(newUtente.get(0).getDataNascita());
        pa.setText(newUtente.get(0).getPassword());



        modifica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle b1 = new Bundle();
                b1.putParcelable("object_key", newUtente.get(0));
                a.putExtras(b1);
                startActivity(a);
                finish();
            }
        });



    }
}
