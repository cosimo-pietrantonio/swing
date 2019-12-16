package it.poliba.swing;
import android.content.*;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import io.realm.Realm;
import io.realm.SyncConfiguration;
import io.realm.SyncUser;

public class OfferActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offerta);

        String syncServerURL = "https://swing-app.de1a.cloud.realm.io/swingDataB";
        final SyncConfiguration config = new SyncConfiguration.Builder(SyncUser.current(), syncServerURL).build();
        final Realm realm = Realm.getInstance(config);



        final EditText etLuogoPartenza = findViewById(R.id.etLuogoPartenzaO);
        final EditText etLuogoArrivo = findViewById(R.id.etLuogoArrivoO);
        final EditText etDataPartenza =findViewById(R.id.etDataPartenzaO);
        final EditText etOraPartenza = findViewById(R.id.etOraPartenzaO);
        final EditText etPrezzo = findViewById(R.id.etPrezzo);
        Button bOffri = findViewById(R.id.bOffri);
        RadioButton rbPeriodica = findViewById(R.id.radioPeriodica);
        RadioButton rbSingola = findViewById(R.id.radioSingola);


        final Intent periodica = new Intent(this, Offerta_Periodica.class);
        // da creare la scrolling activity, da vedere insieme
        rbPeriodica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(periodica);
            }
        });

        bOffri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Offerta o = new Offerta();

                String prezzo = etPrezzo.getText().toString();
                int iprezzo = Integer.parseInt(prezzo);

                o.setCodOfferta((int)Math.random());
                o.setLuogoPartenza(etLuogoPartenza.); //usare add to list, non mi compare il metodo add di realmlist
                o.setLuogoArrivo(etLuogoArrivo.getText().toString());
                o.setPrezzo(iprezzo);

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