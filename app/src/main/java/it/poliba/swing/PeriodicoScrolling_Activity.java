package it.poliba.swing;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.SyncConfiguration;
import io.realm.SyncUser;

public class PeriodicoScrolling_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_periodico_scrolling_);

        String syncServerURL = "https://swing-app.de1a.cloud.realm.io/swingdb";
        final SyncConfiguration config = new SyncConfiguration.Builder(SyncUser.current(), syncServerURL).build();
        final Realm realm = Realm.getInstance(config);



        final CheckBox cbLun = findViewById(R.id.cbLun);
        final CheckBox cbMar = findViewById(R.id.cbMar);
        final CheckBox cbMer = findViewById(R.id.cbMer);
        final CheckBox cbGio = findViewById(R.id.cbGio);
        final CheckBox cbVen = findViewById(R.id.cbVen);
        final CheckBox cbSab = findViewById(R.id.cbSab);
        final CheckBox cbDom = findViewById(R.id.cbDom);
        final EditText etLuogoPar = findViewById(R.id.etLuogoPartenza);
        final EditText etLuogoArr = findViewById(R.id.etLuogoArrivo);
        final EditText etOra = findViewById(R.id.etOraPartenza);
        final Button bPubb = findViewById(R.id.buttonPubblica);

        Richiesta_Periodico rp= new Richiesta_Periodico();
        final RealmList<Boolean> giorni;


        bPubb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbLun.isChecked() == true)
                else if(cbMar.isChecked()==true)
                else if(cbMer.isChecked()==true)

                else if (cbGio.isChecked()==true)

                else if (cbVen.isChecked()==true)
                else if (cbSab.isChecked()==true)
                else if (cbDom.isChecked()==true)
                else if ()

            }
        });





    }
        }
