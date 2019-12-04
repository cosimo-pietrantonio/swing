package it.poliba.swing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class Login_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);

        Button bLogIn = (Button) findViewById(R.id.buttonLogIn);
        Button bRegister = (Button) findViewById(R.id.buttonReg);
        EditText etEmail = (EditText) findViewById(R.id.etMail);
        EditText etPassword = (EditText) findViewById(R.id.etPass);

        final Intent a = new Intent(this,Register_Activity.class);
        final Intent b = new Intent(this,Home_activity.class);

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                        startActivity(a);

            }
        });

        bLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(b);
            }
        });


    }
}
