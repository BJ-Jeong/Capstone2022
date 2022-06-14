package com.example.capstone2022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ContactInformation extends AppCompatActivity {

    ImageView back_im;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_imformation);

        back_im = findViewById(R.id.back_im);
        back_im.setOnClickListener(view -> finish());

        //텍스트 뷰 설정
        TextView tv_pnumber = findViewById(R.id.tv_pnumber);
        TextView tv_pname = findViewById(R.id.c_imfor_ti);
        TextView tv_virus = findViewById(R.id.tv_virus);
        TextView tv_clear = findViewById(R.id.tv_clear);
        TextView tv_vaccine = findViewById(R.id.tv_vaccine);
        //인텐트 정의 (이 인텐트는 ContactFragment에서 넘어옵니다.
        Intent intent = getIntent();
        //인텐트에서 받아오기
        String tvna = intent.getStringExtra("tvname");
        String tvnu = intent.getStringExtra("tvnumber");
        //텍스트 뷰에 값 전달
        tv_pnumber.setText(tvnu);
        tv_pname.setText(tvna);

        //플로팅 액션버튼 설정
        FloatingActionButton fab = findViewById(R.id.bt_revise);
        //플로팅 액션 버튼에 클릭 리스너 지정
        fab.setOnClickListener(view -> {
            //인텐트 정의(다른 화면으로 넘어가야 하므로, 방금 데이터 받을 용도로 만든 것과 별개.)
            Intent intent1 = new Intent(getApplicationContext(), ContactEdit.class);
            intent1.putExtra("tvname",tvna);
            intent1.putExtra("tvnumber",tvnu);
            //해당 화면으로 전환
            startActivity(intent1);
        });

        //인텐트 정의 (이 인텐트는 ContactEdit에서 넘어옵니다.
        Intent intent2 = getIntent();
        //인텐트에서 받아오기
        String tvna2 = intent2.getStringExtra("tvname");
        String tvnu2 = intent2.getStringExtra("tvnumber");
        String tvvi = intent2.getStringExtra("tvvirus");
        String tvcl = intent2.getStringExtra("tvclear");
        String tvva = intent2.getStringExtra("tvvaccine");
        //텍스트 뷰에 값 전달
        tv_pnumber.setText(tvnu2);
        tv_pname.setText(tvna2);
        tv_virus.setText(tvvi);
        tv_clear.setText(tvcl);
        tv_vaccine.setText(tvva);

    }
}
