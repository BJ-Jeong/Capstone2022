package com.example.capstone2022;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

public class ContactEdit extends AppCompatActivity {

    ImageView back_im;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_edit);

        back_im = findViewById(R.id.back_im);

        back_im.setOnClickListener(view -> {

        });

        //텍스트 뷰 설정
        EditText tv_pnumber2 = findViewById(R.id.et_number);
        TextView tv_pname2 = findViewById(R.id.c_imfor_ti);
        //인텐트 정의
        Intent intent = getIntent();
        //인텐트에서 받아오기
        String tvna = intent.getStringExtra("tvname");
        String tvnu = intent.getStringExtra("tvnumber");
        //텍스트 뷰에 값 전달
        tv_pnumber2.setText(tvnu);
        tv_pname2.setText(tvna);

        //변수 설정
        TextView virusedit = findViewById(R.id.date_virus);
        TextView clearedit = findViewById(R.id.date_clear);
        TextView vaccineedit = findViewById(R.id.date_vaccine);
        Calendar cal = Calendar.getInstance();
        //날짜를 표시하기 전에, 임시로 당일 날짜를 입력받아둔다.
        virusedit.setText(cal.get(Calendar.YEAR) +"-"+ (cal.get(Calendar.MONTH)+1) +"-"+ cal.get(Calendar.DATE));
        clearedit.setText(cal.get(Calendar.YEAR) +"-"+ (cal.get(Calendar.MONTH)+1) +"-"+ cal.get(Calendar.DATE));
        vaccineedit.setText(cal.get(Calendar.YEAR) +"-"+ (cal.get(Calendar.MONTH)+1) +"-"+ cal.get(Calendar.DATE));

        DatePickerDialog datesetv = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                virusedit.setText(year +"-"+ (month+1) +"-"+ dayOfMonth);
            }
        }, cal.get(Calendar.YEAR), (cal.get(Calendar.MONTH)+1), cal.get(Calendar.DATE));
        virusedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (virusedit.isClickable()) {
                    datesetv.show();
                }
            }
        });

        DatePickerDialog datesetc = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                clearedit.setText(year +"-"+ (month+1) +"-"+ dayOfMonth);
            }
        }, cal.get(Calendar.YEAR), (cal.get(Calendar.MONTH)+1), cal.get(Calendar.DATE));
        clearedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clearedit.isClickable()) {
                    datesetc.show();
                }
            }
        });

        DatePickerDialog datesetvac = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                vaccineedit.setText(year +"-"+ (month+1) +"-"+ dayOfMonth);
            }
        }, cal.get(Calendar.YEAR), (cal.get(Calendar.MONTH)+1), cal.get(Calendar.DATE));
        vaccineedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (vaccineedit.isClickable()) {
                    datesetvac.show();
                }
            }
        });

        //플로팅 액션버튼 설정
        FloatingActionButton fab = findViewById(R.id.bt_save);
        //플로팅 액션 버튼에 클릭 리스너 지정
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //전달 용도로 데이터 저장
                String virusDate = (String) virusedit.getText();
                String clearDate = (String) clearedit.getText();
                String vaccineDate = (String) vaccineedit.getText();
                //인텐트 정의
                Intent intent = new Intent(getApplicationContext(), ContactInformation.class);
                intent.putExtra("tvname",tvna);
                intent.putExtra("tvnumber",tvnu);
                intent.putExtra("tvvirus",virusDate);
                intent.putExtra("tvclear",clearDate);
                intent.putExtra("tvvaccine",vaccineDate);
                //해당 화면으로 전환
                startActivity(intent);
            }
        });

    }
}