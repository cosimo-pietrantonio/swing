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

public class RequestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_periodico_scrolling_);

        String syncServerURL = "https://swing-app.de1a.cloud.realm.io/swingDataB";
        final SyncConfiguration config = new SyncConfiguration.Builder(SyncUser.current(), syncServerURL).build();
        final Realm realm = Realm.getInstance(config);

        final EditText etLpartenza = findViewById(R.id.etLuogoPartenza);
        final EditText etLarrivo = findViewById(R.id.etLuogoArrivo);
        final EditText etPosti  = findViewById(R.id.etNumPosti);
        Button bPub = findViewById(R.id.bRichiesta);
        RadioButton rbSingle = findViewById(R.id.radioSingola);
        RadioButton rbPeriod = findViewById(R.id.radioPeriodica);


        //ArrayList<Integer> v = new ArrayList<>();

        //v.add(1);
        //v.add(2);
        //v.add(3);
        //v.add(4);

        //spinner  :
        // ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spinner.setAdapter(adapter);


        final Intent periodico = new Intent(this, RichiestaPeriodicaScrolling_Activity.class);

        rbPeriod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(periodico);
            }
        } ) ;


        bPub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Richiesta r =new Richiesta();

                String numero= etPosti.getText().toString();
                int intero = Integer.parseInt(numero);

                r.setCodRichiesta((int) Math.random());
                r.setLuogoPartenza(etLpartenza.getText().toString());
                r.setLuogoArrivo(etLarrivo.getText().toString());
                r.setNumPosti( intero );

                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        realm.copyToRealm(r);
                    }
                });


            }
        });








        }
    }



