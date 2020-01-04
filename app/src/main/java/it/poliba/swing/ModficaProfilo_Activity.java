package it.poliba.swing;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.SyncConfiguration;
import io.realm.SyncUser;

public class ModficaProfilo_Activity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifica_profilo);

        final EditText nome = findViewById(R.id.no);
        final EditText cognome = findViewById(R.id.co);
        final TextView email = findViewById(R.id.em);
        final EditText data = findViewById(R.id.da);
        final EditText password = findViewById(R.id.pa);
        final int StartYear = 2000;
        final int StartMonth= 12;
        final int StartDay= 31;
        Button salva = findViewById(R.id.salva);
        Utente u = new Utente();


        //data picker dialog
        final DatePickerDialog datePickerDialog = new DatePickerDialog(this, ModficaProfilo_Activity.this,StartYear,StartMonth,StartDay );

        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

        datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                //DatePicker date = datePickerDialog.getDatePicker();

                String day,month,year;
                day= Integer.toString(i2);
                month=Integer.toString(i1+1);
                year=Integer.toString(i);
                String data1=day+"/"+month+"/"+year;
                data.setText(data1);
            }
        });

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Email non modificabile", Toast.LENGTH_LONG).show();
            }
        });

        // cofigurazione del DB nell'activity
        String syncServerURL = "https://swing-app.de1a.cloud.realm.io/temp12";
        final SyncConfiguration config = new SyncConfiguration.Builder(SyncUser.current(), syncServerURL).build();
        final Realm realm = Realm.getInstance(config);

        //ricezione dati utete loggato
        Intent b = getIntent();
        Bundle b1 = b.getExtras();
        if (b1 != null) {
            u = b1.getParcelable("object_key");
        }

        nome.setHint(u.getNome());
        cognome.setHint(u.getCognome());
        email.setText(u.getEmail());
        data.setHint(u.getDataNascita());
        password.setHint(u.getPassword());

        final Utente finalU = new Utente();
        final Utente finaluu = new Utente();
        finaluu.setPassword(u.getPassword());
        salva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finalU.setCognome(cognome.getText().toString());
                finalU.setNome(nome.getText().toString());
                finalU.setDataNascita(data.getText().toString());
                finalU.setEmail(email.getText().toString());
                finalU.setPassword(password.getText().toString());
                final String email = finalU.getEmail();

                //controllo
                final String Cognome;
                if(cognome.getText().toString().length()!=0){
                    Cognome =finalU.getCognome();
                }else{
                    Cognome= cognome.getHint().toString();
                }

                final String Nome;
                if(nome.getText().toString().length()!=0){
                    Nome =finalU.getNome();
                }else{
                    Nome= nome.getHint().toString();
                }

                final String Data;
                if(data.getText().toString().length()!=0){
                    Data =finalU.getDataNascita();
                }else{
                    Data= data.getHint().toString();
                }

                final String Password;
                if(password.getText().toString().length()!=0){
                    Password =finalU.getPassword();
                }else{
                    Password= password.getHint().toString();
                }

                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        RealmResults <Utente> utenti = realm.where(Utente.class).equalTo("email",email).findAll();
                        utenti.setValue("nome",Nome );
                        utenti.setValue("cognome", Cognome);
                        utenti.setValue("password", Password);
                        utenti.setValue("dataNascita", Data);
                        Toast.makeText(getApplicationContext(), "Modifica effettuata", Toast.LENGTH_LONG).show();
                        finish();
                    }

                });
            }
        });

    }


    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

    }

}
