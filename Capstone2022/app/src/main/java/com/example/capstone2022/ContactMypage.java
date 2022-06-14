package com.example.capstone2022;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.capstone2022.api.ServerConnector;
import com.example.capstone2022.api.user.MemberData;
import com.example.capstone2022.service.UUIDService;
import com.example.capstone2022.util.GsonUtil;
import com.google.gson.JsonObject;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.UUID;

public class ContactMypage extends AppCompatActivity {

    ImageView edit, back_c, con_save;
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

        selfPos = findViewById(R.id.rb_selfpos);
        selfNeg = findViewById(R.id.rb_selfneg);
        fastPos = findViewById(R.id.rb_fastpos);
        fastNeg = findViewById(R.id.rb_fastneg);
        pcrPos = findViewById(R.id.rb_pcrpos);
        pcrNeg = findViewById(R.id.rb_pcrneg);
        text_con5 = findViewById(R.id.text_con5);

        back_c = findViewById(R.id.back_con);
        back_c.setOnClickListener(view -> finish());

        edit = findViewById(R.id.edit);
        edit.setOnClickListener(view -> {
            Calendar calendar = Calendar.getInstance();
            int pYear = calendar.get(Calendar.YEAR);
            int pMonth = calendar.get(Calendar.MONTH);
            int pDay = calendar.get(Calendar.DAY_OF_MONTH);

            datePickerDialog = new DatePickerDialog(ContactMypage.this, (datePicker, year, month, day) -> {
                month = month + 1;
                String date = year + "년" + month + "월" + day + "일";
                text_con5.setText(date);
            }, pYear, pMonth, pDay);

            datePickerDialog.show();
        });

        con_save = findViewById(R.id.con_save);
        con_save.setOnClickListener(view -> {

            boolean self_Pos = selfPos.isChecked();
            boolean fast_Pos = fastPos.isChecked();
            boolean pcr_Pos = pcrPos.isChecked();

            if (datePickerDialog == null) {
                Toast.makeText(getApplicationContext(), "정보를 입력하세요.", Toast.LENGTH_SHORT).show();
                return;
            }

            int year = datePickerDialog.getDatePicker().getYear();
            int month = datePickerDialog.getDatePicker().getMonth() + 1;
            int day = datePickerDialog.getDatePicker().getDayOfMonth();

            LocalDate confirmDate = LocalDate.of(year, month, day);

            UUID uuid = new UUIDService(getApplicationContext()).getUUID();
            MemberData data = MemberData.builder()
                    .uuid(uuid)
                    .confirmDate(confirmDate)
                    .kitPositive(self_Pos)
                    .fastPositive(fast_Pos)
                    .pcrPositive(pcr_Pos)
                    .build();
            JsonObject json = data.toJson();

            Log.d(ContactMypage.class.getSimpleName(), "data: " + GsonUtil.toText(json));

            ServerConnector.PATCH("rest/member", json, () -> Log.d(ContactMypage.class.getSimpleName(), "data Patched"));
        });

    }

}
