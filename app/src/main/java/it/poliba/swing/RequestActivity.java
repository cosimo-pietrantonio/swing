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

public class RequestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        String syncServerURL = "https://swing-app.de1a.cloud.realm.io/temp2";
        final SyncConfiguration config = new SyncConfiguration.Builder(SyncUser.current(), syncServerURL).build();
        final Realm realm = Realm.getInstance(config);
        final EditText etLpartenza = findViewById(R.id.etLuogoPartenza);
        final EditText etData = findViewById(R.id.etDataPartenza);
        final EditText etLarrivo = findViewById(R.id.etLuogoArrivo);
        final EditText etPosti  = findViewById(R.id.etNumPosti);
        final EditText etOra    = findViewById(R.id.etOra);
        Button bPub = findViewById(R.id.bRichiesta);
        RadioButton rbSingle = findViewById(R.id.radioSingola);
        RadioButton rbPeriod = findViewById(R.id.radioPeriodica);

        final ArrayList<Offerta> risultatiDelMatch = new ArrayList<>();
        final ArrayList<Offerta> res = new ArrayList<>();

        final Intent periodico = new Intent(this, RichiestaPeriodicaScrolling_Activity.class);
        final Intent passaggioMatchList = new Intent(this, MatchList_Activity.class);

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


                r.setDataPartenza(etData.getText().toString());
                double numerocodrich= Math.random()*99999999;
                r.setCodRichiesta((int) numerocodrich);
                r.setLuogoPartenza(etLpartenza.getText().toString());
                r.setLuogoArrivo(etLarrivo.getText().toString());
                r.setNumPosti( intero );
                r.setOra(etOra.getText().toString());

                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {

                        realm.copyToRealm(r);

                    }

                });

                Date orarioConvertito = new Date();
                final String orarioDaConv = etOra.getText().toString();
                final DateFormat sdf= new SimpleDateFormat("hh:mm");
                try {
                      orarioConvertito = sdf.parse(orarioDaConv);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                // settore del matching

                if (realm.where(Offerta.class).equalTo("luogoPartenza",etLpartenza.getText().toString())
                        .equalTo("luogoArrivo",etLarrivo.getText().toString()).count() != 0  ){
                    // inserire confronto su dataPartenza
                    RealmQuery<Offerta> query = realm.where(Offerta.class).equalTo("luogoPartenza",etLpartenza.getText().toString())
                            .equalTo("luogoArrivo",etLarrivo.getText().toString());
                    RealmResults<Offerta> queryResults = query.findAll();
                    Iterator<Offerta> resIter= queryResults.iterator();
                    while (resIter.hasNext()){
                        res.add(resIter.next());
                    }
                    Iterator<Offerta> arrayIterator = res.iterator();
                    while(arrayIterator.hasNext()){

                        String orarioListaDaConv = arrayIterator.next().getOra();
                        Date orarioListaConvertito = new Date();
                        try {
                             orarioListaConvertito = sdf.parse(orarioListaDaConv);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        if (orarioConvertito.compareTo(orarioListaConvertito) < 0){
                            risultatiDelMatch.add(arrayIterator.next());
                        }

                   }

                }
                if (risultatiDelMatch.isEmpty() == false){

                    startActivity(passaggioMatchList);
                    Toast.makeText(getApplicationContext(), "Match riuscito!!!!!", Toast.LENGTH_LONG).show();

                }
                else{
                    Toast.makeText(getApplicationContext(), "Match NON riuscito!!!!!", Toast.LENGTH_LONG).show();

                }







            }
        });








        }
    }



