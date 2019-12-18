package it.poliba.swing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmRecyclerViewAdapter;
import io.realm.RealmResults;
import io.realm.SyncConfiguration;
import io.realm.SyncUser;


public class OfferActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer);

        String syncServerURL = "https://swing-app.de1a.cloud.realm.io/temp3";
        final SyncConfiguration config = new SyncConfiguration.Builder(SyncUser.current(), syncServerURL).build();
        final Realm realm = Realm.getInstance(config);



        final EditText etLuogoPartenza = findViewById(R.id.etLuogoPartenzaO);
        final EditText etLuogoArrivo = findViewById(R.id.etLuogoArrivoO);
        final EditText etDataPartenza =findViewById(R.id.etDataPartenzaO);
        final EditText etOraPartenza = findViewById(R.id.etOraPartenzaO);
        final EditText etPrezzo = findViewById(R.id.etPrezzo);
        Button bOffri = findViewById(R.id.bOffri);
        RadioButton rbPeriodica = findViewById(R.id.radioPeriodica);




        final Intent periodica = new Intent(this, Offerta_Periodica.class);
        rbPeriodica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(periodica);
            }
        });

        bOffri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String prezzo = etPrezzo.getText().toString();
                float iprezzo = Float.parseFloat(prezzo);
                final Offerta o = new Offerta();



                double numerocodoff= Math.random()*99999999;
                o.setData(etDataPartenza.getText().toString());
                o.setCodOfferta((int) numerocodoff);
                o.setLuogoPartenza(etLuogoPartenza.getText().toString());
                o.setLuogoArrivo(etLuogoArrivo.getText().toString());
                o.setPrezzo(iprezzo);
                o.setOra(etOraPartenza.getText().toString());

                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        realm.copyFromRealm(o);
                    }
                });

            }
        });


    }
    }