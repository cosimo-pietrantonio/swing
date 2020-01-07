package it.poliba.swing;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
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


public class Home_activity extends AppCompatActivity
   {

    boolean stato=false; //false: menu chiuso, true : menu aperto
    FloatingActionButton principale;
    FloatingActionButton profilo;
    FloatingActionButton notifiche;
    OvershootInterpolator interpolator = new OvershootInterpolator();

    Utente utente = new Utente();
    Fragment fragRichiesta = new FragmentRichiesta();  //fragment richiesta
    Fragment fragOfferta = new FragmentOfferta();       //fragment offerta

    TextView offerta;
    TextView richiesta;
    FrameLayout layout_offerta;
    FrameLayout layout_richiesta;
    CoordinatorLayout Home;
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


        String syncServerURL = "https://swingdatabase.de1a.cloud.realm.io/SWING_DB";
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



        menu();

        offerta = findViewById(R.id.offerta);
        richiesta = findViewById(R.id.ricerca);
        layout_offerta=findViewById(R.id.fragment_offerta);
        layout_richiesta =findViewById(R.id.fragment_ricerca);
        Home = findViewById(R.id.home_layout);
        final TextView Alto_destra = findViewById(R.id.textView4);
        final TextView Alto_sinistra = findViewById(R.id.textView2);

        final ViewGroup.LayoutParams params_offerta = layout_offerta.getLayoutParams();
        final ViewGroup.LayoutParams params_ricerca = layout_richiesta.getLayoutParams();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_ricerca,fragRichiesta).commit();
        richiesta.setAlpha(0f);

        offerta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                richiesta.setAlpha(1f);
                offerta.setAlpha(0f);
                params_ricerca.width=300;
                params_offerta.width=1120;


                layout_richiesta.setLayoutParams(params_ricerca);
                layout_offerta.setLayoutParams(params_offerta);
                int pink = Color.parseColor("#f3e7e7");
                int white = Color.parseColor("#FFFFFF");
                Home.setBackgroundColor(pink);
                Alto_destra.setBackgroundColor(pink);
                Alto_sinistra.setBackground(getDrawable(R.drawable.icona_home_bianca));

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_offerta,fragOfferta).commit();
                getSupportFragmentManager().beginTransaction().remove(fragRichiesta).commit();


            }
        });

        richiesta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                richiesta.setAlpha(0f);
                offerta.setAlpha(1f);
                params_offerta.width=300;
                params_ricerca.width=1125;


                layout_richiesta.setLayoutParams(params_ricerca);
                layout_offerta.setLayoutParams(params_offerta);

                int pink = Color.parseColor("#f3e7e7");
                int white = Color.parseColor("#FFFFFF");
                Alto_destra.setBackgroundColor(white);
                Alto_sinistra.setBackground(getDrawable(R.drawable.icona_home_rosa));

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_ricerca,fragRichiesta).commit();
                getSupportFragmentManager().beginTransaction().remove(fragOfferta).commit();
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

        principale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(stato==false)
                {
                    openMenu();
                }
                else
                {
                    closeMenu();
                }
            }
        });
        profilo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(prof);
            }
        });
        notifiche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(not);
            }
        });

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

}
