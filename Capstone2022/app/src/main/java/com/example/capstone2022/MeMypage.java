package com.example.capstone2022;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.capstone2022.layout.ToggleButtonGroupTableLayout;
import com.example.capstone2022.service.UUIDService;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.UUID;

public class MeMypage extends AppCompatActivity {

    ImageView editVaccineDate, back, saveMyPage;
    EditText hospitalName, hospitalContact;
    TextView textDay;
    DatePickerDialog datePickerDialog;
    ToggleButtonGroupTableLayout radioGroupVaccine;
    RadioButton vaccineNo, vaccine1st, vaccine2nd, vaccine3rd;
    LocalDate vaccineDate;

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

                vaccineDate = LocalDate.of(year, month, day);
                String date = year + "/" + month + "/" + day;

                textDay = findViewById(R.id.text_day);
                textDay.setText(date);
            }, pYear, pMonth, pDay);
            datePickerDialog.show();
        });

        radioGroupVaccine = findViewById(R.id.radioGroupVaccine);
        vaccineNo = findViewById(R.id.VaccineNo);
        vaccine1st = findViewById(R.id.Vaccine1st);
        vaccine2nd = findViewById(R.id.Vaccine2nd);
        vaccine3rd = findViewById(R.id.Vaccine3rd);
        hospitalName = findViewById(R.id.hospital_name);
        hospitalContact = findViewById(R.id.hospital_contact);

        saveMyPage = findViewById(R.id.saveMyPageMe);
        saveMyPage.setOnClickListener(view -> {
            RadioButton vaccineStatusButton = radioGroupVaccine.getActiveRadioButton();
            if (vaccineStatusButton == null) {
                sendToast("접종 상태를 선택하세요.");
                return;
            }

            String vaccineStatus = vaccineStatusButton.getText().toString();
            String hospitalNameString = hospitalName.getText().toString();
            String hospitalContextString = hospitalContact.getText().toString();

            if (hospitalNameString.isEmpty() || hospitalContextString.isEmpty() || datePickerDialog == null) {
                sendToast("텍스트를 입력하고 접종 날짜를 선택하세요.");
                return;
            }

            UUID uuid = new UUIDService(getApplicationContext()).getUUID();
            /* APIConnector.POST("member", new MemberData(uuid, new MemberData.MemberCoronaInfo(

            )));*/
        });
    }

    private void sendToast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }

}
