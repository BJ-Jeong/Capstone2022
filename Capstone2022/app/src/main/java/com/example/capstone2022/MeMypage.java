package com.example.capstone2022;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.capstone2022.layout.ToggleButtonGroupTableLayout;

import java.util.Calendar;

public class MeMypage extends AppCompatActivity {

    ImageView editVaccineDate, back;
    TextView text_day;
    DatePickerDialog datePickerDialog;
    ToggleButtonGroupTableLayout radioGroupVaccine;
    RadioButton VaccineNo, Vaccine1st, Vaccine2nd, Vaccine3rd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage_me);

        back = findViewById(R.id.back);
        back.setOnClickListener(view -> finish());

        editVaccineDate = findViewById(R.id.editVaccineDate);
        editVaccineDate.setOnClickListener(view -> {
            Calendar calendar = Calendar.getInstance();
            int pYear = calendar.get(Calendar.YEAR);
            int pMonth = calendar.get(Calendar.MONTH);
            int pDay = calendar.get(Calendar.DAY_OF_MONTH);
            datePickerDialog = new DatePickerDialog(MeMypage.this, (datePicker, year, month, day) -> {
                month = month + 1;
                String date = year + "/" + month + "/" + day;
                text_day = findViewById(R.id.text_day);
                text_day.setText(date);
            }, pYear, pMonth, pDay);
            datePickerDialog.show();
        });

        radioGroupVaccine = findViewById(R.id.radioGroupVaccine);
        VaccineNo = findViewById(R.id.VaccineNo);
        Vaccine1st = findViewById(R.id.Vaccine1st);
        Vaccine2nd = findViewById(R.id.Vaccine2nd);
        Vaccine3rd = findViewById(R.id.Vaccine3rd);
    }
}
