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


        Button bLogIn = findViewById(R.id.buttonLogIn);
        Button bRegister = findViewById(R.id.buttonReg);
        final EditText etEmail = findViewById(R.id.etMail);
        final EditText etPassword = findViewById(R.id.etPass);



        // cofigurazione del DB nell'activity
        String syncServerURL = "https://swingdatabase.de1a.cloud.realm.io/temp12";
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
                RealmQuery<Utente> query = realm.where(Utente.class).equalTo("email", etEmail.getText().toString()).equalTo("password", etPassword.getText().toString());
                RealmResults<Utente> result = query.findAll();
                long temp = query.count();
                if (temp != 0) {
                    Bundle b1 = new Bundle();
                    b1.putParcelable("object_key", result.get(0));
                    b.putExtras(b1);
                    startActivity(b);
                } else if (realm.where(Utente.class).equalTo("email", etEmail.getText().toString()).count() != 0 && realm.where(Utente.class).equalTo("email", etEmail.getText().toString()).equalTo("password", etPassword.getText().toString()).count() == 0) {
                    Toast.makeText(getApplicationContext(), "La Password che hai inserito è errata" , Toast.LENGTH_LONG).show();
                } else if (realm.where(Utente.class).equalTo("email", etEmail.getText().toString()).count() == 0) {
                    Toast.makeText(getApplicationContext(), "L'Email che hai inserito è errata" , Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
