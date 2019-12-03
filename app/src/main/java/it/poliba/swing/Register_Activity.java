package it.poliba.swing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import io.realm.ObjectServerError;
import io.realm.Realm;
import io.realm.SyncConfiguration;
import io.realm.SyncCredentials;
import io.realm.SyncManager;
import io.realm.SyncUser;

import static it.poliba.swing.Constants.AUTH_URL;

public class Register_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_);

        final EditText etUsername = (EditText) findViewById(R.id.username);
        final EditText etCognome = (EditText) findViewById(R.id.cognome);
        final EditText etNome = (EditText) findViewById(R.id.nome);
        final EditText etEmail = (EditText) findViewById(R.id.email);
        final EditText etPassword = (EditText) findViewById(R.id.password);
        final EditText etDataN = (EditText) findViewById(R.id.dataN);
        Button bReg = (Button) findViewById(R.id.registrati);

        bReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(etUsername.getText().toString().equals("") ||etNome.getText().toString().equals("")||etCognome.getText().toString().equals("")
                        ||etEmail.getText().toString().equals("")||etPassword.getText().toString().equals("")
                        ||etDataN.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(),"tutti i campi devono essere compilati",Toast.LENGTH_LONG).show();
                }
                else
                {
                    utente a = new utente();
                    SyncCredentials credentials = SyncCredentials.usernamePassword("ScaranoNicola","ciao",false);
                    //Credentials contiene le credenziali dell'unico utente che puo accedere al Realm Object Server per modificarlo(quindi effettuare operazioni di lettura e scrittura)
                    SyncUser.logInAsync(credentials, AUTH_URL, new SyncUser.Callback<SyncUser>() //Metodo che effettua il login dell'admin con le credenziali inserite
                    {

                        @Override
                        public void onSuccess(SyncUser user) {
                            System.out.println("sono qui");
                            Log.i("Login", "Admin identificato");

                            //una volta loggati possiamo creare la configurazione che mette in contatto il server con i devices:
                            String syncServerURL = "https://expanded-granite-ball.us1.cloud.realm.io/ScaranoNicola";
                            final SyncConfiguration config = new SyncConfiguration.Builder(SyncUser.current(), syncServerURL).build();
                            Realm realm = Realm.getInstance(config  );
                            final utente uno = new utente();

                            uno.setUsername(etUsername.getText().toString());
                            uno.setCognome(etCognome.getText().toString());
                            uno.setNome(etNome.getText().toString());
                            uno.setEmail(etEmail.getText().toString());
                            uno.setPassword(etPassword.getText().toString());
                            SimpleDateFormat x =new SimpleDateFormat("dd-MM-YYYY");
                            try {
                                uno.setDataNascita(x.parse(etDataN.getText().toString()));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                            realm.executeTransaction(new Realm.Transaction() {
                                @Override
                                public void execute(Realm realm) {

                                    utente realmUser = realm.copyToRealm(uno);

                                }
                            });

                        }

                        @Override
                        public void onError(ObjectServerError error) {
                            //showProgress(false);
                            //usernameView.setError("Uh oh something went wrong! (check your logcat please)");
                            //usernameView.requestFocus();
                            Log.e("Login error", error.toString());
                        }



                    });
                }
            }
        });
    }
}
