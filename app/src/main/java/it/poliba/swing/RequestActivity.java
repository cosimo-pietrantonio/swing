package it.poliba.swing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.SyncConfiguration;
import io.realm.SyncUser;

public class RequestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        String syncServerURL = "https://swing-app.de1a.cloud.realm.io/swingdb";
        final SyncConfiguration config = new SyncConfiguration.Builder(SyncUser.current(), syncServerURL).build();
        final Realm realm = Realm.getInstance(config);

        EditText etLpartenza = findViewById(R.id.etLuogoPartenza);
        EditText etLarrivo = findViewById(R.id.etLuogoArrivo);
        EditText etPosti  = findViewById(R.id.etNumPosti);
        Button bPub = findViewById(R.id.buttonOffri);
        RadioButton rbSingle = findViewById(R.id.radioSingolaO);
        RadioButton rbPeriod = findViewById(R.id.radioPeriodicaO);
        Spinner spinner = findViewById(R.id.spinnerPosti);

        //ArrayList<Integer> v = new ArrayList<>();

        //v.add(1);
        //v.add(2);
        //v.add(3);
        //v.add(4);

        //spinner  :
        // ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spinner.setAdapter(adapter);

        final Intent periodico = new Intent(this, PeriodicoScrolling_Activity.class);

        rbPeriod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(periodico);
            }
        } ) ;


        bPub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });








        }
    }



