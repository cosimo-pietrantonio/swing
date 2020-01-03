package it.poliba.swing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.SyncConfiguration;
import io.realm.SyncUser;

public class Login_Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);


        Button bLogIn = (Button) findViewById(R.id.buttonLogIn);
        Button bRegister = (Button) findViewById(R.id.buttonReg);
        final EditText etEmail = (EditText) findViewById(R.id.etMail);
        final EditText etPassword = (EditText) findViewById(R.id.etPass);
        final utente u1 = new utente();


        // cofigurazione del DB nell'activity
        String syncServerURL = "https://swing-app.de1a.cloud.realm.io/temp12";
        final SyncConfiguration config = new SyncConfiguration.Builder(SyncUser.current(), syncServerURL).build();
        final Realm realm = Realm.getInstance(config);


        final Intent a = new Intent(this, Register_Activity.class);
        final Intent b = new Intent(this, Home_Activity.class);
        final Intent c = new Intent(this, Richiesta.class);//inutile
        final Intent d = new Intent(this, Offerta.class);//inutile


        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(a);
            }
        });


        bLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RealmQuery<utente> query = realm.where(utente.class).equalTo("email", etEmail.getText().toString()).equalTo("password", etPassword.getText().toString());
                RealmResults<utente> result = query.findAll();
                long temp = query.count();

                if (temp != 0) {
                    u1.setCognome(result.get(0).getCognome());
                    u1.setNome(result.get(0).getNome());
                    u1.setDataNascita(result.get(0).getDataNascita());
                    u1.setEmail(result.get(0).getEmail());
                    u1.setPassword(result.get(0).getPassword());

                    //invio dati utente alla home
                    Bundle b1 = new Bundle();
                    b1.putParcelable("object_key", u1);
                    b.putExtras(b1);
                    startActivity(b);

                } else if (realm.where(utente.class).equalTo("email", etEmail.getText().toString()).count() != 0 && realm.where(utente.class).equalTo("email", etEmail.getText().toString()).equalTo("password", etPassword.getText().toString()).count() == 0) {
                    Toast.makeText(getApplicationContext(), "La Password che hai inserito è errata" , Toast.LENGTH_LONG).show();
                } else if (realm.where(utente.class).equalTo("email", etEmail.getText().toString()).count() == 0) {
                    Toast.makeText(getApplicationContext(), "L'Email che hai inserito è errata" , Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
