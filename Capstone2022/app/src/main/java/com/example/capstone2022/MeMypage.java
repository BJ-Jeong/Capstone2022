package com.example.capstone2022;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.capstone2022.api.ServerConnector;
import com.example.capstone2022.api.user.MemberData;
import com.example.capstone2022.layout.ToggleButtonGroupTableLayout;
import com.example.capstone2022.service.UUIDService;
import com.example.capstone2022.util.GsonUtil;
import com.google.gson.JsonObject;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.UUID;

public class MeMypage extends AppCompatActivity {
    ImageView back_me, iv_save, pancleC;
    TextView v_date_input;
    EditText hname_input, hnumber_input;
    private DatePickerDialog datePickerDialog;

    ToggleButtonGroupTableLayout rg_all;

    public void init() {
        hname_input = findViewById(R.id.ed_hname_input);
        hnumber_input = findViewById(R.id.ed_hnumber_input);
        v_date_input = findViewById(R.id.tv_v_date_input);

        back_me = findViewById(R.id.back_me);
        pancleC = findViewById(R.id.iv_pancle2);
        iv_save = findViewById(R.id.iv_save);

        rg_all = findViewById(R.id.rg_all);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage_me);

        this.init();

        back_me.setOnClickListener(view -> finish());

        pancleC.setOnClickListener(view -> {
            Calendar calendar = Calendar.getInstance();
            int pYear = calendar.get(Calendar.YEAR);
            int pMonth = calendar.get(Calendar.MONTH);
            int pDay = calendar.get(Calendar.DAY_OF_MONTH);
            datePickerDialog = new DatePickerDialog(MeMypage.this, (datePicker, year, month, day) -> {
                month = month + 1;
                String date = year + "년" + month + "월" + day + "일";
                v_date_input.setText(date);
            }, pYear, pMonth, pDay);
            datePickerDialog.show();
        });

        iv_save.setOnClickListener(view -> {
            int vaccineCount = -1;

            if (R.id.rb_v_no == rg_all.getCheckedRadioButtonId()) {
                vaccineCount = 0;
            } else if (R.id.rb_v_1 == rg_all.getCheckedRadioButtonId()) {
                vaccineCount = 1;
            } else if (R.id.rb_v_2 == rg_all.getCheckedRadioButtonId()) {
                vaccineCount = 2;
            } else if (R.id.rb_v_3 == rg_all.getCheckedRadioButtonId()) {
                vaccineCount = 3;
            }

            if (vaccineCount == -1
                    || datePickerDialog == null
                    || hname_input.getText().toString().isEmpty()
                    || hnumber_input.getText().toString().isEmpty()) {
                Toast.makeText(getApplicationContext(), "정보를 입력하세요.", Toast.LENGTH_SHORT).show();
                return;
            }

            int year = datePickerDialog.getDatePicker().getYear();
            int month = datePickerDialog.getDatePicker().getMonth() + 1;
            int day = datePickerDialog.getDatePicker().getDayOfMonth();

            LocalDate vaccineDate = LocalDate.of(year, month, day);

            UUID uuid = new UUIDService(getApplicationContext()).getUUID();
            MemberData data = MemberData.builder()
                    .uuid(uuid)
                    .vaccineCount(vaccineCount)
                    .hospitalName(hname_input.getText().toString())
                    .hospitalContact(hnumber_input.getText().toString())
                    .vaccineDate(vaccineDate).build();
            JsonObject json = data.toJson();

            Log.d("MyMyPage", "data: " + GsonUtil.toText(json));

            ServerConnector.PATCH("rest/member", json, () -> Log.d(MeMypage.class.getSimpleName(), "data Patched"));
        });
    }

}
