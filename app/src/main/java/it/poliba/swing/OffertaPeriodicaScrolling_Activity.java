package it.poliba.swing;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.SyncConfiguration;
import io.realm.SyncUser;

public class OffertaPeriodicaScrolling_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passaggio__periodico_);

        String syncServerURL = "https://swing-app.de1a.cloud.realm.io/swingDataB";
        final SyncConfiguration config = new SyncConfiguration.Builder(SyncUser.current(), syncServerURL).build();
        final Realm realm = Realm.getInstance(config);

        final CheckBox cbLun = findViewById(R.id.cbLun);
        final CheckBox cbMar = findViewById(R.id.cbMar);
        final CheckBox cbMer = findViewById(R.id.cbMer);
        final CheckBox cbGio = findViewById(R.id.cbGio);
        final CheckBox cbVen = findViewById(R.id.cbVen);
        final CheckBox cbSab = findViewById(R.id.cbSab);
        final CheckBox cbDom = findViewById(R.id.cbDom);
        final EditText etLuogoPar = findViewById(R.id.etLuogoPartenzaO);
        final EditText etLuogoArr = findViewById(R.id.etLuogoArrivoO);
        final EditText etOra = findViewById(R.id.etOraPartenzaO);

        final Button bOffriPeriodico = findViewById(R.id.bOffri);

        final RealmList<String> giorni= new RealmList<>();

        bOffriPeriodico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(cbLun.isChecked() == true)
                    giorni.add("lunedi");

                if(cbMar.isChecked()==true)
                    giorni.add("martedi");

                if(cbMer.isChecked()==true)
                    giorni.add("mercoledi");

                if (cbGio.isChecked()==true)
                    giorni.add("giovedi");

                if (cbVen.isChecked()==true)
                    giorni.add("venerdi");

                if (cbSab.isChecked()==true)
                    giorni.add("sabato");

                if (cbDom.isChecked()==true)
                    giorni.add("domenica");

                if (giorni.isEmpty())
                    Toast.makeText(getApplicationContext(),"Seleziona almeno un giorno della settimana", Toast.LENGTH_LONG).show();

                else if (  etLuogoArr.getText().toString().equals("")
                        || etLuogoPar.getText().toString().equals("")
                        || etOra.getText().toString().equals("")  )
                    Toast.makeText(getApplicationContext(), "Compilare tutti i campi", Toast.LENGTH_LONG).show();
                else {
                    //creazione offerta periodica
                    final Offerta_Periodica rp= new Offerta_Periodica();
                    rp.setCodOfferta((int) Math.random());
                    rp.setLuogoPartenza(etLuogoPar.); //stessa cosa, vedere come fare add list to realmlist
                    rp.setLuogoArrivo(etLuogoArr.getText().toString());
                    rp.setGiorni(giorni);

                    //salvataggio dell'offerta sul DB
                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {

                            realm.copyToRealm(rp);
                        }
                    });
                }
            }
        });
    }
}
