package it.poliba.swing;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.lang.UScript;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.SyncConfiguration;
import io.realm.SyncUser;


public class FragmentRichiesta extends DialogFragment implements DatePickerDialog.OnDateSetListener,TimePickerDialog.OnTimeSetListener {
    EditText et_data;
    EditText et_ora;
    EditText et_posti;
    Switch abbonamento;
    EditText et_LPartenza;
    EditText et_LArrivo;
    boolean controllo_posti = false;

    final ArrayList<Offerta> risultatiDelMatch = new ArrayList<>();
    final ArrayList<Offerta> resMatchSemplice = new ArrayList<>();


    String[] day; //contiene tutti gli elementi
    String giorni_selezionati; //conterrà la stringa composta da tutti gli elementi selezionati
    boolean[] checkedItems;
    ArrayList<Integer> UserItem = new ArrayList<>(); //contiene le posizioni all'interno della dialog degli elementi selezionati
    TextView giorni;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        String syncServerURL = "https://swing-app.de1a.cloud.realm.io/temp8";
        final SyncConfiguration config = new SyncConfiguration.Builder(SyncUser.current(), syncServerURL).build();
        final Realm realm = Realm.getInstance(config);

        //ricevo stringa mail
        //final String mailUtente = this.getArguments().getString("object_key");


        final View view = inflater.inflate(R.layout.fragment_richiesta, container, false);
        Button invia = view.findViewById(R.id.InviaR);
        final NumberPicker np = view.findViewById(R.id.numberPickerGiorni);
        np.setMinValue(1);
        np.setMaxValue(7);
        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

            }
        });


        et_LPartenza = view.findViewById(R.id.etLuogoPartenzaF);
        et_LArrivo = view.findViewById(R.id.etLuogoArrivoF);

        et_data = view.findViewById(R.id.etDataRichiesta);
        et_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        et_ora = view.findViewById(R.id.etOraRichiesta);
        et_ora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog();
            }
        });

        et_posti = view.findViewById(R.id.etPostiRichiesta);
        et_posti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (controllo_posti == false) {
                    np.setEnabled(true);
                    np.setAlpha(1f);
                    controllo_posti = true;

                } else {
                    np.setEnabled(false);
                    np.setAlpha(0f);
                    controllo_posti = false;
                }
            }
        });

        abbonamento = view.findViewById(R.id.switch1);
        day = getResources().getStringArray(R.array.numbers);
        checkedItems = new boolean[day.length];
        giorni = view.findViewById(R.id.giorni);
        abbonamento.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    giorni.setEnabled(true);
                    giorni.setAlpha(1f);

                    Toast.makeText(getContext(), "ABBONAMENTO", Toast.LENGTH_SHORT).show();

                    final AlertDialog.Builder scelta = new AlertDialog.Builder(getContext());
                    scelta.setTitle("Seleziona i giorni desiderati");

                    //Aggiungo gli elementi selezionati ad un vettore
                    scelta.setMultiChoiceItems(day, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int position, boolean isChecked) {
                            if (isChecked) {
                                if (!UserItem.contains(position)) {
                                    UserItem.add(position);
                                }

                            } else {
                                for (int i = 0; i < UserItem.size(); i++) {
                                    if (UserItem.get(i) == position) {
                                        UserItem.remove(i);
                                    }
                                }
                            }
                        }

                    });


                    //Comportamento in seguito all'azione di Conferma
                    scelta.setCancelable(false);
                    scelta.setPositiveButton("Conferma", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (!UserItem.isEmpty()) {
                                giorni_selezionati = "";
                                for (int i = 0; i < UserItem.size(); i++) {
                                    giorni_selezionati = giorni_selezionati + day[UserItem.get(i)];
                                    if (i != UserItem.size() - 1) {
                                        giorni_selezionati = giorni_selezionati + "-";
                                    }
                                }
                                giorni.setText(giorni_selezionati);
                            } else giorni.setText("Giorni");
                        }
                    });

                    //Comportamento in seguito all'azione di Annulla
                    scelta.setNegativeButton("Annulla", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });


                    giorni.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AlertDialog giorni = scelta.create();
                            giorni.show();
                        }
                    });

                } else {
                    giorni.setEnabled(false);
                    giorni.setAlpha(0);
                }
            }
        });


        //inviare dati
        invia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Richiesta r = new Richiesta();
                String numero = et_posti.getText().toString();
                int intero = Integer.parseInt(numero);


                r.setDataPartenza(et_data.getText().toString());
                double numerocodrich = Math.random() * 99999999;
                r.setCodRichiesta((int) numerocodrich);
                r.setLuogoPartenza(et_LPartenza.getText().toString());
                r.setLuogoArrivo(et_LArrivo.getText().toString());
                r.setNumPosti(intero);
                r.setOra(et_ora.getText().toString());
               // r.setMailUtente(mailUtente);

                if (UserItem.isEmpty()) {
                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {

                            realm.copyToRealm(r);
                        }
                    });
                    if (realm.where(Richiesta.class).count()!= 0) {
                        Toast.makeText(getContext(), "Ci sono richieste sul DB", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getContext(), "non ci sono richieste sul DB ", Toast.LENGTH_LONG).show();
                    }
                } else {
                    // qui va caricata la richiesta periodica

                }



                // settore del matching
                if (UserItem.isEmpty()) {

                    final RealmQuery<Offerta> queryMatchSingolo = realm.where(Offerta.class).equalTo("luogoPartenza", et_LPartenza.getText().toString())
                            .equalTo("luogoArrivo", et_LArrivo.getText().toString());
                    //MATCH PER OFF-RICH SINGOLE
                    //Controllo se c'è già un' offerta pubblicata dall'utente richiedente con gli stessi parametri della richiesta
                    //if (queryMatchSingolo.equalTo("emailUtente", r.getMailUtente()).equalTo("data", et_data.getText().toString()).count() == 0) {

                        if (queryMatchSingolo.equalTo("data", et_data.getText().toString()).count() != 0)
                        {

                            final RealmResults<Offerta> queryRes = queryMatchSingolo.equalTo("data", et_data.getText().toString()).findAll();
                            final Iterator<Offerta> resIter = queryRes.iterator();

                            String stringaPosti = et_posti.getText().toString();
                            int intPostiRichiesta = Integer.parseInt(stringaPosti);

                            while (resIter.hasNext()) {
                                if (resIter.next().getNumPostiDisponibili() >= intPostiRichiesta) {
                                    System.out.println("Stringa del next: "+resIter.next().getLuogoArrivo());
                                    resMatchSemplice.add(resIter.next());
                                }
                            }
                        }
                    /*} else {
                        Toast.makeText(getContext(), "C'è un offerta da te formulata " +
                                "con gli stessi parametri richiesti in questa data !! "
                                , Toast.LENGTH_LONG).show();   } */
                } else {
                    //MATCH PER OFF-RICH PERIODICHE
                    final RealmQuery<Offerta_Periodica> queryP = realm.where(Offerta_Periodica.class)
                            .equalTo("luogoPartenza", et_LPartenza.getText().toString())
                            .equalTo("luogoArrivo", et_LArrivo.getText().toString());

                    if (queryP.equalTo("emailUtente", r.getMailUtente()).count() == 0) {
                        if (queryP.count() != 0) {
                            RealmResults<Offerta_Periodica> queryResultsP = queryP.findAll();
                            Iterator<Offerta_Periodica> i = queryResultsP.iterator();
                            while (i.hasNext()) {
                                Iterator<String> itGiorni = i.next().getGiorni().iterator();
                                // while (itGiorni.hasNext()) {


                            }
                        }

                    }
                }

