package it.poliba.swing;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.SyncConfiguration;
import io.realm.SyncUser;

public class MatchList_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_list_);

        final TextView add = new TextView(MatchList_Activity.this);
        final LinearLayout offerte = findViewById(R.id.l2);
        final LinearLayout richieste = findViewById(R.id.l3);
        Boolean bitOff=false;
        Boolean bitRic=true;

        String syncServerURL = "https://swing-app.de1a.cloud.realm.io/temp9";
        final SyncConfiguration config = new SyncConfiguration.Builder(SyncUser.current(), syncServerURL).build();
        final Realm realm = Realm.getInstance(config);


        if(bitRic == true){
            add.setText("Bari-noci   ore: 15:00  ciao ciao");
            add.setPadding(5,5,0,15);
            add.setTextColor(Color.BLACK);
            add.setTextSize(25);
            add.setBackgroundColor(Color.WHITE);
            add.setFocusable(true);
            add.setSingleLine(true);
            add.setEllipsize(TextUtils.TruncateAt.MARQUEE);
            add.setMarqueeRepeatLimit(100);
            add.setFocusable(true);
            add.setFocusableInTouchMode(true);
            add.setHorizontallyScrolling(true);
            add.setSelected(true);
            offerte.addView(add);
        }

        if(bitOff == true){
            add.setText("Bari-noci   ore: 15:00  ciao ciao");
            add.setPadding(5,5,0,15);
            add.setTextColor(Color.BLACK);
            add.setTextSize(25);
            add.setBackgroundColor(Color.WHITE);
            add.setFocusable(true);
            add.setSingleLine(true);
            add.setEllipsize(TextUtils.TruncateAt.MARQUEE);
            add.setMarqueeRepeatLimit(100);
            add.setFocusable(true);
            add.setFocusableInTouchMode(true);
            add.setHorizontallyScrolling(true);
            add.setSelected(true);
            offerte.addView(add);
        }




    }


}
