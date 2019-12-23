package it.poliba.swing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import io.realm.Realm;
import io.realm.SyncConfiguration;
import io.realm.SyncUser;

public class OffertaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offerta);
        String syncServerURL = "https://swing-app.de1a.cloud.realm.io/temp7";
        final SyncConfiguration config = new SyncConfiguration.Builder(SyncUser.current(), syncServerURL).build();
        final Realm realm = Realm.getInstance(config);

        final EditText etLuogoPartenza = findViewById(R.id.etLuogoPartenzaO);
        final EditText etLuogoArrivo = findViewById(R.id.etLuogoArrivoO);
        final EditText etDataPartenza =findViewById(R.id.etDataPartenzaO);
        final EditText etOraPartenza = findViewById(R.id.etOraPartenzaO);
        final EditText etPrezzo = findViewById(R.id.etPrezzo);
        final EditText etPosti  = findViewById(R.id.etPostiDisp);
        final Button bOffri = findViewById(R.id.bOffri);
        RadioButton rbPeriodica;
        rbPeriodica = findViewById(R.id.radioPeriodica);


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
                final double dprezzo = Double.parseDouble(prezzo);


                String posti = etPosti.getText().toString();
                final int iposti = Integer.parseInt(posti);


                final Offerta o = new Offerta();
                double numerocodoff = Math.random() * 99999999;
                final String data = etDataPartenza.getText().toString();
                final int cod = (int) numerocodoff;
                final String part = etLuogoPartenza.getText().toString();
                final String OFF = etLuogoArrivo.getText().toString();
                final String ora = etOraPartenza.getText().toString();


                o.setData(data);
                o.setCodOfferta(cod);
                o.setLuogoPartenza(part);
                o.setLuogoArrivo(OFF);
                o.setPrezzo(dprezzo);
                o.setOra(ora);
                o.setNumPostiDisponibili(iposti);


                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        realm.copyToRealm(o);
                    }
                });

            }
        });
    }
}

