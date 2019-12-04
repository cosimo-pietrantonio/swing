package it.poliba.swing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import io.realm.ObjectServerError;
import io.realm.Realm;
import io.realm.SyncConfiguration;
import io.realm.SyncCredentials;
import io.realm.SyncUser;

import static it.poliba.swing.Constants.AUTH_URL;

public class MainActivity extends AppCompatActivity {

    private static int splash_time_out = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Realm.init(getApplicationContext());

        SyncCredentials credentials = SyncCredentials.usernamePassword("NiCoFrAl", "password", false);
        //Credentials contiene le credenziali dell'unico utente che puo accedere al Realm Object Server per modificarlo(quindi effettuare operazioni di lettura e scrittura)
        SyncUser.logInAsync(credentials, AUTH_URL, new SyncUser.Callback<SyncUser>() {
            @Override
            public void onSuccess(SyncUser syncUser) {
                String syncServerURL = "https://swing-app.de1a.cloud.realm.io/swingDB";
                final SyncConfiguration config = new SyncConfiguration.Builder(SyncUser.current(), syncServerURL).build();
                Realm realm = Realm.getInstance(config);

                System.out.println("sono qui");
                Log.i("Login", "Admin identificato");

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent homeIntent = new Intent(MainActivity.this, Login_Activity.class);
                        startActivity(homeIntent);
                        finish();
                    }
                },splash_time_out);
            }

            @Override
            public void onError(ObjectServerError error) {

            }
            });

    }
}
