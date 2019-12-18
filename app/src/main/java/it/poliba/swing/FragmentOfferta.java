package it.poliba.swing;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Calendar;

public class FragmentOfferta extends DialogFragment implements DatePickerDialog.OnDateSetListener,TimePickerDialog.OnTimeSetListener
{
    EditText et_data_offerta;
    EditText et_ora_offerta;
    EditText et_posti_offerta;
    Switch abboamento;
    boolean controllo_posti = false;

    String[] day;//contiene tutti gli elementi
    String giorni_selezionati; //conterrà la stringa composta da tutti gli elementi selezionati
    boolean[] checkedItems;
    ArrayList<Integer> UserItem = new ArrayList<>(); //contiene le posizioni all'interno della dialog degli elementi selezionati
    TextView giorni_offerta;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_offerta, container, false);

        final NumberPicker np = view.findViewById(R.id.numberPicker_offerta);
        np.setMinValue(1);
        np.setMaxValue(7);
        np.setOnValueChangedListener(onValueChangeListener);

        et_data_offerta = view.findViewById(R.id.et_data_offerta);
        et_data_offerta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        et_ora_offerta = view.findViewById(R.id.et_ora_offerta);
        et_ora_offerta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog();
            }
        });

        et_posti_offerta = view.findViewById(R.id.et_posti_offerta);
        et_posti_offerta.setOnClickListener(new View.OnClickListener() {
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
