package it.poliba.swing;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import io.realm.Realm;
import io.realm.SyncConfiguration;
import io.realm.SyncUser;

public class Register_Activity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_);

        final EditText etCognome = (EditText) findViewById(R.id.c);
        final EditText etNome = (EditText) findViewById(R.id.n);
        final EditText etEmail = (EditText) findViewById(R.id.e);
        final EditText etPassword = (EditText) findViewById(R.id.password);
        final EditText etData = (EditText) findViewById(R.id.data);
        final int StartYear = 2000;
        final int StartMonth= 12;
        final int StartDay= 31;
        final Intent login = new Intent(this, Login_Activity.class);
        Button bReg = findViewById(R.id.registrati);


        //Collegamento con il DB
        String syncServerURL = "https://swingdb.de1a.cloud.realm.io/temp12";
        final SyncConfiguration config = new SyncConfiguration.Builder(SyncUser.current(), syncServerURL).build();
        final Realm realm = Realm.getInstance(config);


        //Parte dedicata al setting del calendario
        final DatePickerDialog datePickerDialog = new DatePickerDialog(Register_Activity.this,Register_Activity.this,StartYear,StartMonth,StartDay );

        etData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

        datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                String day,month,year;
                year=Integer.toString(i);
                month=Integer.toString(i1+1);
                day= Integer.toString(i2);
                String data=day+"/"+month+"/"+year;
                etData.setText(data);
            }
        });



        bReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (etNome.getText().toString().equals("") || etCognome.getText().toString().equals("")
                        || etEmail.getText().toString().equals("") || etPassword.getText().toString().equals("")) {

                    Toast.makeText(getApplicationContext(), "tutti i campi devono essere compilati", Toast.LENGTH_LONG).show();

                } else{

                    final Utente uno = new Utente();
                    uno.setCognome(etCognome.getText().toString());
                    uno.setNome(etNome.getText().toString());
                    uno.setEmail(etEmail.getText().toString());
                    uno.setPassword(etPassword.getText().toString());
                    uno.setDataNascita(etData.getText().toString());
                    if (realm.where(Utente.class).equalTo("email", etEmail.getText().toString()).count() != 0) {

                        Toast.makeText(getApplicationContext(), "Email già in uso", Toast.LENGTH_LONG).show();

                    } else {

                        realm.executeTransaction(new Realm.Transaction() {
                            @Override
                            public void execute(Realm realm) {
                                realm.copyToRealm(uno);
                            }
                        });

                        Toast.makeText(getApplicationContext(), "Registrazione effettuata con successo", Toast.LENGTH_LONG).show();
                        startActivity(login);
                        finish();
                    }
                }
            }
        });

    }


    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

    }


}



