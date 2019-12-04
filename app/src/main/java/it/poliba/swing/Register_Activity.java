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

        final EditText etCognome = (EditText) findViewById(R.id.cognome);
        final EditText etNome = (EditText) findViewById(R.id.nome);
        final EditText etEmail = (EditText) findViewById(R.id.email);
        final EditText etPassword = (EditText) findViewById(R.id.password);
        final EditText etDataN = (EditText) findViewById(R.id.dataN);
        Button bReg = (Button) findViewById(R.id.registrati);



        bReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (etNome.getText().toString().equals("") || etCognome.getText().toString().equals("")
                        || etEmail.getText().toString().equals("") || etPassword.getText().toString().equals("")
                        || etDataN.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "tutti i campi devono essere compilati", Toast.LENGTH_LONG).show();
                } else
                    {
                        utente a = new utente();
                        {

                                //una volta loggati possiamo creare la configurazione che mette in contatto il server con i devices:
                            String syncServerURL = "https://swing-app.de1a.cloud.realm.io/swingDB";
                            final SyncConfiguration config = new SyncConfiguration.Builder(SyncUser.current(), syncServerURL).build();
                            Realm realm = Realm.getInstance(config);

                                final utente uno = new utente();

                                uno.setCognome(etCognome.getText().toString());
                                uno.setNome(etNome.getText().toString());
                                uno.setEmail(etEmail.getText().toString());
                                uno.setPassword(etPassword.getText().toString());
                                SimpleDateFormat x = new SimpleDateFormat("dd-MM-YYYY");
                                try {
                                    uno.setDataNascita(x.parse(etDataN.getText().toString()));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                                boolean temp = false;
                                if (temp == false) {

                                }
                                if (realm.where(utente.class).equalTo("email", etEmail.getText().toString()).count() != 0) {
                                    Toast.makeText(getApplicationContext(), "Email già in uso", Toast.LENGTH_LONG).show();
                                }else{
                                    realm.executeTransaction(new Realm.Transaction() {
                                    @Override
                                    public void execute(Realm realm) {
                                        utente realmUser = realm.copyToRealm(uno);
                                    }

                                    });
                                }
                                }




                            }


                        }
            });
                }
            }