// Discuto i risultati del match :
                if (!resMatchSemplice.isEmpty()) {
                    Toast.makeText(getContext(), "Match Semplice riuscito ", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getContext(), "Match semplice non riuscito", Toast.LENGTH_LONG).show();
                }
            }


        });


        return view;
    }

    final int StartYear = 2019;
    final int StartMonth = 12;
    final int StartDay = 31;


    private void showDatePickerDialog() {
        DatePickerDialog dpd = new DatePickerDialog(
                getContext(),
                this,
                StartYear,
                StartMonth,
                StartDay);
        dpd.show();
    }

    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        month = month + 1;
        String date = "" + dayOfMonth + "/" + month;
        et_data.setText(date);
    }


    NumberPicker.OnValueChangeListener onValueChangeListener = new NumberPicker.OnValueChangeListener() {
        @Override
        public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
            et_posti.setText(" " + picker.getValue());
        }
    };

    private void showTimePickerDialog() {
        TimePickerDialog tpd = new TimePickerDialog(
                getContext(),
                this,
                Calendar.HOUR_OF_DAY,
                Calendar.MINUTE,
                true);
        tpd.show();
    }

    public void onTimeSet(TimePicker t, int hour, int minute) {
        String time = "" + hour + ":" + minute + "";
        et_ora.setText(time);
    }


}

