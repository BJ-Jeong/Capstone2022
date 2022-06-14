package com.example.capstone2022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ContactInformation extends AppCompatActivity {

    ImageView back_im;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_imformation);

        back_im = findViewById(R.id.back_im);

        back_im.setOnClickListener(view -> {

        });
        TextView tv_pnumber = findViewById(R.id.tv_pnumber);

        Intent intent = getIntent();
        String tvna = intent.getStringExtra("tvname");
        String tvnu = intent.getStringExtra("tvnumber");
        tv_pnumber.setText(tvnu);
    }
}