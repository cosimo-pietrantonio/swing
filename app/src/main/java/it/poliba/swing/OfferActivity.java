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

        String syncServerURL = "https://swing-app.de1a.cloud.realm.io/swingdb";
        final SyncConfiguration config = new SyncConfiguration.Builder(SyncUser.current(), syncServerURL).build();
        final Realm realm = Realm.getInstance(config);

        EditText etLuogoPartenza = findViewById(R.id.etLuogoPartenzaO);
        EditText etLuogoArrivo = findViewById(R.id.etLuogoArrivoO);
        EditText etDataPartenza =findViewById(R.id.etDataPartenzaO);
        EditText etOraPartenza = findViewById(R.id.etOraPartenzaO);
        EditText etPrezzo = findViewById(R.id.etPrezzo);
        Button bOffri = findViewById(R.id.bPubblica);
        RadioButton rbPeriodica = findViewById(R.id.radioPeriodica);
        RadioButton rbSingola = findViewById(R.id.radioSingola);


        final Intent periodica = new Intent(this, OffertaPeriodica_ScrollingActivity); //da creare la scrolling activity, da vedere insieme
        rbPeriodica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(periodica);
            }
        } ) ;


        bOffri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        } );
    }
}