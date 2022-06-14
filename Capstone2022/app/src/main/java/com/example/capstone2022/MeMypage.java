package com.example.capstone2022;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.app.DatePickerDialog;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.TextView;
import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MeMypage extends AppCompatActivity {
    ImageView back_me, iv_save, pancleC;
    TextView v_date_input;
    EditText hname_input, hnumber_input;
    private DatePickerDialog datePickerDialog;

    private RadioGroup rg_1,rg_2;

    public void Initialeze(){

        hname_input = findViewById(R.id.ed_hname_input);
        hnumber_input = findViewById(R.id.ed_hnumber_input);
        v_date_input = findViewById(R.id.tv_v_date_input);

        back_me = findViewById(R.id.back_me);
        pancleC = findViewById(R.id.iv_panclec);
        iv_save = findViewById(R.id.iv_save);

        rg_1 = findViewById(R.id.rg_1);
        rg_1.clearCheck();
        rg_1.setOnCheckedChangeListener(listner1);
        rg_2 = findViewById(R.id.rg_2);
        rg_2.clearCheck();
        rg_2.setOnCheckedChangeListener(listner2);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage_me);

        this.Initialeze();


        back_me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Fragment();
                Bundle bundle = new Bundle();
                bundle.putString("name", hname_input.getText().toString());
                bundle.putString("numbeer", hnumber_input.getText().toString());
                bundle.putString("date", v_date_input.getText().toString());
                fragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().add(R.id.f_mypage_x, fragment).commit();
//                Intent intent = new Intent(MeMypage.this, MypageFragment.class);
//                String name = hname_input.getText().toString();
//                String number = hnumber_input.getText().toString();
//                String date = v_date_input.getText().toString();
//                intent.putExtra("name",name);
//                intent.putExtra("number",number);
//                intent.putExtra("date",date);
//                startActivity(intent);
            }
        });

        pancleC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int pYear = calendar.get(Calendar.YEAR);
                int pMonth = calendar.get(Calendar.MONTH);
                int pDay = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(MeMypage.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month+1;
                        String date = year + "년" + month + "월" + day + "일";
                        v_date_input.setText(date);
                    }
                }, pYear, pMonth, pDay);
                datePickerDialog.show();
            }
        });

        iv_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MeMypage.this, MainActivity.class);
                intent.putExtra("name",hname_input.getText().toString());
                intent.putExtra("date",v_date_input.getText().toString());
                intent.putExtra("number",hnumber_input.getText().toString());
                startActivity(intent);
            }
        });



    }
    private RadioGroup.OnCheckedChangeListener listner1 = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int checkedld) {
            if(checkedld != -1){
                rg_2.setOnCheckedChangeListener(null);
                rg_2.clearCheck();
                rg_2.setOnCheckedChangeListener(listner2);
            }
        }
    };

    private RadioGroup.OnCheckedChangeListener listner2 = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int checkedld) {
            if (checkedld != -1) {
                rg_1.setOnCheckedChangeListener(null);
                rg_1.clearCheck();
                rg_1.setOnCheckedChangeListener(listner1);
            }
        }
    };
}

