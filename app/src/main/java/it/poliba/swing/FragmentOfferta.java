package it.poliba.swing;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
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

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.SyncConfiguration;
import io.realm.SyncUser;

public class FragmentOfferta extends DialogFragment implements DatePickerDialog.OnDateSetListener,TimePickerDialog.OnTimeSetListener
{
    EditText et_luogo_partenza;
    EditText et_luogo_arrivo;
    EditText et_data_offerta;
    EditText et_ora_offerta;
    EditText et_posti_offerta;
    EditText et_prezzo;
    Switch abboamento;
    boolean controllo_posti = false;

    String[] day;//contiene tutti gli elementi
    String giorni_selezionati; //conterrà la stringa composta da tutti gli elementi selezionati
    boolean[] checkedItems;
    ArrayList<Integer> UserItem = new ArrayList<>(); //contiene le posizioni all'interno della dialog degli elementi selezionati
    TextView giorni_offerta;

    final Offerta o = new Offerta();
    final Offerta_Periodica op = new Offerta_Periodica();
    RealmList<String> giorniSel = new RealmList<>();
    ArrayList<Richiesta> resMatchSemplice = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



        //configurazione  DB
        String syncServerURL = "https://swing-app.de1a.cloud.realm.io/temp12";
        final SyncConfiguration config = new SyncConfiguration.Builder(SyncUser.current(), syncServerURL).build();
        final Realm realm = Realm.getInstance(config);


        final View view = inflater.inflate(R.layout.fragment_offerta, container, false);
        Button invia = view.findViewById(R.id.InviaO);

        et_luogo_arrivo= view.findViewById(R.id.etLuogoArrivoF);
        et_luogo_partenza= view.findViewById(R.id.etLuogoPartenzaF);

