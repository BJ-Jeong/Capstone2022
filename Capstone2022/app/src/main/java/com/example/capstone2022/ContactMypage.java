package com.example.capstone2022;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class ContactMypage extends AppCompatActivity {

    ImageView edit, back_c, saveMyPageCon;
    TextView text_con5;
    DatePickerDialog datePickerDialog;
    RadioButton selfPos, selfNeg, fastPos, fastNeg, pcrPos, pcrNeg;

    private AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage_con);

        back_c = findViewById(R.id.back_con);
        back_c.setOnClickListener(view -> finish());

        edit = findViewById(R.id.edit);
        edit.setOnClickListener(view -> {
            Calendar calendar = Calendar.getInstance();
            int pYear = calendar.get(Calendar.YEAR);
            int pMonth = calendar.get(Calendar.MONTH);
            int pDay = calendar.get(Calendar.DAY_OF_MONTH);

            datePickerDialog = new DatePickerDialog(getApplicationContext(), (datePicker, year, month, day) -> {
                month = month + 1;
                String date = year + "/" + month + "/" + day;
                text_con5.setText(date);
            }, pYear, pMonth, pDay);

            datePickerDialog.show();
        });

        selfPos = findViewById(R.id.selfpos);
        selfNeg = findViewById(R.id.selfneg);
        fastPos = findViewById(R.id.fastpos);
        fastNeg = findViewById(R.id.fastneg);
        pcrPos = findViewById(R.id.pcrpos);
        pcrNeg = findViewById(R.id.pcrneg);

        saveMyPageCon = findViewById(R.id.saveMyPageCon);
        saveMyPageCon.setOnClickListener(view -> {

        });

    }

}
