package it.poliba.swing;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.TextView;


public class Home_Activity extends AppCompatActivity implements View.OnClickListener{

    boolean stato=false; //flase: menu chiuso, true:menu aperto
    FloatingActionButton principale;
    FloatingActionButton profilo;
    FloatingActionButton notifiche;
    OvershootInterpolator interpolator = new OvershootInterpolator();

    Fragment richiedi = new Fragment_Richiesta();  //fragment richiesta
    Fragment offri = new FragmentOfferta();       //fragment offerta
    TextView offerta;
    TextView richiesta;
    FrameLayout layout_offerta;
    FrameLayout layout_richiesta;

    TextView et;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        menu();

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

                layout_richiesta.setLayoutParams(params_ricerca);
                layout_offerta.setLayoutParams(params_offerta);

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_ricerca,richiedi).commit();
                getSupportFragmentManager().beginTransaction().remove(offri).commit();
            }
        });
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
                Log.i("info","profilo");
            case R.id.notifiche:
                Log.i("info","notifiche");
        }
    }
}
