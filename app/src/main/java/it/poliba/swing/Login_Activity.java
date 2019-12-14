package it.poliba.swing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import io.realm.Realm;
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


// cofigurazione del DB nell'activity
        String syncServerURL = "https://swing-app.de1a.cloud.realm.io/swingDataB";
        final SyncConfiguration config = new SyncConfiguration.Builder(SyncUser.current(), syncServerURL).build();
        final Realm realm = Realm.getInstance(config);


        final Intent a = new Intent(this, Register_Activity.class);
        final Intent b = new Intent(this, Home_activity.class);

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(a);

            }
        });

        bLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (realm.where(utente.class).equalTo("email", etEmail.getText().toString()).equalTo("password", etPassword.getText().toString()).count() != 0) {
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
