package it.poliba.swing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_activity);
        Button bRich = findViewById(R.id.bRichiesta);
        Button bOfferta= findViewById(R.id.bOfferta);

        final Intent IntentRich = new Intent(this, RequestActivity.class);
        final Intent IntentOff = new Intent(this , OfferActivity.class);

        bRich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(IntentRich);
            }
        });

        bOfferta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(IntentOff);
            }
        });

    }
}
