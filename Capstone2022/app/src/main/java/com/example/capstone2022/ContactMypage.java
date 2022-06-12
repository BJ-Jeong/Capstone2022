package com.example.capstone2022;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.app.DatePickerDialog;
import android.widget.DatePicker;
import android.widget.TextView;
import java.util.Calendar;
import androidx.appcompat.app.AppCompatActivity;

public class ContactMypage extends AppCompatActivity {

    ImageView edit, back_c;
    TextView text_con5;
    DatePickerDialog datePickerDialog;

    private AppCompatActivity getActivity() {
        return this;
    }

    @Override    
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage_con);

        back_c = findViewById(R.id.back_con);
        back_c.setOnClickListener(view -> finish());

        edit.findViewById(R.id.edit);
        edit.setOnClickListener(view -> {
            Calendar calendar = Calendar.getInstance();
            int pYear = calendar.get(Calendar.YEAR);
            int pMonth = calendar.get(Calendar.MONTH);
            int pDay = calendar.get(Calendar.DAY_OF_MONTH);

            datePickerDialog = new DatePickerDialog(ContactMypage.this, (datePicker, year, month, day) -> {
                month = month+1;
                String date = year + "/" + month + "/" + day;
                text_con5.setText(date);
            }, pYear, pMonth, pDay);

            datePickerDialog.show();
        });
    }

}