        et_data_offerta = view.findViewById(R.id.etDataOfferta);
        et_data_offerta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        et_ora_offerta = view.findViewById(R.id.etOraOfferta);
        et_ora_offerta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog();
            }
        });

        et_prezzo = view.findViewById(R.id.etPrezzo);
        et_posti_offerta = view.findViewById(R.id.etPostiOfferta);

        abboamento = view.findViewById(R.id.switch1);
        day = getResources().getStringArray(R.array.numbers);
        checkedItems = new boolean[day.length];
        giorni_offerta = view.findViewById(R.id.giorni_offerta);
        abboamento.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    giorni_offerta.setEnabled(true);
                    giorni_offerta.setAlpha(1f);

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
                                    giorniSel.add(day[UserItem.get(i)]);
                                    if (i != UserItem.size() - 1) {
                                        giorni_selezionati = giorni_selezionati + "-";
                                    }
                                }
                                giorni_offerta.setText(giorni_selezionati);
                            } else giorni_offerta.setText("Giorni");
                        }
                    });

                    //Comportamento in seguito all'azione di Annulla
                    scelta.setNegativeButton("Annulla", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    giorni_offerta.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AlertDialog giorni = scelta.create();
                            giorni.show();
                        }
                    });
                } else {
                    giorni_offerta.setEnabled(false);
                    giorni_offerta.setAlpha(0);
                }
            }
        });


        //invia dati
        invia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                double numerocodoff = Math.random() * 99999999;
                final int cod = (int) numerocodoff;

                if (UserItem.isEmpty()) {

                    String prezzo = et_prezzo.getText().toString();
                    final double dprezzo = Double.parseDouble(prezzo);


                    String posti = et_posti_offerta.getText().toString();
                    final int iposti = Integer.parseInt(posti);




                    o.setData(et_data_offerta.getText().toString());
                    o.setCodOfferta(cod);
                    o.setLuogoPartenza(et_luogo_partenza.getText().toString());
                    o.setLuogoArrivo(et_luogo_arrivo.getText().toString());
                    o.setPrezzo(dprezzo);
                    o.setOra(et_ora_offerta.getText().toString());
                    o.setNumPostiDisponibili(iposti);
                    o.setEmailUtente(((Home_Activity) getActivity()).utente.getEmail());


                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            realm.copyToRealm(o);
                        }
                    });
                } else {

                    op.setDataInizio(et_data_offerta.getText().toString());
                    op.setCodOfferta(cod);
                    op.setLuogoPartenza(et_luogo_partenza.getText().toString());
                    op.setLuogoArrivo(et_luogo_arrivo.getText().toString());
                    op.setGiorni(giorniSel);
                    op.setEmailUtente(((Home_Activity) getActivity()).utente.getEmail());

                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            realm.copyToRealm(op);
                        }
                    });

                }
                    // verifico caricam offerta

                if (realm.where(Offerta.class).count()!= 0) {
                    Toast.makeText(getContext(), "Ci sono offerte sul DB", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getContext(), "non ci sono offerte sul DB ", Toast.LENGTH_LONG).show();
                }


                //MATCH

                //singola

                if (UserItem.isEmpty()) {
                    final RealmQuery<Richiesta> queryMatchSingolo = realm.where(Richiesta.class).equalTo("luogoPartenza", et_luogo_partenza.getText().toString())
                            .equalTo("luogoArrivo", et_luogo_arrivo.getText().toString());
                    if (queryMatchSingolo.equalTo("emailUtente", o.getEmailUtente())
                            .equalTo("dataPartenza", et_data_offerta.getText().toString()).count() == 0) {
                        if (queryMatchSingolo.equalTo("dataPartenza", et_data_offerta.getText().toString()).count() != 0) {
                            final RealmResults<Richiesta> queryRes = queryMatchSingolo.equalTo("dataPartenza", et_data_offerta.getText().toString()).findAll();
                            String stringaPosti = et_posti_offerta.getText().toString();
                            int intPostiOfferta = Integer.parseInt(stringaPosti);
                            for (int i = 0; i < queryRes.size(); i++) {
                                if (queryRes.get(i).getNumPosti() <= intPostiOfferta) {
                                    resMatchSemplice.add(queryRes.get(i));
                                }
                            }
                        }
                    } else {Toast.makeText(getContext(),"C'è già un'' offerta da te inserita con questi parametri",Toast.LENGTH_LONG).show(); }
                }else {
                    //periodica
                    final RealmQuery<Richiesta_Periodica> queryP = realm.where(Richiesta_Periodica.class).equalTo("luogoPartenza", et_luogo_partenza.getText().toString()).equalTo("luogoArrivo", et_luogo_arrivo.getText().toString());
                    if (queryP.equalTo("mailUtente", op.getEmailUtente()).count() == 0) {
                        if (queryP.count() != 0) {
                            RealmResults<Richiesta_Periodica> queryResultsP = queryP.findAll();


                        }
                    }
                }
                //risultati match
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
    final int StartMonth= 12;
    final int StartDay= 31;

    private void showDatePickerDialog()
    {
        DatePickerDialog dpd = new DatePickerDialog(
                getContext(),
                this,
                StartYear,
                StartMonth,
                StartDay);
        dpd.show();
    }

    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
    {
        month = month+1;
        String date =""+dayOfMonth+"/"+month;
        et_data_offerta.setText(date);
    }

    NumberPicker.OnValueChangeListener onValueChangeListener = new NumberPicker.OnValueChangeListener() {
        @Override
        public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
            et_posti_offerta.setText(" "+picker.getValue());
        }
    };

    private void showTimePickerDialog()
    {
        TimePickerDialog tpd = new TimePickerDialog(
                getContext(),
                this,
                Calendar.HOUR_OF_DAY,
                Calendar.MINUTE,
                true);

        tpd.show();
    }

    public void onTimeSet(TimePicker t, int hour, int minute)
    {
        String time =""+hour+":"+minute+"";
        et_ora_offerta.setText(time);
    }


}
