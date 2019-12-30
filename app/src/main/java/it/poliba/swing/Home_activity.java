package it.poliba.swing;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.fragment.app.Fragment;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.SyncConfiguration;
import io.realm.SyncUser;


public class Home_activity extends AppCompatActivity implements View.OnClickListener
   {

    boolean stato=false; //false: menu chiuso, true : menu aperto
    FloatingActionButton principale;
    FloatingActionButton profilo;
    FloatingActionButton notifiche;
    OvershootInterpolator interpolator = new OvershootInterpolator();

    it.poliba.swing.utente utente = new utente();
    Fragment richiedi = new FragmentRichiesta();  //fragment richiesta
    Fragment offri = new FragmentOfferta();       //fragment offerta

    TextView offerta;
    TextView richiesta;
    FrameLayout layout_offerta;
    FrameLayout layout_richiesta;
    TextView et;

    Intent prof = new Intent();
    Intent not = new Intent();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_activity);
        EditText data = findViewById(R.id.etDataRichiesta);

        final Intent intentProfil = new Intent(this, Profilo_Activity.class);
        final Intent intentNotifich = new Intent(this, MatchList_Activity.class);



        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String syncServerURL = "https://swing-app.de1a.cloud.realm.io/temp8";
        final SyncConfiguration config = new SyncConfiguration.Builder(SyncUser.current(), syncServerURL).build();
        final Realm realm = Realm.getInstance(config);



        //ricezione dati utete loggato
        Intent getter = getIntent();
        Bundle b1 = getter.getExtras();
        if (b1 != null) {
            utente = b1.getParcelable("object_key");
        }



        //set dell'intent per il profilo
        Bundle b2 = new Bundle();
        b2.putParcelable("object_key", utente);
        intentProfil.putExtras(b2);

        //set dell'intent per il matchListActivity
        Bundle b3 = new Bundle();
        b3.putParcelable("object_key", utente);
        intentNotifich.putExtras(b3);



        //menu();

        offerta = findViewById(R.id.offerta);
        richiesta = findViewById(R.id.ricerca);
        layout_offerta=findViewById(R.id.fragment_offerta);
        layout_richiesta =findViewById(R.id.fragment_ricerca);
        final ViewGroup.LayoutParams params_offerta = layout_offerta.getLayoutParams();
        final ViewGroup.LayoutParams params_ricerca = layout_richiesta.getLayoutParams();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_ricerca,richiedi).commit();
        richiesta.setAlpha(0f);

        offerta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                richiesta.setAlpha(1f);
                offerta.setAlpha(0f);
                params_ricerca.width=300;
                params_offerta.width=1140;

                //invio stringa mail
               /* Bundle args = new Bundle();
                args.putString("object_key",utente.getEmail());
                offri.setArguments(args); */

                layout_richiesta.setLayoutParams(params_ricerca);
                layout_offerta.setLayoutParams(params_offerta);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_offerta,offri).commit();

                getSupportFragmentManager().beginTransaction().remove(richiedi).commit();
            }
        });

        richiesta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                richiesta.setAlpha(0f);
                offerta.setAlpha(1f);
                params_offerta.width=300;
                params_ricerca.width=1140;
                Bundle args = new Bundle();
                args.putString("object_key",utente.getEmail());
                richiedi.setArguments(args);
                layout_richiesta.setLayoutParams(params_ricerca);
                layout_offerta.setLayoutParams(params_offerta);

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_ricerca,richiedi).commit();
                getSupportFragmentManager().beginTransaction().remove(offri).commit();
            }
        });
        prof = intentProfil;
        not = intentNotifich;

    }



    //METODO PER IL MENU CON FLOATIN ACTION BUTTON
    private void menu()
    {
        principale = findViewById(R.id.principale);
        profilo = findViewById(R.id.profilo);
        notifiche = findViewById(R.id.notifiche);

        principale.setOnClickListener(this);
        profilo.setOnClickListener(this);
        notifiche.setOnClickListener(this);

        //setAlpha modifica l'opacità dell'oggetto
        profilo.setAlpha(0f);
        notifiche.setAlpha(0f);
    }

    private void openMenu()
    {
        stato=true;
        profilo.animate().translationY(10f).alpha(1f).setInterpolator(interpolator).setDuration(1000).start();
        notifiche.animate().translationY(10f).alpha(1f).setInterpolator(interpolator).setDuration(1000).start();
    }

    private void closeMenu()
    {
        stato=false;
        profilo.animate().translationY(150f).alpha(0f).setInterpolator(interpolator).start();
        notifiche.animate().translationY(150f).alpha(0f).setInterpolator(interpolator).start();
    }


    @Override
    public void onClick(View v)
    {
        switch (v.getId()) {
            case R.id.principale:
                if(stato==false)
                {
                    openMenu();
                }
                else
                {
                    closeMenu();
                }
            case R.id.profilo:
                startActivity(prof);
            case R.id.notifiche:
                startActivity(not);
        }
    }
}
